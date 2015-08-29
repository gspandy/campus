package org.campus.media.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;

public class ImgscalrImageTransform implements ImageTransform {

    @Override
    public Image resize(Image image, int width, int height) throws ImageTransformException {
        BufferedImage srcImage = null;
        try {
            srcImage = ImageIO.read(image.getInputStream());
        } catch (IOException e) {
            throw new ImageTransformException("读取图片发生IO错误", e);
        }

        BufferedImage destImage = Scalr.resize(srcImage, Method.QUALITY, Mode.FIT_EXACT, width, height);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(destImage, image.getFormatName(), out);
        } catch (IOException e) {
            throw new ImageTransformException("生成目标图片发生IO错误", e);
        }

        return new Image(new ByteArrayInputStream(out.toByteArray()), image.getFormatName());
    }
}
