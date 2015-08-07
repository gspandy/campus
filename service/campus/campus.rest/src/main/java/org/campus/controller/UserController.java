package org.campus.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.campus.vo.CountVO;
import org.campus.vo.UserPhotosVO;
import org.campus.vo.UserVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api(value = "UserController", description = "用户信息相关操作")
public class UserController {

    @ApiOperation(value = "登录用户信息查询", notes = "登录用户信息查询")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public UserVO getLoginUserInfo(
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        UserVO userVO = new UserVO();
        userVO.setUserId("gr921113");
        userVO.setNickName("edcee3000");
        return userVO;
    }

    @ApiOperation(value = "其他用户的信息查询", notes = "其他用户的信息查询")
    @RequestMapping(value = "/{userId}/info", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public UserVO getUserInfo(
            @ApiParam(name = "userId", value = "用户Id") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        UserVO userVO = new UserVO();
        userVO.setUserId("gr921113");
        userVO.setNickName("edcee3000");
        return userVO;
    }

    @ApiOperation(value = "查询登录用户相册", notes = "查询登录用户相册")
    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public UserPhotosVO getUserPhotos(
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        UserPhotosVO photosVO = new UserPhotosVO();
        photosVO.setPhotoId("12312312");
        photosVO.setNickName("edcee3000");
        photosVO.setPubDate(new Date());
        photosVO.setNote("测试");
        return photosVO;
    }

    @ApiOperation(value = "查询其他用户的相册", notes = "查询其他用户的相册")
    @RequestMapping(value = "/{userId}/photos", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public UserPhotosVO getUserPhotos(
            @ApiParam(name = "userId", value = "用户Id") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        UserPhotosVO photosVO = new UserPhotosVO();
        photosVO.setPhotoId("12312312");
        photosVO.setNickName("edcee3000");
        photosVO.setPubDate(new Date());
        photosVO.setNote("测试");
        return photosVO;
    }

    @ApiOperation(value = "查询帖子、粉丝、关注数", notes = "查询帖子、粉丝、关注数")
    @RequestMapping(value = "/{userId}/count", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public CountVO count(
            @ApiParam(name = "userId", value = "用户Id") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        CountVO countVO = new CountVO();
        countVO.setPostCount(128);
        countVO.setFansCount(256);
        countVO.setAttentionCount(512);
        return countVO;
    }

}
