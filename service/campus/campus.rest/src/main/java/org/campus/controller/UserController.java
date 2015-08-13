package org.campus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.campus.constant.Constant;
import org.campus.model.Comment;
import org.campus.model.FreshNews;
import org.campus.model.User;
import org.campus.model.enums.DisplayModel;
import org.campus.model.enums.InteractType;
import org.campus.service.UserService;
import org.campus.vo.BoardVO;
import org.campus.vo.CommentAddVO;
import org.campus.vo.CommentVO;
import org.campus.vo.FriendVO;
import org.campus.vo.LoginResponseVO;
import org.campus.vo.MyCommentVO;
import org.campus.vo.UserPhotosVO;
import org.campus.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @ApiOperation(value = "*登录用户信息查询:1.0", notes = "登录用户信息查询[API-Version=1.0]")
    @RequestMapping(value = "/info", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public UserVO getLoginUserInfo(
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        return findUserInfo(responseVO.getUserId());
    }

    @ApiOperation(value = "*其他用户的信息查询:1.0", notes = "其他用户的信息查询[API-Version=1.0]")
    @RequestMapping(value = "/{userId}/info", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public UserVO getUserInfo(
            @ApiParam(name = "userId", value = "用户Id") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        return findUserInfo(userId);
    }

    @ApiOperation(value = "*查询登录用户相册:1.0", notes = "查询登录用户相册[API-Version=1.0]")
    @RequestMapping(value = "/photos", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<UserPhotosVO> getUserPhotos(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        Page<UserPhotosVO> page = findUserPhotos(pageable, responseVO.getUserId(), responseVO.getNickName());
        return page;
    }

    @ApiOperation(value = "*查询其他用户的相册:1.0", notes = "查询其他用户的相册[API-Version=1.0]")
    @RequestMapping(value = "/{userId}/photos", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<UserPhotosVO> getUserPhotos(
            @ApiParam(name = "userId", value = "用户Id") @PathVariable String userId,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        User user = userService.findByUserId(userId);
        Page<UserPhotosVO> page = findUserPhotos(pageable, userId, user.getNickname());
        return page;
    }

    @ApiOperation(value = "*查询相册评论:1.0", notes = "查询相册评论[API-Version=1.0]")
    @RequestMapping(value = "/{photoId}/comments", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<CommentVO> getPhotoComments(
            @ApiParam(name = "photoId", value = "相册ID") @PathVariable String photoId,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        Page<Comment> comments = userService.findComments(photoId, pageable);
        List<CommentVO> commentVOs = new ArrayList<CommentVO>();
        if (comments == null || comments.getContent().size() == 0) {
            return new PageImpl<CommentVO>(commentVOs, pageable, commentVOs.size());
        }
        CommentVO commentVO = null;
        for (Comment comment : comments.getContent()) {
            commentVO = new CommentVO();
            commentVO.setUserId(comment.getComuseruid());
            commentVO.setNickName(comment.getUsernickname());
            commentVO.setObjUserId(comment.getObjuseruid());
            commentVO.setObjNickName(comment.getObjusernickname());
            commentVO.setCommentDate(comment.getCreatedate());
            commentVO.setCommentContent(comment.getCommentcontent());
            int supportNum = userService.getUserCommentSupport(comment.getUid(), comment.getComuseruid());
            commentVO.setSupportNum(supportNum);
            commentVOs.add(commentVO);
        }
        Page<CommentVO> page = new PageImpl<CommentVO>(commentVOs, pageable, commentVOs.size());
        return page;
    }

    @ApiOperation(value = "相册点赞/踩:1.0", notes = "相册点赞[API-Version=1.0]")
    @RequestMapping(value = "/photo/{photoId}/interact", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "点赞成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void postSupport(
            @ApiParam(name = "photoId", value = "相册ID") @PathVariable String photoId,
            @ApiParam(name = "type", value = "赞/踩(0:踩,1:赞)") @RequestParam(value = "type", required = true) InteractType type,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        
    }

    @ApiOperation(value = "相册评论点赞:1.0", notes = "相册评论点赞[API-Version=1.0]")
    @RequestMapping(value = "/comment/{commentId}/interact", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "点赞成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void commentSupport(
            @ApiParam(name = "commentId", value = "帖子评论的ID") @PathVariable String commentId,
            @ApiParam(name = "type", value = "赞/踩(0:踩,1:赞)") @RequestParam(value = "type", required = true) InteractType type,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // TODO:待完成
    }

    @ApiOperation(value = "相册评论:1.0", notes = "相册评论[API-Version=1.0]")
    @RequestMapping(value = "/{photoId}/comment", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "评论成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void addComment(
            @ApiParam(name = "photoId", value = "相册ID") @PathVariable String photoId,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "commentAddVO", value = "评论体信息") @RequestBody CommentAddVO commentAddVO,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {

        // TODO:待完成
    }

    @ApiOperation(value = "添加关注:1.0", notes = "添加关注[API-Version=1.0]")
    @RequestMapping(value = "/attention/{userId}", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "添加关注成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void attention(
            @ApiParam(name = "userId", value = "用户ID") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {

        // TODO:待完成
    }

    @ApiOperation(value = "取消关注:1.0", notes = "取消关注[API-Version=1.0]")
    @RequestMapping(value = "/remove/{userId}/attention", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "取消关注成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void removeAttention(
            @ApiParam(name = "userId", value = "用户ID") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {

    }

    @ApiOperation(value = "查询好友列表:1.0", notes = "查询好友列表[API-Version=1.0]")
    @RequestMapping(value = "/friends", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询好友成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public List<FriendVO> getFriends(
            @ApiParam(name = "nickName", value = "昵称，可模糊查询") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // TODO:待完成
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

    @ApiOperation(value = "查询粉丝列表:1.0", notes = "查询粉丝列表[API-Version=1.0]")
    @RequestMapping(value = "/fans", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询粉丝成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public List<FriendVO> getFans(
            @ApiParam(name = "nickName", value = "昵称，可模糊查询") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // TODO:待完成
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

    @ApiOperation(value = "查询我的评论:1.0", notes = "查询我的评论[API-Version=1.0]")
    @RequestMapping(value = "/mycomments", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<MyCommentVO> getMycomments(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // TODO:待完成
        List<MyCommentVO> myCommentVOs = new ArrayList<MyCommentVO>();
        MyCommentVO commentVO1 = new MyCommentVO();
        List<CommentVO> list = new ArrayList<CommentVO>();
        CommentVO vo = new CommentVO();
        vo.setCommentId("1231");
        vo.setUserId("4323");
        vo.setNickName("ad123123");
        vo.setCommentDate(new Date());
        vo.setCommentContent("测试1");
        vo.setSupportNum(99);
        list.add(vo);
        commentVO1.setCommentVOs(list);
        myCommentVOs.add(commentVO1);
        commentVO1.setPostId("123");
        commentVO1.setContent("测试1");
        myCommentVOs.add(commentVO1);
        MyCommentVO commentVO2 = new MyCommentVO();
        List<CommentVO> list1 = new ArrayList<CommentVO>();
        CommentVO vo1 = new CommentVO();
        vo1.setCommentId("1231");
        vo1.setUserId("4323");
        vo1.setNickName("ad123123");
        vo1.setCommentDate(new Date());
        vo1.setCommentContent("测试1");
        vo1.setSupportNum(99);
        list1.add(vo1);
        commentVO2.setCommentVOs(list1);
        myCommentVOs.add(commentVO2);
        commentVO2.setPostId("124");
        commentVO2.setContent("测试2");
        myCommentVOs.add(commentVO2);
        Page<MyCommentVO> page = new PageImpl<MyCommentVO>(myCommentVOs, pageable, myCommentVOs.size());
        return page;
    }

    @ApiOperation(value = "查询我点过的赞:1.0", notes = "查询我点过的赞[API-Version=1.0]")
    @RequestMapping(value = "/mysupports", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<BoardVO> getMysupports(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // TODO:待完成
        List<BoardVO> boardVOs = new ArrayList<BoardVO>();
        BoardVO boardVO1 = new BoardVO();
        boardVO1.setPostsId("2123123");
        boardVO1.setUserId("123231");
        boardVO1.setNickName("ec00000");
        List<String> picUrls1 = new ArrayList<String>();
        picUrls1.add("http://cdn.duitang.com/uploads/item/201502/25/20150225172743_x2hfW.jpeg");
        boardVO1.setPicUrls(picUrls1);
        boardVO1.setContent("测试1");
        boardVO1.setPublishDate(new Date());
        boardVOs.add(boardVO1);
        BoardVO boardVO2 = new BoardVO();
        boardVO2.setPostsId("2123124");
        boardVO2.setUserId("123232");
        boardVO2.setNickName("ec00001");
        List<String> picUrls2 = new ArrayList<String>();
        picUrls2.add("http://cdn.duitang.com/uploads/item/201502/25/20150225172743_x2hfW.jpeg");
        boardVO2.setPicUrls(picUrls2);
        boardVO2.setContent("测试2");
        boardVO2.setPublishDate(new Date());
        boardVOs.add(boardVO2);
        Page<BoardVO> page = new PageImpl<BoardVO>(boardVOs, pageable, boardVOs.size());
        return page;
    }

    private UserVO findUserInfo(String userId) {
        User user = userService.findByUserId(userId);
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getUseruid());
        userVO.setNickName(user.getNickname());
        userVO.setPostCount(userService.countPost(userId));
        userVO.setFansCount(userService.countFans(userId));
        userVO.setAttentionCount(userService.countAttention(userId));
        return userVO;
    }

    private Page<UserPhotosVO> findUserPhotos(Pageable pageable, String userId, String nickName) {
        Page<FreshNews> photos = userService.findUserPhotos(userId, pageable);
        List<UserPhotosVO> photosVOs = new ArrayList<UserPhotosVO>();
        if (photos == null || photos.getContent().size() == 0) {
            return new PageImpl<UserPhotosVO>(photosVOs, pageable, photosVOs.size());
        }
        UserPhotosVO userPhotosVO = null;
        for (FreshNews freshNews : photos.getContent()) {
            userPhotosVO = new UserPhotosVO();
            userPhotosVO.setPhotoId(freshNews.getUid());
            userPhotosVO.setNickName(nickName);
            userPhotosVO.setPubDate(freshNews.getCreatedate());
            userPhotosVO.setContent(freshNews.getNewscontent());
            userPhotosVO.setTransNum(freshNews.getTransnum());
            userPhotosVO.setCommentNum(freshNews.getCommentnum());
            userPhotosVO.setSupportNum(freshNews.getSupportnum());
            userPhotosVO.setNotSupportNum(freshNews.getNotsupportnum());
            userPhotosVO.setComplainNum(freshNews.getComplainnum());
            photosVOs.add(userPhotosVO);
        }
        Page<UserPhotosVO> page = new PageImpl<UserPhotosVO>(photosVOs, pageable, photosVOs.size());
        return page;
    }

}
