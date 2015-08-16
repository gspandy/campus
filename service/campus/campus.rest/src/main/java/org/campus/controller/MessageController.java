package org.campus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.campus.constant.Constant;
import org.campus.core.exception.CampusException;
import org.campus.model.enums.IsNewSession;
import org.campus.model.enums.SessionType;
import org.campus.service.MessageService;
import org.campus.vo.ConversationDetailVO;
import org.campus.vo.ConversationVO;
import org.campus.vo.LoginResponseVO;
import org.campus.vo.MessageAddVO;
import org.campus.vo.MessageRequestVo;
import org.campus.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @ApiOperation(value = "查询信息提示列表:1.0", notes = "查询信息提示列表[API-Version=1.0]")
    @RequestMapping(value = "/lists", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<MessageVO> getLoginUserInfo(
            @ApiParam(name = "type", value = "消息类型(0:系统公告;1:普通用户信息)") @RequestParam(value = "type", required = true) String type,
            @ApiParam(name = "isRead", value = "是否已读(0:未读;1:已读)") @RequestParam(value = "isRead", required = true) String isRead,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // 1.校验用户session信息
        LoginResponseVO vo = checkLogin(session);
        // 2.TODO：校验signId 校验数据库中signId是否一致。
        // 3.查询
        MessageRequestVo params = new MessageRequestVo();
        params.setIsRead(isRead);
        params.setPageable(pageable);
        params.setUserId(vo.getUserId());
        params.setType(type);
        List<MessageVO> messageVOs = messageService.getMessagePromptList(params);
        // List<MessageVO> messageVOs = new ArrayList<MessageVO>();
        // MessageVO messageVO = new MessageVO();
        // messageVO.setConversationId("4565645");
        // messageVO.setMessageId("123123");
        // messageVO.setMessage("测试测试测试测试测试测试测试测试测试测试试测试测试测试试测试测试测试");
        // messageVO.setSendDate(new Date());
        // messageVO.setSendUserId("123321");
        // messageVO.setIsRead(isRead);
        // messageVO.setSendNickName("gh123123");
        // messageVOs.add(messageVO);
        Page<MessageVO> page = new PageImpl<MessageVO>(messageVOs, pageable, messageVOs.size());
        return page;
    }

    @ApiOperation(value = "信息发送:1.0", notes = "信息发送[API-Version=1.0]")
    @RequestMapping(value = "/send/{userId}", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "发送成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void send(
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
            conversationId = messageService.createSession(vo.getUserId(), userId, sessionType, messageAddVO.getMessage());
        }
        
        // 4.发送消息
        messageService.sendSessionMsg(conversationId, userId, vo.getUserId(), messageAddVO, sessionType);
    }

    private void checkParams(String sessionType, String isNewSession, String conversationId) {
        IsNewSession newFlag = IsNewSession.getIsNewSessionByCode(isNewSession);

        if ((null == SessionType.getSessionTypeByCode(sessionType)) || (null == newFlag)
                || (newFlag.getCode().equals(IsNewSession.UNCREATE.getCode()) && StringUtils.isEmpty(conversationId))) {
            throw new CampusException(190004, "参数错误，请检查rest接口参数传递规范");
        }
    }

    @ApiOperation(value = "查询会话列表:1.0", notes = "查询会话列表[API-Version=1.0]")
    @RequestMapping(value = "/conversation/{conversationId}", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "读取成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public ConversationVO getConversation(
            @ApiParam(name = "conversationId", value = "聊天会话ID") @PathVariable String conversationId,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // TODO:待完成
        // 需建立一张聊天会话表，关联两个用户之间的聊天记录
        ConversationVO conversationVO = new ConversationVO();
        conversationVO.setConversationId("123123");
        conversationVO.setReceiveNickName("gt123");
        List<ConversationDetailVO> conversationDetailVOs = new ArrayList<ConversationDetailVO>();
        ConversationDetailVO conversationDetailVO1 = new ConversationDetailVO();
        conversationDetailVO1.setSendMessage("Hi");
        conversationDetailVO1.setSendDate(new Date());
        conversationDetailVO1.setIsRead("1");
        conversationDetailVO1.setReadDate(new Date());
        conversationDetailVOs.add(conversationDetailVO1);
        ConversationDetailVO conversationDetailVO12 = new ConversationDetailVO();
        conversationDetailVO12.setReceiveMessage("Hello");
        conversationDetailVO12.setSendDate(new Date());
        conversationDetailVO12.setIsRead("1");
        conversationDetailVO12.setReadDate(new Date());
        conversationDetailVOs.add(conversationDetailVO12);
        Page<ConversationDetailVO> page = new PageImpl<ConversationDetailVO>(conversationDetailVOs, pageable,
                conversationDetailVOs.size());
        conversationVO.setPage(page);
        return conversationVO;
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
