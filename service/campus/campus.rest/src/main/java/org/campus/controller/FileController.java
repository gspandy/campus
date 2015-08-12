/*
 * Copyright (C), 2014-2014, 联创车盟汽车服务有限公司
 * FileName: FileController.java
 * Author:   ZGF
 * Date:     2014年9月17日 下午1:20:10
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.campus.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.campus.core.exception.CampusException;
import org.campus.fastdfs.FastdfsClient;
import org.campus.fastdfs.FastdfsClientFactory;
import org.campus.vo.FileUploadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("file")
@Api(value = "FileController", description = "文件上传相关操作")
public class FileController {
    
    @Autowired
    private FastdfsClientFactory fastdfsClientFactory;

    @SuppressWarnings("null")
    @ApiOperation(value = "图片上传:1.0", notes = "图片上传:1.0")
    @RequestMapping(value = "/upload", method = RequestMethod.POST, headers = { "API-Version=1.0" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "请求成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public FileUploadVO uploadidentityCardPicture(
            @ApiParam(name = "multipartFile", value = "图片", required = true) @RequestParam("multipartFile") MultipartFile multipartFile) {
        FastdfsClient fastdfsClient = fastdfsClientFactory.getFastdfsClient();
        File file = null;
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            throw new CampusException(100010,"系统异常");
        }
        Map<String,String> meta = new HashMap<String, String>();
        meta.put("fileName", file.getName());
        String fileId = fastdfsClient.upload(file, file.getName(), meta);
        FileUploadVO fileUploadVO = new FileUploadVO();
        fileUploadVO.setPicName("测试");
        fileUploadVO.setPicFullUrl(fileId);
        return fileUploadVO;
    }
    
    @ApiOperation(value = "图片下载:1.0", notes = "图片下载:1.0")
    @RequestMapping(value = "/downLoad", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "请求成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void downLoadidentityCardPicture(
            @ApiParam(name = "fileId", value = "图片id", required = true) @RequestParam("fileId") String fileId,HttpServletResponse response) {
        FastdfsClient fastdfsClient = fastdfsClientFactory.getFastdfsClient();
        byte[] data = fastdfsClient.downLoad(fileId);
        ServletOutputStream out;
        try {
            out = response.getOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(data);    //将b作为输入流；
            BufferedImage image = ImageIO.read(in);   
            // write the data out
            ImageIO.write(image, "jpg", out);
            try {
                out.flush();
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new CampusException(100010,"系统异常");
        }
         
    }

}
