package org.campus.csource;

import java.io.File;
import java.io.IOException;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.campus.core.exception.FastdfsIOException;
import org.campus.util.ToolUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtil {

    public static File transToFile(MultipartFile multipartFile) {
        CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        String path = file.getPath();
        path = path.substring(0, path.lastIndexOf(File.separator));
        StringBuilder pathBuilder = new StringBuilder(path);
        pathBuilder.append(File.separator).append(ToolUtil.getId()).append("_")
                .append(multipartFile.getOriginalFilename());
        path = pathBuilder.toString();
        file = new File(path);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            throw new FastdfsIOException(100010, "系统异常");
        }

        return file;
    }

}
