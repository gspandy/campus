package org.campus.service;

import java.util.Date;

import org.campus.config.SystemConfig;
import org.campus.constant.Constant;
import org.campus.model.Comment;
import org.campus.model.Complain;
import org.campus.model.FavoriteFreshNews;
import org.campus.model.FreshNews;
import org.campus.model.FreshNewsAudit;
import org.campus.model.NotSupport;
import org.campus.model.Support;
import org.campus.model.Transfer;
import org.campus.model.UserFavorite;
import org.campus.model.enums.ActiveType;
import org.campus.model.enums.CheckType;
import org.campus.model.enums.IntegralType;
import org.campus.model.enums.TopicType;
import org.campus.model.enums.TypeCode;
import org.campus.repository.CommentMapper;
import org.campus.repository.ComplainMapper;
import org.campus.repository.FreshNewsAuditMapper;
import org.campus.repository.FreshNewsMapper;
import org.campus.repository.NotSupportMapper;
import org.campus.repository.SupportMapper;
import org.campus.repository.TransferMapper;
import org.campus.repository.UserFavoriteMapper;
import org.campus.util.ToolUtil;
import org.campus.vo.TransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private FreshNewsMapper freshMapper;

    @Autowired
    private UserFavoriteMapper favoriteMapper;

    @Autowired
    private SupportMapper supportMapper;

    @Autowired
    private FreshNewsAuditMapper freshNewsAuditMapper;

    @Autowired
    private TransferMapper transferMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ComplainMapper complainMapper;

    @Autowired
    private NotSupportMapper notSupportMapper;

    @Autowired
    private IntegralService integralService;

    /**
     * 查询帖子列表
     * 
     * @param topicType 话题类型
     * @param isShield 是否隐藏标识:隐藏贴只提供注册用户浏览
     * @param pageable 分页参数
     * @return
     */
    private Page<FreshNews> getTopicByTypeAndShield(String userId, TopicType topicType, int isShield, Pageable pageable) {
        // 根据不同的话题类型分发不同的SQL查询
        switch (topicType) {
            case HOT:
                return freshMapper.selectHotPosts(pageable);
            case ATTENTION:
                return freshMapper.selectAttentionPosts(userId, pageable);
            case RELAXATION:
            case NOVELTY:
            case PRIVACY:
            case SPEECH:
                return freshMapper.selectByNewTypeAndShield(topicType.getCode(), isShield, pageable);
            default:
                return null;
        }
    }

    /**
     * 为普通用户查询帖子列表
     * 
     * @param topicType 话题类型
     * @param pageable
     * @return
     */
    public Page<FreshNews> getPostsForAnonymous(TopicType topicType, Pageable pageable) {
        return this.getTopicByTypeAndShield(null, topicType, FreshNews.VIEW_ANONYMOUSE, pageable);
    }

    /**
     * 为登录用户查询帖子列表
     * 
     * @param userId 登录用户编号
     * @param topicType 话题类型
     * @param pageable
     * @return
     */
    public Page<FreshNews> getPostsForRegister(String userId, TopicType topicType, Pageable pageable) {
        return this.getTopicByTypeAndShield(userId, topicType, FreshNews.VIEW_ANONYMOUSE, pageable);
    }

    /**
     * 
     * 功能描述: <br>
     * 审核帖子列表查询
     *
     * @param userId
     * @param topicType
     * @param pageable
     * @return
     *
     */
    public Page<FreshNews> getAuditPosts(String userId, Pageable pageable) {
        return freshMapper.getAuditPosts(userId, FreshNews.VIEW_ANONYMOUSE, pageable);
    }

    /**
     * 帖子详情
     * 
     * @param postsId 帖子编号
     * @return
     */
    public FreshNews getPostsDetail(String postsId) {
        return this.freshMapper.selectByPrimaryKey(postsId);
    }

    /**
     * 发表帖子
     * 
     * @param freshNews
     */
    public void publishPosts(FreshNews freshNews) {
        this.freshMapper.insert(freshNews);
    }

    /**
     * 收藏帖子
     * 
     * @param favorite
     */
    public void createFavorite(UserFavorite favorite) {
        this.favoriteMapper.insert(favorite);
    }

    /**
     * 删除收藏的帖子
     * 
     * @param favoriteId 收藏编号
     */
    public void deleteFavorite(String favoriteId) {
        this.favoriteMapper.deleteByPrimaryKey(favoriteId);
    }

    /**
     * 查询收藏帖子内容
     * 
     * @param userId
     * @param pageable
     * @return
     */
    public Page<FavoriteFreshNews> getUserFavorite(String userId, Pageable pageable) {
        return this.favoriteMapper.selectByUserId(userId, pageable);
    }

    /**
     * 
     * 功能描述: <br>
     * 查询帖子是否被赞过
     *
     * @param sourceId
     * @param userId
     * @return
     *
     */
    public boolean isSupported(String sourceId, String userId) {
        int count = supportMapper.isSupported(sourceId, userId);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 查询帖子是否被赞过
     *
     * @param sourceId
     * @param userId
     * @return
     *
     */
    public boolean isFavorited(String postsId, String userId) {
        int count = favoriteMapper.isFavorited(postsId, userId);
        if (count > 0) {
            return true;
        }
        return false;
    }

    public void audit(String postsId, String userId, String nickName, CheckType type) {
        FreshNews freshNews = freshMapper.selectByPrimaryKey(postsId);
        switch (type) {
            case COMPLAIN:
                Complain complain = new Complain();
                complain.setUid(ToolUtil.getUUid());
                complain.setSourceuid(postsId);
                complain.setUseruid(userId);
                complain.setTypecode(1);
                complain.setManageoperate(0);
                complain.setIsactive(1);
                complain.setCreateby(userId);
                complain.setCreatedate(new Date());
                complain.setLastupdateby(userId);
                complain.setLastupdatedate(new Date());
                complainMapper.insert(complain);
                freshNews.setComplainnum(freshNews.getComplainnum() + 1);
                break;
            case SUPPORT:
                Support support = new Support();
                support.setUid(ToolUtil.getUUid());
                support.setSourceuid(postsId);
                support.setSupportuseruid(userId);
                support.setUsernickname(nickName);
                support.setTypecode(TypeCode.FRESH_NEWS);
                support.setIsactive(ActiveType.ACTIVE);
                support.setCreateby(userId);
                support.setCreatedate(new Date());
                support.setLastupdateby(userId);
                support.setLastupdatedate(new Date());
                supportMapper.insert(support);
                freshNews.setSupportnum(freshNews.getSupportnum() + 1);
                break;
            case NOT_SUPPORT:
                NotSupport notsupport = new NotSupport();
                notsupport.setUid(ToolUtil.getUUid());
                notsupport.setSourceuid(postsId);
                notsupport.setUseruid(userId);
                notsupport.setUsernickname(nickName);
                notsupport.setTypecode(TypeCode.FRESH_NEWS);
                notsupport.setIsactive(ActiveType.ACTIVE);
                notsupport.setCreateby(userId);
                notsupport.setCreatedate(new Date());
                notsupport.setLastupdateby(userId);
                notsupport.setLastupdatedate(new Date());
                notSupportMapper.insert(notsupport);
                freshNews.setNotsupportnum(freshNews.getNotsupportnum() + 1);
                break;
            default:
                break;
        }

        if (freshNews.getSupportnum() - freshNews.getNotsupportnum() - freshNews.getComplainnum() >= SystemConfig
                .getInt("PASS_AUDIT")) {
            freshNews.setIsshield(0);
            freshNews.setCheckDate(new Date());
            integralService.integral(freshNews.getAdduseruid(), null, IntegralType.POSTS);
        }

        freshMapper.updateByPrimaryKeySelective(freshNews);
        FreshNewsAudit record = new FreshNewsAudit();
        record.setUid(ToolUtil.getUUid());
        record.setUserid(userId);
        record.setPostid(postsId);
        record.setAuditreust(type);
        record.setAudittime(new Date());
        freshNewsAuditMapper.insert(record);
        integralService.integral(userId, null, IntegralType.CHECk_POSTS);
    }

    public Page<FreshNews> search(String keyword, Pageable pageable) {
        return freshMapper.search(keyword, pageable);
    }

    public void transfer(String postsId, String userId, String nickName, TransferVO transferVO, String ipAddr) {
        if (transferVO != null && transferVO.isComment()) {
            Comment comment = new Comment();
            comment.setUid(ToolUtil.getUUid());
            comment.setSourceuid(postsId);
            comment.setComuseruid(userId);
            comment.setUsernickname(nickName);
            comment.setCommentcontent(transferVO.getContent());
            comment.setIsactive(ActiveType.ACTIVE);
            comment.setCreateby(Constant.CREATE_BY);
            comment.setCreatedate(new Date());
            comment.setLastupdateby(Constant.CREATE_BY);
            comment.setLastupdatedate(new Date());
            comment.setIpaddress(ipAddr);
            commentMapper.insert(comment);
        }
        FreshNews freshNews = freshMapper.selectByPrimaryKey(postsId);
        freshNews.setCommentnum(freshNews.getCommentnum() == null ? 1 : freshNews.getCommentnum() + 1);
        freshMapper.updateByPrimaryKey(freshNews);

        freshNews.setUid(ToolUtil.getUUid());
        freshNews.setAdduseruid(userId);
        freshNews.setAddnickname(nickName);
        freshNews.setNewstype(transferVO.getTopicType().getCode());
        freshNews.setCommentnum(0);
        freshNews.setComplainnum(0);
        freshNews.setNotsupportnum(0);
        freshNews.setSupportnum(0);
        freshNews.setTransnum(0);
        freshNews.setCreatedate(new Date());
        freshMapper.insert(freshNews);

        Transfer record = new Transfer();
        record.setUid(ToolUtil.getUUid());
        record.setUserid(userId);
        record.setPostid(postsId);
        record.setObjpostid(freshNews.getUid());
        record.setTransdate(new Date());
        record.setDeleted("0");
        record.setTransferComment(transferVO.getContent());
        transferMapper.insert(record);
    }

    public void delete(String postId, String userId) {
        FreshNews freshNews = new FreshNews();
        freshNews.setUid(postId);
        freshNews.setDeleted("1");
        freshMapper.updateByPrimaryKeySelective(freshNews);
        Transfer record = new Transfer();
        record.setPostid(postId);
        record.setDeleted("1");
        transferMapper.updateByPrimaryKeySelective(record);
    }

    public boolean isDelete(String postId) {
        FreshNews fresh = freshMapper.selectByPrimaryKey(postId);
        if (fresh != null && "1".equals(fresh.getDeleted())) {
            return true;
        }
        return false;
    }

    public Transfer findTransfer(String objPostId) {
        return transferMapper.findTransferByObjPostId(objPostId);
    }

}