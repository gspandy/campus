package org.campus.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.campus.annotation.NeedRoles;
import org.campus.constant.Constant;
import org.campus.core.exception.CampusException;
import org.campus.model.enums.IsNewSession;
import org.campus.model.enums.SessionType;
import org.campus.service.MessageService;
import org.campus.service.UserService;
import org.campus.vo.CommentMyCommentVO;
import org.campus.vo.CommentPostsMsgVO;
import org.campus.vo.ConversationDetailVO;
import org.campus.vo.LoginResponseVO;
import org.campus.vo.MessageAddVO;
import org.campus.vo.MessageRequestVo;
import org.campus.vo.MessageVO;
import org.campus.vo.SupportCommentMsgVO;
import org.campus.vo.SupportMsgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
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
@RequestMapping("/msg")
@Api(value = "MessageController", description = "消息相关操作")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "*查询信息提示列表:1.0", notes = "查询信息提示列表[API-Version=1.0]")
    @RequestMapping(value = "/lists", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<MessageVO> getLoginUserInfo(
            @ApiParam(name = "isRead", value = "是否已读(0:未读;1:已读)") @RequestParam(value = "isRead", required = true) String isRead,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // 1.校验用户session信息
        LoginResponseVO vo = checkLogin(session);
        // 2.查询
        MessageRequestVo params = new MessageRequestVo();
        params.setIsRead(isRead);
        params.setPageable(pageable);
        params.setUserId(vo.getUserId());
        List<MessageVO> messageVOs = messageService.getMessagePromptList(params);
        Page<MessageVO> page = new PageImpl<MessageVO>(messageVOs, pageable, messageVOs.size());
        return page;
    }

    @ApiOperation(value = "*信息发送:1.0", notes = "信息发送[API-Version=1.0]")
    @RequestMapping(value = "/send/{userId}", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "发送成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Map<String, String> send(
            @ApiParam(name = "userId", value = "接收方用户ID") @PathVariable String userId,
            @ApiParam(name = "messageAddVO", value = "消息发送体") @RequestBody MessageAddVO messageAddVO,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            @ApiParam(name = "isNewSession", value = "创建会话标识0:创建会话,1:已有会话") @RequestParam(value = "isNewSession") String isNewSession,
            @ApiParam(name = "conversationId", value = "会话id,isNewSession为1时，必传") @RequestParam(value = "conversationId", required = false) String conversationId,
            @ApiParam(name = "sessionType", value = "会话类型代码,1单聊 2 群聊") @RequestParam(value = "sessionType") String sessionType,
            HttpSession session) {
        // 1.校验用户session信息
        LoginResponseVO vo = checkLogin(session);
        // 2.校验sessionType,isNewSession，conversationId
        checkParams(sessionType, isNewSession, conversationId);

        // 3.创建会话
        if (isNewSession.equals(IsNewSession.CREATE.getCode())) {
            conversationId = messageService.createSession(vo.getUserId(), userId, sessionType,
                    messageAddVO.getMessage());
        }

        // 4.发送消息
        messageService.sendSessionMsg(conversationId, userId, vo.getUserId(), messageAddVO, sessionType);
        Map<String, String> converstionMap = new HashMap<String, String>(1);
        converstionMap.put("conversationId", conversationId);
        return converstionMap;
    }

    private void checkParams(String sessionType, String isNewSession, String conversationId) {
        IsNewSession newFlag = IsNewSession.getIsNewSessionByCode(isNewSession);

        if ((null == SessionType.getSessionTypeByCode(sessionType)) || (null == newFlag)
                || (newFlag.getCode().equals(IsNewSession.UNCREATE.getCode()) && StringUtils.isEmpty(conversationId))) {
            throw new CampusException(190004, "参数错误，请检查rest接口参数传递规范");
        }
    }

    @ApiOperation(value = "*查询会话列表:1.0", notes = "查询会话列表[API-Version=1.0]")
    @RequestMapping(value = "/conversation/{conversationId}", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "读取成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<ConversationDetailVO> getConversation(
            @ApiParam(name = "conversationId", value = "聊天会话ID") @PathVariable String conversationId,
            @ApiParam(name = "slide", value = "1:上划;2:下划") @RequestParam(value = "slide", required = false) String slide,
            @ApiParam(name = "lastMsgDate", value = "上划传最新一条信息的时间,下划传显示最早的时间,格式：yyyy/MM/dd HH/mm/ss") @RequestParam(value = "lastMsgDate", required = false) @DateTimeFormat(pattern = "yyyy/MM/dd HH/mm/ss") Date lastMsgDate,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // 1.校验用户session信息
        LoginResponseVO vo = checkLogin(session);
        // 查询会话详细信息
        List<ConversationDetailVO> detailList = messageService.queryConversationList(vo.getUserId(), conversationId);
        // 需建立一张聊天会话表，关联两个用户之间的聊天记录
        Page<ConversationDetailVO> page = new PageImpl<ConversationDetailVO>(detailList, pageable, detailList.size());
        return page;
    }

    @ApiOperation(value = "*获取会话id:1.0", notes = "根据会话对象获取会话id[API-Version=1.0]")
    @RequestMapping(value = "/getConId/{objUserId}", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "读取成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Map<String, String> getConversationId(
            @ApiParam(name = "objUserId", value = "会话对象用户ID") @PathVariable String objUserId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // 1.校验用户session信息
        LoginResponseVO vo = checkLogin(session);
        String conversationId = messageService.getConversationId(vo.getUserId(), objUserId);

        Map<String, String> resultMap = new HashMap<String, String>(1);
        resultMap.put("conversationId", conversationId);
        return resultMap;
    }

    @ApiOperation(value = "*查询赞我的帖子提示:1.0", notes = "查询赞我的帖子提示[API-Version=1.0]")
    @RequestMapping(value = "/support/posts", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<SupportMsgVO> getSupportPostMsg(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO vo = checkLogin(session);
        return userService.findSupportPostMsgVO(vo.getUserId(), pageable);
    }

    @ApiOperation(value = "*查询赞我的评论提示:1.0", notes = "查询赞我的评论提示[API-Version=1.0]")
    @RequestMapping(value = "/support/comments", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<SupportCommentMsgVO> getSupportCommentMsg(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO vo = checkLogin(session);
        return userService.findSupportCommentMsgVO(vo.getUserId(), pageable);
    }

    @ApiOperation(value = "*查询评论我的帖子提示:1.0", notes = "查询评论我的帖子提示[API-Version=1.0]")
    @RequestMapping(value = "/comment/posts", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<CommentPostsMsgVO> getCommentPostMsg(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO vo = checkLogin(session);
        return userService.findCommentPostsMsgVO(vo.getUserId(), pageable);
    }

    @ApiOperation(value = "*查询评论我的评论提示:1.0", notes = "查询评论我的评论提示[API-Version=1.0]")
    @RequestMapping(value = "/comment/mycomment", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<CommentMyCommentVO> getCommentMyCommentMsg(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        LoginResponseVO vo = checkLogin(session);
        return userService.findCommentMyCommentMsgVO(vo.getUserId(), pageable);
    }

    private LoginResponseVO checkLogin(HttpSession session) {
        LoginResponseVO vo = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        if (vo == null) {
            throw new CampusException(100007, "请登录.");
        }
        if (!StringUtils.hasText(vo.getUserId())) {
            throw new CampusException(190001, "系统异常，用户id丢失");
        }
        return vo;
    }
}
