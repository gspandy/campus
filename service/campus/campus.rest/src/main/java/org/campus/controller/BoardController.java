package org.campus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.campus.constant.Constant;
import org.campus.core.exception.CampusException;
import org.campus.model.enums.DisplayModel;
import org.campus.model.enums.TopicType;
import org.campus.vo.BoardDetailVO;
import org.campus.vo.BoardPublishVO;
import org.campus.vo.BoardVO;
import org.campus.vo.CommentAddVO;
import org.campus.vo.CommentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
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
@RequestMapping("/board")
@Api(value = "BoardController", description = "板块相关操作")
public class BoardController {

    @ApiOperation(value = "帖子列表查询:1.0", notes = "帖子列表查询[API-Version=1.0]")
    @RequestMapping(value = "/posts", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<BoardVO> findBoard(
            @ApiParam(name = "type", value = "1:休闲;2:新鲜;3:秘密;4:言论;5:热门;6:关注") @RequestParam(value = "type", required = true) String type,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        Assert.notNull(type,"帖子类型不能为空.");
    	TopicType topicType = TopicType.getTypeCodeByCode(type);
    	if(topicType == null){
    		throw new CampusException(100201,"帖子类型不存在:"+type);
    	}
    	
    	if(session.getAttribute(Constant.CAMPUS_SECURITY_SESSION) == null){
    		//未登录用户查询
    	}
    	
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

    @ApiOperation(value = "帖子详情查询:1.0", notes = "帖子详情查询[API-Version=1.0]")
    @RequestMapping(value = "/{postsId}/detail", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public BoardDetailVO findBoardDetail(
            @ApiParam(name = "postsId", value = "帖子的ID") @PathVariable String postsId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        // TODO:待完成
        BoardDetailVO boardDetailVO = new BoardDetailVO();
        boardDetailVO.setPostsId("123123");
        boardDetailVO.setUserId("123232");
        boardDetailVO.setNickName("ec0000");
        List<String> picUrls1 = new ArrayList<String>();
        picUrls1.add("http://cdn.duitang.com/uploads/item/201502/25/20150225172743_x2hfW.jpeg");
        boardDetailVO.setPicUrls(picUrls1);
        boardDetailVO.setContent("测试1");
        boardDetailVO.setPublishDate(new Date());
        boardDetailVO.setTransNum(128);
        boardDetailVO.setCommentNum(2);
        boardDetailVO.setSupportNum(99);
        return boardDetailVO;
    }

    @ApiOperation(value = "查询帖子评论内容:1.0", notes = "查询帖子评论内容[API-Version=1.0]")
    @RequestMapping(value = "/{postsId}/comments", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<CommentVO> findComments(
            @ApiParam(name = "postsId", value = "帖子的ID") @PathVariable String postsId,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        // TODO:待完成
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

    @ApiOperation(value = "帖子点赞:1.0", notes = "帖子点赞[API-Version=1.0]")
    @RequestMapping(value = "/{postsId}/support", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "点赞成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void postSupport(
            @ApiParam(name = "postsId", value = "帖子的ID") @PathVariable String postsId,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {

        // TODO:待完成
    }

    @ApiOperation(value = "帖子评论点赞:1.0", notes = "帖子评论点赞[API-Version=1.0]")
    @RequestMapping(value = "/{commentId}/support", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "点赞成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void commentSupport(
            @ApiParam(name = "commentId", value = "帖子评论的ID") @PathVariable String commentId,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {

        // TODO:待完成
    }

    @ApiOperation(value = "帖子评论:1.0", notes = "帖子评论[API-Version=1.0]")
    @RequestMapping(value = "/{postsId}/comment", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "评论成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void addComment(
            @ApiParam(name = "postsId", value = "帖子ID") @PathVariable String postsId,
            @ApiParam(name = "model", value = "显示模式(0:月亮模式;1:太阳模式)") @RequestParam(value = "model", required = true) DisplayModel model,
            @ApiParam(name = "commentAddVO", value = "评论体信息") @RequestBody CommentAddVO commentAddVO,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {

        // TODO:待完成
    }

    @ApiOperation(value = "添加收藏:1.0", notes = "添加收藏[API-Version=1.0]")
    @RequestMapping(value = "/{postsId}/collect", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "收藏成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void addCollect(
            @ApiParam(name = "postsId", value = "帖子ID") @PathVariable String postsId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {

        // TODO:待完成
    }

    @ApiOperation(value = "取消收藏:1.0", notes = "取消收藏[API-Version=1.0]")
    @RequestMapping(value = "/collect/{postsId}/cancel", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "取消收藏成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void cancelCollect(
            @ApiParam(name = "postsId", value = "帖子ID") @PathVariable String postsId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {

        // TODO:待完成
    }

    @ApiOperation(value = "收藏列表查询:1.0", notes = "收藏列表查询[API-Version=1.0]")
    @RequestMapping(value = "/collects", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<BoardVO> findCollects(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
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

    @ApiOperation(value = "审帖:1.0", notes = "审帖[API-Version=1.0]")
    @RequestMapping(value = "/{postsId}/check", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "审帖成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void check(
            @ApiParam(name = "postsId", value = "帖子ID") @PathVariable String postsId,
            @ApiParam(name = "result", value = "审核结果(0:举报;1:赞;2:踩)") @PathVariable String result,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {

        // TODO:待完成
    }

    @ApiOperation(value = "帖子发布:1.0", notes = "帖子发布[API-Version=1.0]")
    @RequestMapping(value = "/publish", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "发布成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void publish(
            @ApiParam(name = "BoardPublishVO", value = "发布内容体") @RequestBody BoardPublishVO boardPublishVO,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
        // TODO:待完成
    }

}
