package org.campus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.campus.vo.BoardDetailVO;
import org.campus.vo.BoardVO;
import org.campus.vo.CommentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/board")
@Api(value = "BoardController", description = "板块相关操作")
public class BoardController {

    @ApiOperation(value = "帖子列表查询", notes = "帖子列表查询")
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<BoardVO> findBoard(
            @ApiParam(name = "type", value = "1:休闲;2:新鲜;3:秘密;4:言论;5:热门;6:关注") @RequestParam(value = "type", required = true) String type,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
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

    @ApiOperation(value = "帖子详情查询", notes = "帖子详情查询")
    @RequestMapping(value = "/{postsId}/detail", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public BoardDetailVO findBoardDetail(
            @ApiParam(name = "postsId", value = "帖子的ID") @PathVariable String postsId,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
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

    @ApiOperation(value = "查询帖子评论内容", notes = "查询帖子评论内容")
    @RequestMapping(value = "/{postsId}/comments", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<CommentVO> findComments(
            @ApiParam(name = "postsId", value = "帖子的ID") @PathVariable String postsId,
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

}
