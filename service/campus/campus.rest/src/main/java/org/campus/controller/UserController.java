package org.campus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.campus.annotation.NeedRoles;
import org.campus.constant.Constant;
import org.campus.model.Comment;
import org.campus.model.FreshNews;
import org.campus.model.User;
import org.campus.model.enums.DisplayModel;
import org.campus.model.enums.InteractType;
import org.campus.service.NickNameService;
import org.campus.service.UserService;
import org.campus.util.CollectionUtils;
import org.campus.util.ToolUtil;
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

    @Autowired
    private NickNameService nickNameService;

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
    @NeedRoles
    public UserVO getUserInfo(
            @ApiParam(name = "userId", value = "用户Id") @PathVariable String userId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        UserVO user = findUserInfo(userId);
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        user.setAttention(userService.isAttention(responseVO.getUserId(), userId));
        return user;
    }

    @ApiOperation(value = "*查询登录用户相册:1.0", notes = "查询登录用户相册[API-Version=1.0]")
    @RequestMapping(value = "/photos", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
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
    @NeedRoles
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
    @NeedRoles
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
            @ApiParam(name = "type", value = "赞/踩(0:踩,1:赞)") @RequestParam(value = "type", required = true) InteractType type,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO responseVO = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        String userName = nickNameService.findRandomNickName(model, session.getId());
        userName = userName == null ? responseVO.getNickName() : userName;
        userService.commentSupport(commentId, responseVO.getUserId(), userName, type);
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
            userPhotosVO.setBrief(freshNews.getNewsbrief());
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

    // private String getNickName(DisplayModel model, LoginResponseVO responseVO) {
    // String userName = responseVO.getNickName();
    // if (DisplayModel.MOON.equals(model)) {
    // NickName nickName = nickNameService.findRandomNickName();
    // userName = nickName.getNickname();
    // }
    // return userName;
    // }

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
            friendVO.setInitial(user.getNamefirstletter());
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
