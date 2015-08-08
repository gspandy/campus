package org.campus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.campus.vo.CommentAddVO;
import org.campus.vo.CommentVO;
import org.campus.vo.FriendVO;
import org.campus.vo.UserPhotosVO;
import org.campus.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
        userVO.setPostCount(128);
        userVO.setFansCount(256);
        userVO.setAttentionCount(512);
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
    public Page<UserPhotosVO> getUserPhotos(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        List<UserPhotosVO> photosVOs = new ArrayList<UserPhotosVO>();
        UserPhotosVO photosVO = new UserPhotosVO();
        photosVO.setPhotoId("12312312");
        photosVO.setNickName("edcee3000");
        photosVO.setPubDate(new Date());
        photosVO.setContent("测试");
        photosVO.setTransNum(128);
        photosVO.setCommentNum(2);
        photosVO.setSupportNum(99);
        Page<UserPhotosVO> page = new PageImpl<UserPhotosVO>(photosVOs, pageable, photosVOs.size());
        return page;
    }

    @ApiOperation(value = "查询其他用户的相册", notes = "查询其他用户的相册")
    @RequestMapping(value = "/{userId}/photos", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<UserPhotosVO> getUserPhotos(
            @ApiParam(name = "userId", value = "用户Id") @PathVariable String userId,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        List<UserPhotosVO> photosVOs = new ArrayList<UserPhotosVO>();
        UserPhotosVO photosVO = new UserPhotosVO();
        photosVO.setPhotoId("12312312");
        photosVO.setNickName("edcee3000");
        photosVO.setPubDate(new Date());
        photosVO.setContent("测试");
        photosVO.setTransNum(128);
        photosVO.setCommentNum(2);
        photosVO.setSupportNum(99);
        Page<UserPhotosVO> page = new PageImpl<UserPhotosVO>(photosVOs, pageable, photosVOs.size());
        return page;
    }

    @ApiOperation(value = "查询相册评论", notes = "查询相册评论")
    @RequestMapping(value = "/{photoId}/comments", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<CommentVO> getPhotoComments(
            @ApiParam(name = "photoId", value = "相册ID") @PathVariable String photoId,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        List<CommentVO> commentVOs = new ArrayList<CommentVO>();
        CommentVO commentVO1 = new CommentVO();
        commentVO1.setCommentId("1231");
        commentVO1.setUserId("4323");
        commentVO1.setNickName("ad123123");
        commentVO1.setCommentDate(new Date());
        commentVO1.setCommentContent("测试1");
        commentVO1.setSupportNum(99);
        commentVOs.add(commentVO1);
        CommentVO commentVO2 = new CommentVO();
        commentVO2.setCommentId("1231");
        commentVO2.setUserId("4323");
        commentVO2.setNickName("ad123123");
        commentVO2.setCommentDate(new Date());
        commentVO2.setCommentContent("测试1");
        commentVO2.setSupportNum(99);
        commentVOs.add(commentVO2);
        Page<CommentVO> page = new PageImpl<CommentVO>(commentVOs, pageable, commentVOs.size());
        return page;
    }

    @ApiOperation(value = "相册点赞", notes = "相册点赞")
    @RequestMapping(value = "/{photoId}/support", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "点赞成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void postSupport(
            @ApiParam(name = "photoId", value = "相册ID") @PathVariable String photoId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {

    }

    @ApiOperation(value = "相册评论点赞", notes = "相册评论点赞")
    @RequestMapping(value = "/{commentId}/support", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "点赞成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void commentSupport(
            @ApiParam(name = "commentId", value = "帖子评论的ID") @PathVariable String commentId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {

    }

    @ApiOperation(value = "相册评论", notes = "相册评论")
    @RequestMapping(value = "/{photoId}/comment", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "评论成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void addComment(
            @ApiParam(name = "photoId", value = "相册ID") @PathVariable String photoId,
            @ApiParam(name = "commentAddVO", value = "评论体信息") @RequestBody CommentAddVO commentAddVO,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {

    }

    @ApiOperation(value = "添加关注", notes = "添加关注")
    @RequestMapping(value = "/attention/{userId}", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "添加关注成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void attention(
            @ApiParam(name = "userId", value = "用户ID") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {

    }

    @ApiOperation(value = "取消关注", notes = "取消关注")
    @RequestMapping(value = "/remove/{userId}/attention", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "取消关注成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void removeAttention(
            @ApiParam(name = "userId", value = "用户ID") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {

    }

    @ApiOperation(value = "查询好友列表", notes = "查询好友列表")
    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询好友成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public List<FriendVO> getFriends(
            @ApiParam(name = "nickName", value = "昵称，可模糊查询") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        List<FriendVO> friendVOs = new ArrayList<FriendVO>();
        FriendVO friendVO1 = new FriendVO();
        friendVO1.setUserId("123123");
        friendVO1.setNickName("asd123");
        friendVO1.setInitial("A");
        friendVO1.setHeadUrl("http://cdn.duitang.com/uploads/item/201502/25/20150225172743_x2hfW.jpeg");
        friendVOs.add(friendVO1);
        FriendVO friendVO2 = new FriendVO();
        friendVO2.setUserId("123322");
        friendVO2.setNickName("Dsd123");
        friendVO2.setInitial("D");
        friendVO2.setSignature("测试");
        friendVO2.setHeadUrl("http://cdn.duitang.com/uploads/item/201502/25/20150225172743_x2hfW.jpeg");
        friendVOs.add(friendVO2);
        return friendVOs;
    }

    @ApiOperation(value = "查询粉丝列表", notes = "查询粉丝列表")
    @RequestMapping(value = "/fans", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询粉丝成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public List<FriendVO> getFans(
            @ApiParam(name = "nickName", value = "昵称，可模糊查询") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        List<FriendVO> friendVOs = new ArrayList<FriendVO>();
        FriendVO friendVO1 = new FriendVO();
        friendVO1.setUserId("123123");
        friendVO1.setNickName("asd123");
        friendVO1.setInitial("A");
        friendVO1.setHeadUrl("http://cdn.duitang.com/uploads/item/201502/25/20150225172743_x2hfW.jpeg");
        friendVOs.add(friendVO1);
        FriendVO friendVO2 = new FriendVO();
        friendVO2.setUserId("123322");
        friendVO2.setNickName("Dsd123");
        friendVO2.setInitial("D");
        friendVO2.setSignature("测试");
        friendVO2.setHeadUrl("http://cdn.duitang.com/uploads/item/201502/25/20150225172743_x2hfW.jpeg");
        friendVOs.add(friendVO2);
        return friendVOs;
    }

}
