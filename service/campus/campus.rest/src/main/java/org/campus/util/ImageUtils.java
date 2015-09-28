package org.campus.util;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.campus.config.SystemConfig;
import org.campus.core.web.Attachment;

public class ImageUtils {

    public static Attachment getAttachment(String fileId, byte[] data) {
        Attachment attachment = new Attachment();
        attachment.setContent(data);
        attachment.setOriginalFilename(fileId);
        attachment.setContentType("image/jpg");
        return attachment;
    }

    public static File cut(BufferedImage image, String format, int width, int height) throws IOException {
        Rectangle rect = getRectangle(width, height);
        return saveSubImage(image, format, rect);
    }

    private static Rectangle getRectangle(int width, int height) {
        int newWidth = 0;// 新图的宽
        int newHeight = 0;// 新图的高
        Rectangle rectangle = null;
        if (division(width, height) < 0.5) {
            newWidth = width;
            newHeight = (int) (newWidth * (370.0 / 640.0));
            rectangle = new Rectangle(newWidth, newHeight);
        } else if ((0.5 <= division(width, height)) && (division(width, height) <= 0.8)) {
            double y = height * 0.618;
            newWidth = width;
            newHeight = (int) (width * (370.0 / 640.0));
            rectangle = new Rectangle(0, (int) ((height - y) - newHeight / 2), newWidth, newHeight);
        } else if ((0.8 < division(width, height)) && (division(width, height) < 1.72973)) {
            // double y = height * 0.5;
            // newHeight = (int) (width * (370.0 / 640.0));
            newHeight = height;
            newWidth = (int) (newHeight * (640.0 / 370.0));
            if (newWidth > width) {
                newHeight = (int) (width * (370.0 / 640.0));
                newWidth = width;
            }
            rectangle = new Rectangle(newWidth, newHeight);
        } else if (1.72973 <= division(width, height) && division(width, height) <= 2) {
            double x = width * 0.5;
            newWidth = (int) (height * (640.0 / 370.0));
            newHeight = height;
            rectangle = new Rectangle((int) (x - newWidth / 2), 0, newWidth, newHeight);
        } else if (division(width, height) > 2) {
            newWidth = (int) (height * (640.0 / 370.0));
            newHeight = height;
            rectangle = new Rectangle(newWidth, newHeight);
        }
        return rectangle;
    }

    // public static void main(String[] args) throws Exception {
    // File file = new File("F:\\image\\12.jpg");
    // BufferedInputStream data = new BufferedInputStream(new FileInputStream(file));
    // BufferedImage image = ImageIO.read(data);
    // cut(image, "jpg", image.getWidth(), image.getHeight());
    // }

    private static File saveSubImage(BufferedImage image, String format, Rectangle subImageBounds) throws IOException {
        BufferedImage subImage = new BufferedImage(subImageBounds.width, subImageBounds.height, 1);
        Graphics g = subImage.getGraphics();
        g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y, subImageBounds.width, subImageBounds.height),
                0, 0, null);
        g.dispose();
        String fileName = new StringBuffer().append(SystemConfig.getString("CUT_PIC_TEM_DIR")).append(File.separator)
                .append(ToolUtil.getId()).append(".").append(format).toString();
        File destFile = new File(fileName);
        ImageIO.write(subImage, format, destFile);
        return destFile;
    }

    private static int division(int width, int height) {
        return width / height;
    }

}
