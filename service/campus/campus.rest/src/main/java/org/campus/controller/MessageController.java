package org.campus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.campus.vo.ConversationDetailVO;
import org.campus.vo.ConversationVO;
import org.campus.vo.MessageAddVO;
import org.campus.vo.MessageVO;
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
@RequestMapping("/msg")
@Api(value = "MessageController", description = "消息相关操作")
public class MessageController {

    @ApiOperation(value = "查询信息提示列表", notes = "查询信息提示列表")
    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<MessageVO> getLoginUserInfo(
            @ApiParam(name = "userId", value = "接收方用户ID") @RequestParam(value = "userId", required = false) String userId,
            @ApiParam(name = "type", value = "消息类型(0:系统公告;1:普通用户信息)") @RequestParam(value = "type", required = true) String type,
            @ApiParam(name = "isRead", value = "是否已读(0:未读;1:已读)") @RequestParam(value = "isRead", required = true) String isRead,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        List<MessageVO> messageVOs = new ArrayList<MessageVO>();
        MessageVO messageVO = new MessageVO();
        messageVO.setConversationId("4565645");
        messageVO.setMessageId("123123");
        messageVO.setMessage("测试测试测试测试测试测试测试测试测试测试试测试测试测试试测试测试测试");
        messageVO.setSendDate(new Date());
        messageVO.setSendUserId("123321");
        messageVO.setIsRead(isRead);
        messageVO.setSendNickName("gh123123");
        messageVOs.add(messageVO);
        Page<MessageVO> page = new PageImpl<MessageVO>(messageVOs, pageable, messageVOs.size());
        return page;
    }

    @ApiOperation(value = "信息发送", notes = "信息发送")
    @RequestMapping(value = "/send/{userId}", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "发送成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void send(
            @ApiParam(name = "userId", value = "接收方用户ID") @PathVariable String userId,
            @ApiParam(name = "messageAddVO", value = "消息发送体") @RequestBody MessageAddVO messageAddVO,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {

    }

    @ApiOperation(value = "信息读取", notes = "信息读取")
    @RequestMapping(value = "/read/{messageId}", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "读取成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void read(
            @ApiParam(name = "messageId", value = "消息ID") @PathVariable String messageId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {

    }

    @ApiOperation(value = "查询会话列表", notes = "查询会话列表")
    @RequestMapping(value = "/conversation/{conversationId}", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "读取成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public ConversationVO getConversation(
            @ApiParam(name = "conversationId", value = "聊天会话ID") @PathVariable String conversationId,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
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
}
