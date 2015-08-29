package org.campus.util;

import org.campus.core.web.Attachment;

public class ImageUtils {

    public static int newWidth = 0;// 新图的宽

    public static int newHeight = 0;// 新图的高

    public static void incision(int width, int height) {
        if (division(width, height) < 0.5) {
            newWidth = width;
            newHeight = (int) (height * (370.0 / 640.0));
        } else if ((0.5 <= division(width, height)) && (division(width, height) <= 0.8)) {
            newWidth = width;
            newHeight = (int) (width * (370.0 / 640.0));
        } else if ((0.8 < division(width, height)) && (division(width, height) < 1.72973)) {
            newWidth = width;
            newHeight = (int) (width * (370.0 / 640.0));
        } else if (1.72973 <= division(width, height) && division(width, height) <= 2) {
            newHeight = height;
            newWidth = (int) (height * (640.0 / 370.0));
        } else if (division(width, height) > 2) {
            newHeight = height;
            newWidth = (int) (height * (640.0 / 370.0));
        }
    }

    public static Attachment getAttachment(String fileId, byte[] data) {
        Attachment attachment = new Attachment();
        attachment.setContent(data);
        attachment.setOriginalFilename(fileId + "_" + newWidth + "_" + newHeight);
        attachment.setContentType("multipart/form-data");
        return attachment;
    }

    private static int division(int width, int height) {
        return width / height;
    }

}
