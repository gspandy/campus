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

import org.campus.vo.FileUploadVO;
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

    @ApiOperation(value = "图片上传", notes = "图片上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "请求成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public FileUploadVO uploadidentityCardPicture(
            @ApiParam(name = "multipartFile", value = "图片", required = true) @RequestParam("multipartFile") MultipartFile multipartFile) {
        FileUploadVO fileUploadVO = new FileUploadVO();
        fileUploadVO.setPicName("测试");
        fileUploadVO.setPicFullUrl("http://cdn.duitang.com/uploads/item/201502/25/20150225172743_x2hfW.jpeg");
        return fileUploadVO;

    }

}
