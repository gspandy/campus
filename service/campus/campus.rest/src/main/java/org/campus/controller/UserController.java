package org.campus.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.campus.annotation.NeedRoles;
import org.campus.constant.Constant;
import org.campus.core.exception.CampusException;
import org.campus.model.Comment;
import org.campus.model.FreshNews;
import org.campus.model.Transfer;
import org.campus.model.User;
import org.campus.model.enums.DisplayModel;
import org.campus.model.enums.InteractType;
import org.campus.service.NickNameService;
import org.campus.service.TopicService;
import org.campus.service.UserService;
import org.campus.util.CollectionUtils;
import org.campus.util.ToolUtil;
import org.campus.vo.BoardVO;
import org.campus.vo.CommentAddVO;
import org.campus.vo.CommentVO;
import org.campus.vo.FriendVO;
import org.campus.vo.LoginResponseVO;
import org.campus.vo.MyCommentVO;
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

    @Autowired
    private NickNameService nickNameService;

    @Autowired
    TopicService topicSvc;

    @ApiOperation(value = "*登录用户信息查询:1.0", notes = "登录用户信息查询[API-Version=1.0]")
    @RequestMapping(value = "/info", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
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
            @ApiParam(name = "nickName", value = "用户昵称") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        UserVO user = findUserInfo(userId);
        if (user == null) {
            throw new CampusException(1000003, "用户不存在");
        }
        if (!StringUtils.isEmpty(nickName)) {
            user.setNickName(nickName);
        }
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        if (responseVO != null && !StringUtils.isEmpty(responseVO.getUserId())) {
            user.setAttention(userService.isAttention(responseVO.getUserId(), userId));
        }
        return user;
    }

    @ApiOperation(value = "*查询登录用户相册:1.0", notes = "查询登录用户相册[API-Version=1.0]")
    @RequestMapping(value = "/photos", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<BoardVO> getUserPhotos(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        Page<BoardVO> page = findUserPhotos(pageable, responseVO.getUserId(), responseVO.getNickName(),
                responseVO.getHeadPic());
        return page;
    }

    @ApiOperation(value = "*查询其他用户的相册:1.0", notes = "查询其他用户的相册[API-Version=1.0]")
    @RequestMapping(value = "/{userId}/photos", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<BoardVO> getUserPhotos(
            @ApiParam(name = "userId", value = "用户Id") @PathVariable String userId,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        User user = userService.findByUserId(userId);
        if (user == null) {
            throw new CampusException(1000003, "用户不存在");
        }
        Page<BoardVO> page = findUserPhotos(pageable, userId, user.getNickname(), user.getHeadpic());
        return page;
    }

    @ApiOperation(value = "*查询相册评论:1.0", notes = "查询相册评论[API-Version=1.0]")
    @RequestMapping(value = "/{photoId}/comments", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<CommentVO> getPhotoComments(
            @ApiParam(name = "photoId", value = "相册ID") @PathVariable String photoId,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        Page<Comment> comments = userService.findComments(photoId, pageable);
        List<CommentVO> commentVOs = new ArrayList<CommentVO>();
        if (comments == null || comments.getContent().size() == 0) {
            return new PageImpl<CommentVO>(commentVOs, pageable, commentVOs.size());
        }
        CommentVO commentVO = null;
        for (Comment comment : comments.getContent()) {
            commentVO = new CommentVO();
            commentVO.setCommentId(comment.getUid());
            commentVO.setUserId(comment.getComuseruid());
            commentVO.setNickName(comment.getUsernickname());
            User user = userService.findByUserId(comment.getComuseruid());
            if (user != null) {
                commentVO.setHeadPic(user.getHeadpic());
            }
            commentVO.setObjUserId(comment.getObjuseruid());
            commentVO.setObjNickName(comment.getObjusernickname());
            User objUser = userService.findByUserId(comment.getObjuseruid());
            if (objUser != null) {
                commentVO.setObjHeadPic(objUser.getHeadpic());
            }
            commentVO.setObjComment(comment.getObjComment());
            commentVO.setCommentDate(comment.getCreatedate());
            commentVO.setCommentContent(comment.getCommentcontent());
            int supportNum = userService.getUserCommentSupport(comment.getUid());
            commentVO.setSupportNum(supportNum);
            if (responseVO != null && !StringUtils.isEmpty(responseVO.getUserId())) {
                commentVO.setSupport(userService.isSupport(commentVO.getCommentId(), responseVO.getUserId()));
            }
            commentVOs.add(commentVO);
        }
        Page<CommentVO> page = new PageImpl<CommentVO>(commentVOs, pageable, commentVOs.size());
        return page;
    }

    @ApiOperation(value = "*相册点赞/踩:1.0", notes = "相册点赞[API-Version=1.0]")
    @RequestMapping(value = "/photo/{photoId}/interact", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "点赞成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void postSupport(
            @ApiParam(name = "photoId", value = "相册ID") @PathVariable String photoId,
            @ApiParam(name = "type", value = "赞/踩(0:踩,1:赞)") @RequestParam(value = "type", required = true) InteractType type,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        String userName = nickNameService.findRandomNickName(model, session.getId());
        userName = userName == null ? responseVO.getNickName() : userName;
        userService.photoSupport(photoId, responseVO.getUserId(), userName, type);
    }

    @ApiOperation(value = "*相册评论点赞:1.0", notes = "相册评论点赞[API-Version=1.0]")
    @RequestMapping(value = "/comment/{commentId}/interact", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "点赞成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void commentSupport(
            @ApiParam(name = "commentId", value = "帖子评论的ID") @PathVariable String commentId,
            @ApiParam(name = "postId", value = "帖子ID") @RequestParam(value = "postId", required = true) String postId,
            @ApiParam(name = "type", value = "赞/踩(0:踩,1:赞)") @RequestParam(value = "type", required = true) InteractType type,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        String userName = nickNameService.findRandomNickName(model, session.getId());
        userName = userName == null ? responseVO.getNickName() : userName;
        userService.commentSupport(commentId, postId, responseVO.getUserId(), userName, type);
    }

    @ApiOperation(value = "*相册评论:1.0", notes = "相册评论[API-Version=1.0]")
    @RequestMapping(value = "/{photoId}/comment", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "评论成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void addComment(
            @ApiParam(name = "photoId", value = "相册ID") @PathVariable String photoId,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "commentAddVO", value = "评论体信息") @RequestBody CommentAddVO commentAddVO,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session, HttpServletRequest request) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        String userName = nickNameService.findRandomNickName(model, session.getId());
        userName = userName == null ? responseVO.getNickName() : userName;
        userService
                .comment(photoId, responseVO.getUserId(), userName, ToolUtil.getIpAddr(request), model, commentAddVO);
    }

    @ApiOperation(value = "*添加关注:1.0", notes = "添加关注[API-Version=1.0]")
    @RequestMapping(value = "/attention/{userId}", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "添加关注成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void attention(
            @ApiParam(name = "userId", value = "用户ID") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        userService.attention(responseVO.getUserId(), userId);
    }

    @ApiOperation(value = "*取消关注:1.0", notes = "取消关注[API-Version=1.0]")
    @RequestMapping(value = "/remove/{userId}/attention", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "取消关注成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void removeAttention(
            @ApiParam(name = "userId", value = "用户ID") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        userService.removeAttention(responseVO.getUserId(), userId);
    }

    @ApiOperation(value = "*查询好友列表:1.0", notes = "查询好友列表[API-Version=1.0]")
    @RequestMapping(value = "/friends", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询好友成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public List<FriendVO> getFriends(
            @ApiParam(name = "nickName", value = "昵称，可模糊查询") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        List<User> users = userService.findMyFriends(responseVO.getUserId(), nickName);
        return getFriendsVOs(users);
    }

    @ApiOperation(value = "*查询指定用户好友列表:1.0", notes = "查询好友列表[API-Version=1.0]")
    @RequestMapping(value = "/{userId}/friends", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询好友成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public List<FriendVO> getFriends(
            @ApiParam(name = "userId", value = "用户ID") @PathVariable String userId,
            @ApiParam(name = "nickName", value = "昵称，可模糊查询") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        List<User> users = userService.findMyFriends(userId, nickName);
        return getFriendsVOs(users);
    }

    @ApiOperation(value = "*查询粉丝列表:1.0", notes = "查询粉丝列表[API-Version=1.0]")
    @RequestMapping(value = "/fans", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询粉丝成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public List<FriendVO> getFans(
            @ApiParam(name = "nickName", value = "昵称，可模糊查询") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        List<User> users = userService.findMyFans(responseVO.getUserId(), nickName);
        return getFriendsVOs(users);
    }

    @ApiOperation(value = "*查询指定用户粉丝列表:1.0", notes = "查询粉丝列表[API-Version=1.0]")
    @RequestMapping(value = "/{userId}/fans", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询粉丝成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public List<FriendVO> getFans(
            @ApiParam(name = "userId", value = "用户ID") @PathVariable String userId,
            @ApiParam(name = "nickName", value = "昵称，可模糊查询") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        List<User> users = userService.findMyFans(userId, nickName);
        return getFriendsVOs(users);
    }

    @ApiOperation(value = "*查询我评论过的帖子:1.0", notes = "查询我评论过的帖子[API-Version=1.0]")
    @RequestMapping(value = "/mycomments", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<MyCommentVO> getMycomments(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        Page<FreshNews> freshPage = userService.findMyCommentPosts(responseVO.getUserId(), pageable);
        List<MyCommentVO> myCommentVOs = new ArrayList<MyCommentVO>();
        if (freshPage == null || CollectionUtils.isEmpty(freshPage.getContent())) {
            return new PageImpl<MyCommentVO>(myCommentVOs, pageable, myCommentVOs.size());
        }

        MyCommentVO commentVO = null;
        for (FreshNews freshNews : freshPage.getContent()) {
            commentVO = new MyCommentVO();
            commentVO.setPostId(freshNews.getUid());
            commentVO.setBrief(freshNews.getNewsbrief());
            commentVO.setContent(freshNews.getNewscontent());
            commentVO.setPostPics(dealPics(freshNews));
            findMyComments(responseVO, commentVO, freshNews);
            myCommentVOs.add(commentVO);
        }
        return new PageImpl<MyCommentVO>(myCommentVOs, pageable, myCommentVOs.size());
    }

    @ApiOperation(value = "*查询我点过的赞:1.0", notes = "查询我点过的赞[API-Version=1.0]")
    @RequestMapping(value = "/mysupports", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<BoardVO> getMysupports(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        Page<FreshNews> freshPage = userService.findMySupportPosts(responseVO.getUserId(), pageable);
        List<BoardVO> boardVOs = new ArrayList<BoardVO>();
        if (freshPage == null || CollectionUtils.isEmpty(freshPage.getContent())) {
            return new PageImpl<BoardVO>(boardVOs, pageable, boardVOs.size());
        }

        BoardVO boardVO = null;
        for (FreshNews freshNews : freshPage.getContent()) {
            boardVO = new BoardVO();
            boardVO.setPostsId(freshNews.getUid());
            boardVO.setUserId(freshNews.getAdduseruid());
            boardVO.setNickName(freshNews.getAddnickname());
            boardVO.setPicUrls(dealPics(freshNews));
            boardVO.setBrief(freshNews.getNewsbrief());
            boardVO.setContent(freshNews.getNewscontent());
            boardVO.setPublishDate(freshNews.getCreatedate());
            boardVOs.add(boardVO);
        }
        return new PageImpl<BoardVO>(boardVOs, pageable, boardVOs.size());
    }

    @ApiOperation(value = "*用户搜索:1.0", notes = "用户搜索[API-Version=1.0]")
    @RequestMapping(value = "/user/search", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<UserVO> searchUser(
            @ApiParam(name = "nickName", value = "昵称，可模糊查询") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        Page<UserVO> user = findUserInfoByNickName(responseVO, nickName, pageable);
        return user;
    }

    @ApiOperation(value = "*回复评论:1.0", notes = "回复评论[API-Version=1.0]")
    @RequestMapping(value = "/{commentId}/reply", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void reply(
            @ApiParam(name = "commentId", value = "评论的ID") @PathVariable String commentId,
            @ApiParam(name = "postId", value = "帖子Id") @RequestParam(value = "postId", required = true) String postId,
            @ApiParam(name = "commentAddVO", value = "评论体信息") @RequestBody CommentAddVO commentAddVO,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session, HttpServletRequest request) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        String userName = nickNameService.findRandomNickName(model, session.getId());
        userName = userName == null ? responseVO.getNickName() : userName;
        userService.reply(commentId, postId, responseVO.getUserId(), userName, ToolUtil.getIpAddr(request),
                commentAddVO);
    }

    @ApiOperation(value = "*取消赞/踩:1.0", notes = "取消赞[API-Version=1.0]")
    @RequestMapping(value = "/cancel/{sourceId}/support", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void cancelSupport(
            @ApiParam(name = "sourceId", value = "需取消赞的帖子Id或评论Id") @PathVariable String sourceId,
            @ApiParam(name = "postId", value = "当取消评论赞或踩时，需传入帖子Id") @RequestParam(value = "postId", required = false) String postId,
            @ApiParam(name = "type", value = "赞/踩(0:踩,1:赞)") @RequestParam(value = "type", required = true) InteractType type,
            @ApiParam(name = "mod", value = "1 帖子; 2 评论") @RequestParam(value = "mod", required = true) String mod,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        userService.cancelSupport(sourceId, postId, type, mod, responseVO.getUserId());
    }

    @ApiOperation(value = "*删评论:1.0", notes = "删评论[API-Version=1.0]")
    @RequestMapping(value = "/{commentId}/delete", headers = { "API-Version=1.0" }, method = RequestMethod.DELETE)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "审核成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void delete(
            @ApiParam(name = "commentId", value = "帖子ID") @PathVariable String commentId,
            @ApiParam(name = "environment", value = "显示模式(0:月亮;1:太阳;)") @RequestParam(value = "environment", required = true) String environment,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO vo = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        userService.delete(commentId, vo.getUserId());
    }

    private UserVO findUserInfo(String userId) {
        User user = userService.findByUserId(userId);
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getUseruid());
        userVO.setNickName(user.getNickname());
        userVO.setPostCount(userService.countPost(userId));
        userVO.setFansCount(userService.countFans(userId));
        userVO.setAttentionCount(userService.countAttention(userId));
        userVO.setHeadPic(user.getHeadpic());
        userVO.setIntegral(user.getIntegral());
        userVO.setSchoolId(user.getSchooluid());
        userVO.setSchoolName(user.getSchoolname());
        userVO.setCollegeId(user.getCollegeuid());
        userVO.setCollegeName(user.getCollegename());
        userVO.setProfessionId(user.getProfessionuid());
        userVO.setProfessionName(user.getProfessionname());
        userVO.setSignName(user.getSignName());
        return userVO;
    }

    private Page<UserVO> findUserInfoByNickName(LoginResponseVO responseVO, String nickName, Pageable pageable) {
        Page<User> users = userService.findByNickName(nickName, pageable);
        List<UserVO> userVOs = new ArrayList<UserVO>();
        if (users == null || CollectionUtils.isEmpty(users.getContent())) {
            return new PageImpl<UserVO>(userVOs, pageable, userVOs.size());
        }
        UserVO userVO = null;
        for (User user : users.getContent()) {
            userVO = new UserVO();
            userVO.setUserId(user.getUseruid());
            userVO.setNickName(user.getNickname());
            userVO.setPostCount(userService.countPost(user.getUseruid()));
            userVO.setFansCount(userService.countFans(user.getUseruid()));
            userVO.setAttentionCount(userService.countAttention(user.getUseruid()));
            userVO.setHeadPic(user.getHeadpic());
            if (responseVO != null && !StringUtils.isEmpty(responseVO.getUserId())) {
                userVO.setAttention(userService.isAttention(responseVO.getUserId(), user.getUseruid()));
            }
            userVOs.add(userVO);
        }

        return new PageImpl<UserVO>(userVOs, pageable, userVOs.size());
    }

    private Page<BoardVO> findUserPhotos(Pageable pageable, String userId, String nickName, String headPic) {
        Page<FreshNews> photos = userService.findUserPhotos(userId, pageable);
        List<BoardVO> photosVOs = new ArrayList<BoardVO>();
        if (photos == null || photos.getContent().size() == 0) {
            return new PageImpl<BoardVO>(photosVOs, pageable, photosVOs.size());
        }
        BoardVO userPhotosVO = null;
        Transfer tranfer = null;
        for (FreshNews freshNews : photos.getContent()) {
            userPhotosVO = new BoardVO();
            userPhotosVO.setPostsId(freshNews.getUid());
            userPhotosVO.setUserId(freshNews.getAdduseruid());
            userPhotosVO.setNickName(freshNews.getAddnickname());
            userPhotosVO.setHeadPic(headPic);
            boolean delete = topicSvc.isDelete(freshNews.getUid());
            if (delete) {
                userPhotosVO.setBrief("");
                userPhotosVO.setContent("");
                userPhotosVO.setPicUrls(new ArrayList<String>());
            } else {
                userPhotosVO.setBrief(freshNews.getNewsbrief());
                userPhotosVO.setContent(freshNews.getNewscontent());
                String[] picUrls = freshNews.getPictures().split(",");
                userPhotosVO.setPicUrls(Arrays.asList(picUrls));
            }
            userPhotosVO.setDeleted(delete);
            userPhotosVO.setPublishDate(freshNews.getCreatedate());
            userPhotosVO.setSupported(topicSvc.isSupported(freshNews.getUid(), userId));
            userPhotosVO.setTransNum(freshNews.getTransnum());
            userPhotosVO.setCommentNum(freshNews.getCommentnum());
            userPhotosVO.setSupportNum(freshNews.getSupportnum());
            userPhotosVO.setNotSupportNum(freshNews.getNotsupportnum());
            userPhotosVO.setComplainNum(freshNews.getComplainnum());
            userPhotosVO.setSourceUserId(freshNews.getCreateby());
            tranfer = topicSvc.findTransfer(freshNews.getUid());
            if (tranfer != null) {
                userPhotosVO.setTransferComment(tranfer.getTransferComment());
            }
            photosVOs.add(userPhotosVO);
        }
        Page<BoardVO> page = new PageImpl<BoardVO>(photosVOs, pageable, photosVOs.size());
        return page;
    }

    private List<FriendVO> getFriendsVOs(List<User> users) {
        List<FriendVO> friendVOs = new ArrayList<FriendVO>();
        if (CollectionUtils.isEmpty(users)) {
            return friendVOs;
        }

        FriendVO friendVO = null;
        for (User user : users) {
            friendVO = new FriendVO();
            friendVO.setUserId(user.getUseruid());
            friendVO.setNickName(user.getNickname());
            friendVO.setInitial(user.getNickFirstLetter());
            friendVO.setHeadUrl(user.getHeadpic());
            friendVOs.add(friendVO);
        }
        return friendVOs;
    }

    private List<String> dealPics(FreshNews freshNews) {
        String pictures = freshNews.getPictures();
        List<String> picList = null;
        if (StringUtils.isNotBlank(pictures)) {
            picList = new ArrayList<String>();
            String[] picArr = pictures.split(",");
            for (int i = 0; i < picArr.length; i++) {
                picList.add(picArr[i]);
            }
        }
        return picList;
    }

    private void findMyComments(LoginResponseVO responseVO, MyCommentVO commentVO, FreshNews freshNews) {
        List<Comment> comments = userService.findMyComments(freshNews.getUid(), responseVO.getUserId());
        List<CommentVO> commentVOs = new ArrayList<CommentVO>();
        CommentVO vo = null;
        for (Comment comment : comments) {
            vo = new CommentVO();
            vo.setCommentId(comment.getUid());
            vo.setUserId(comment.getComuseruid());
            vo.setNickName(comment.getUsernickname());
            vo.setObjUserId(comment.getObjuseruid());
            vo.setObjNickName(comment.getObjusernickname());
            vo.setCommentContent(comment.getCommentcontent());
            vo.setCommentDate(comment.getCreatedate());
            vo.setSupportNum(userService.countMyCommentSupport(comment.getUid()));
        }
        commentVO.setCommentVOs(commentVOs);
    }

}
