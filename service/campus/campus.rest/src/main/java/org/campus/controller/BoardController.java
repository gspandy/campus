package org.campus.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.campus.annotation.NeedRoles;
import org.campus.constant.Constant;
import org.campus.core.exception.CampusException;
import org.campus.model.FavoriteFreshNews;
import org.campus.model.FreshNews;
import org.campus.model.UserFavorite;
import org.campus.model.enums.AnonymousType;
import org.campus.model.enums.DisplayModel;
import org.campus.model.enums.TopicType;
import org.campus.service.NickNameService;
import org.campus.service.TopicService;
import org.campus.service.UserService;
import org.campus.util.ToolUtil;
import org.campus.vo.BoardDetailVO;
import org.campus.vo.BoardFavoriteVO;
import org.campus.vo.BoardPublishVO;
import org.campus.vo.BoardVO;
import org.campus.vo.LoginResponseVO;
import org.campus.vo.TransferVO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TopicService topicSvc;

    @Autowired
    NickNameService nickNameSvc;

    @Autowired
    UserService userService;

    @ApiOperation(value = "*帖子列表查询:1.0", notes = "*帖子列表查询[API-Version=1.0]")
    @RequestMapping(value = "/posts", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<BoardVO> findBoard(
            @ApiParam(name = "type", value = "1:休闲;2:新鲜;3:秘密;4:言论;5:热门;6:关注") @RequestParam(value = "type", required = true) String type,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            HttpSession session) {
        Assert.hasLength(type, "帖子类型不能为空.");
        TopicType topicType = TopicType.getTypeCodeByCode(type);
        if (topicType == null) {
            throw new CampusException(100201, "帖子类型不存在:" + type);
        }

        Page<FreshNews> freshNews;
        LoginResponseVO user = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        if (user == null) {
            // 未登录用户查询
            // 没有关注帖子
            if (topicType == TopicType.ATTENTION)
                throw new CampusException(100301, "未登录用户没有关注内容.");
            freshNews = topicSvc.getPostsForAnonymous(topicType, pageable);
        } else {
            // 登录用户查询
            freshNews = topicSvc.getPostsForRegister(user.getUserId(), topicType, pageable);
        }

        List<FreshNews> listTopic = freshNews.getContent();
        List<BoardVO> boardVOs = new ArrayList<BoardVO>();
        for (FreshNews topic : listTopic) {
            BoardVO vo = new BoardVO();
            vo.setPostsId(topic.getUid());
            vo.setUserId(topic.getCreateby());
            vo.setNickName(topic.getAddnickname());
            vo.setHeadPic(user.getHeadPic());
            vo.setBrief(topic.getNewsbrief());
            vo.setContent(topic.getNewscontent());
            vo.setPublishDate(topic.getCreatedate());
            String[] picUrls = topic.getPictures().split(",");
            vo.setPicUrls(Arrays.asList(picUrls));
            vo.setSupported(topicSvc.isSupported(topic.getUid(), user.getUserId()));
            vo.setTransNum(topic.getTransnum());
            vo.setCommentNum(topic.getCommentnum());
            vo.setSupportNum(topic.getSupportnum());
            vo.setNotSupportNum(topic.getNotsupportnum());
            vo.setComplainNum(topic.getComplainnum());
            boardVOs.add(vo);
        }

        Page<BoardVO> page = new PageImpl<BoardVO>(boardVOs, pageable, boardVOs.size());
        return page;
    }

    @ApiOperation(value = "*帖子详情查询:1.0", notes = "帖子详情查询[API-Version=1.0]")
    @RequestMapping(value = "/posts/detail", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public BoardDetailVO findBoardDetail(
            @ApiParam(name = "postsId", value = "帖子的ID") @RequestParam(value = "postsId", required = true) String postsId,
            HttpSession session) {
        LoginResponseVO user = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        BoardDetailVO boardVo = null;
        FreshNews topic = this.topicSvc.getPostsDetail(postsId);
        if (topic != null) {
            boardVo = new BoardDetailVO();
            boardVo.setCommentNum(topic.getCommentnum());
            boardVo.setContent(topic.getNewscontent());
            boardVo.setNickName(topic.getAddnickname());

            String[] picUrls = topic.getPictures().split(",");
            boardVo.setPicUrls(Arrays.asList(picUrls));

            boardVo.setPostsId(postsId);
            boardVo.setPublishDate(topic.getCreatedate());
            boardVo.setSupportNum(topic.getSupportnum());
            boardVo.setTransNum(topic.getTransnum());
            boardVo.setUserId(topic.getCreateby());
            boardVo.setSupported(topicSvc.isSupported(postsId, user.getUserId()));
            boardVo.setCollected(topicSvc.isFavorited(postsId, user.getUserId()));
        }

        return boardVo;
    }

    @ApiOperation(value = "*添加收藏:1.0", notes = "添加收藏[API-Version=1.0]")
    @RequestMapping(value = "/{postsId}/collect", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "收藏成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void addCollect(@ApiParam(name = "postsId", value = "帖子ID") @PathVariable String postsId, HttpSession session) {
        Assert.hasLength(postsId, "必须指定待收藏的帖子.");
        // 验证用户有无登录
        LoginResponseVO vo = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        if (vo == null)
            throw new CampusException(100204, "请登录.");
        UserFavorite favorite = new UserFavorite();
        favorite.setUid(ToolUtil.getUUid());
        favorite.setPostsid(postsId);
        favorite.setUserid(vo.getUserId());
        favorite.setCreatetime(Calendar.getInstance().getTime());
        topicSvc.createFavorite(favorite);
    }

    @ApiOperation(value = "*取消收藏:1.0", notes = "取消收藏[API-Version=1.0]")
    @RequestMapping(value = "/collect/{postsId}/cancel", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "取消收藏成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void cancelCollect(@ApiParam(name = "postsId", value = "收藏列表编号") @PathVariable String postsId,
            HttpSession session) {
        if (session.getAttribute(Constant.CAMPUS_SECURITY_SESSION) == null)
            throw new CampusException(100204, "请登录.");
        topicSvc.deleteFavorite(postsId);
    }

    @ApiOperation(value = "*收藏列表查询:1.0", notes = "收藏列表查询[API-Version=1.0]")
    @RequestMapping(value = "/collects", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public Page<BoardFavoriteVO> findCollects(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            HttpSession session) {

        // 验证用户有无登录
        LoginResponseVO vo = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        if (vo == null)
            throw new CampusException(100204, "请登录.");

        Page<FavoriteFreshNews> srcData = topicSvc.getUserFavorite(vo.getUserId(), pageable);
        List<FavoriteFreshNews> lstData = srcData.getContent();

        List<BoardFavoriteVO> boardVOs = new ArrayList<BoardFavoriteVO>();
        for (FavoriteFreshNews data : lstData) {
            BoardFavoriteVO favorite = new BoardFavoriteVO();
            favorite.setPostsId(data.getUid());
            favorite.setUserId(data.getCreateby());
            favorite.setNickName(data.getAddnickname());

            String[] picUrls = data.getPictures().split(",");
            favorite.setPicUrls(Arrays.asList(picUrls));

            favorite.setContent(data.getNewscontent());

            favorite.setPublishDate(data.getCreatedate());
            boardVOs.add(favorite);
        }

        Page<BoardFavoriteVO> page = new PageImpl<BoardFavoriteVO>(boardVOs, pageable, boardVOs.size());
        return page;
    }

    @ApiOperation(value = "*帖子发布:1.0", notes = "帖子发布[API-Version=1.0]")
    @RequestMapping(value = "/publish", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "发布成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void publish(@ApiParam(name = "BoardPublishVO", value = "发布内容体") @RequestBody BoardPublishVO boardPublishVO,
            HttpSession session) {
        // 验证用户有无登录
        LoginResponseVO vo = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        if (vo == null)
            throw new CampusException(100204, "请登录.");
        DisplayModel model = (DisplayModel) session.getAttribute(Constant.CAMPUS_DISPLAYMODEL);

        String nickName = nickNameSvc.findRandomNickName(model, session.getId());
        nickName = nickName == null ? vo.getNickName() : nickName;

        FreshNews posts = new FreshNews();
        posts.setAddnickname(nickName);
        posts.setAdduseruid(vo.getUserId());
        posts.setCommentnum(0);
        posts.setComplainnum(0);
        posts.setCreateby(vo.getUserId());
        posts.setCreatedate(Calendar.getInstance().getTime());
        posts.setIsactive(1);
        posts.setIsshield(1);
        posts.setIsanonymous(model == DisplayModel.MOON ? AnonymousType.ANONYMOUS : AnonymousType.NOT_ANONYMOUS);
        posts.setIshot("0");
        posts.setIsshield(FreshNews.VIEW_REGISTER);// 新帖必须屏蔽
        posts.setNewsbrief(boardPublishVO.getTitle());
        posts.setNewscontent(boardPublishVO.getContent());

        // newsType in (1,2,3,4)
        List<String> lst = Arrays.asList("1", "2", "3", "4");
        if (!lst.contains(boardPublishVO.getType()))
            throw new CampusException(100205, "帖子类型错误.");
        posts.setNewstype(boardPublishVO.getType());

        posts.setNotsupportnum(0);

        List<String> pics = boardPublishVO.getPicUrls();
        if (pics == null || pics.size() == 0)
            posts.setPictures("");
        else {
            StringBuilder sb = new StringBuilder();
            for (String s : pics) {
                sb.append(s).append(",");
            }
            String urls = sb.toString().substring(0, sb.length() - 1);
            posts.setPictures(urls);
        }
        posts.setSupportnum(0);
        posts.setTransnum(0);
        posts.setUid(ToolUtil.getUUid());

        topicSvc.publishPosts(posts);
    }

    @ApiOperation(value = "*切换显示模式:1.0", notes = "切换显示模式[API-Version=1.0]")
    @RequestMapping(value = "/changeDisplayModel", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "发布成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void changeDisplayModel(
            @ApiParam(name = "environment", value = "显示模式(0:月亮;1:太阳;)") @RequestParam(value = "environment", required = true) String environment,
            HttpSession session) {
        if (session.getAttribute(Constant.CAMPUS_SECURITY_SESSION) == null)
            throw new CampusException(100204, "请登录.");
        DisplayModel model = DisplayModel.getDisplayModelByCode(environment);
        if (model == null) {
            throw new CampusException(100203, "显示模式错误.");
        }
        session.setAttribute(Constant.CAMPUS_DISPLAYMODEL, model);
    }

    @ApiOperation(value = "*转发评论:1.0", notes = "转发评论[API-Version=1.0]")
    @RequestMapping(value = "/transfer/{postsId}", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "发布成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void transfer(
            @ApiParam(name = "postsId", value = "帖子ID") @PathVariable String postsId,
            @ApiParam(name = "transferVO", value = "转发内容") @RequestBody TransferVO transferVO,
            @ApiParam(name = "environment", value = "显示模式(0:月亮;1:太阳;)") @RequestParam(value = "environment", required = true) String environment,
            HttpSession session) {
        return;
    }

    @ApiOperation(value = "*开始审帖:1.0", notes = "开始审帖[API-Version=1.0]")
    @RequestMapping(value = "/audit", headers = { "API-Version=1.0" }, method = RequestMethod.PUT)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "审核成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void beginAudit(
            @ApiParam(name = "environment", value = "显示模式(0:月亮;1:太阳;)") @RequestParam(value = "environment", required = true) String environment,
            HttpSession session) {
        LoginResponseVO vo = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        userService.beginAudit(vo.getUserId());
    }

    @ApiOperation(value = "*审核:1.0", notes = "审核[API-Version=1.0]")
    @RequestMapping(value = "/audit/{postsId}", headers = { "API-Version=1.0" }, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "审核成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public void audit(
            @ApiParam(name = "postsId", value = "帖子ID") @PathVariable String postsId,
            @ApiParam(name = "transferVO", value = "转发内容") @RequestBody TransferVO transferVO,
            @ApiParam(name = "environment", value = "显示模式(0:月亮;1:太阳;)") @RequestParam(value = "environment", required = true) String environment,
            HttpSession session) {
        return;
    }

}
