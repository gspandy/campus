package org.campus.service.impl;

import java.util.Date;
import java.util.List;

import org.campus.constant.Constant;
import org.campus.core.exception.CampusException;
import org.campus.model.AttentionUser;
import org.campus.model.Comment;
import org.campus.model.FreshNews;
import org.campus.model.NotSupport;
import org.campus.model.Share;
import org.campus.model.Support;
import org.campus.model.User;
import org.campus.model.enums.ActiveType;
import org.campus.model.enums.AnonymousType;
import org.campus.model.enums.DisplayModel;
import org.campus.model.enums.InteractType;
import org.campus.model.enums.TypeCode;
import org.campus.repository.AttentionUserMapper;
import org.campus.repository.CommentMapper;
import org.campus.repository.FreshNewsMapper;
import org.campus.repository.NotSupportMapper;
import org.campus.repository.ShareMapper;
import org.campus.repository.SupportMapper;
import org.campus.repository.UserMapper;
import org.campus.service.UserService;
import org.campus.util.ToolUtil;
import org.campus.vo.CommentAddVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AttentionUserMapper attentionUserMapper;

    @Autowired
    private FreshNewsMapper freshNewsMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SupportMapper supportMapper;

    @Autowired
    private NotSupportMapper notSupportMapper;

    @Autowired
    private ShareMapper shareMapper;

    @Override
    public User findByUserId(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public int countAttention(String userId) {
        return attentionUserMapper.countAttention(userId);
    }

    @Override
    public int countFans(String userId) {
        return attentionUserMapper.countFans(userId);
    }

    @Override
    public int countPost(String userId) {
        return freshNewsMapper.countPost(userId);
    }

    @Override
    public Page<FreshNews> findUserPhotos(String userId, Pageable pageable) {
        return freshNewsMapper.findByUserId(userId, pageable);
    }

    @Override
    public Page<Comment> findComments(String sourceId, Pageable pageable) {
        return commentMapper.findBySourceId(sourceId, pageable);
    }

    @Override
    public int getUserCommentSupport(String sourceId, String userId) {
        return supportMapper.getSupportNum(sourceId, userId);
    }

    @Override
    public void photoSupport(String sourceId, String userId, String userName, InteractType type) {
        FreshNews freshNews = freshNewsMapper.selectByPrimaryKey(sourceId);
        if (freshNews == null) {
            throw new CampusException(1100002, "查询不到数据");
        }
        if (InteractType.SUPPORT.equals(type)) {
            freshNewsMapper.updateSupport(sourceId);
            support(sourceId, userId, userName);
        } else {
            freshNewsMapper.updateNotSupport(sourceId);
            notSupport(sourceId, userId, userName);
        }
    }

    @Override
    public void commentSupport(String sourceId, String userId, String userName, InteractType type) {
        Comment comment = commentMapper.selectByPrimaryKey(sourceId);
        if (comment == null) {
            throw new CampusException(1100002, "查询不到数据");
        }
        if (InteractType.SUPPORT.equals(type)) {
            support(sourceId, userId, userName);
        } else {
            notSupport(sourceId, userId, userName);
        }
    }

    @Override
    public void comment(String sourceId, String userId, String userName, DisplayModel model, CommentAddVO commentAddVO) {
        FreshNews freshNews = freshNewsMapper.selectByPrimaryKey(sourceId);
        if (freshNews == null) {
            throw new CampusException(1100002, "查询不到数据");
        }
        Comment comment = new Comment();
        comment.setUid(ToolUtil.getUUid());
        comment.setSourceuid(sourceId);
        comment.setComuseruid(userId);
        comment.setUsernickname(userName);
        comment.setCommentcontent(commentAddVO.getContent());
        comment.setIsactive(ActiveType.ACTIVE);
        comment.setCreateby(Constant.CREATE_BY);
        comment.setCreatedate(new Date());
        comment.setLastupdateby(Constant.CREATE_BY);
        comment.setLastupdatedate(new Date());
        commentMapper.insert(comment);

        if (commentAddVO.isTrans()) {
            freshNews.setUid(ToolUtil.getUUid());
            freshNews.setAdduseruid(userId);
            freshNews.setAddnickname(userName);
            if (DisplayModel.MOON.equals(model)) {
                freshNews.setIsanonymous(AnonymousType.ANONYMOUS);
            } else {
                freshNews.setIsanonymous(AnonymousType.NOT_ANONYMOUS);
            }
            freshNewsMapper.insert(freshNews);

            Share share = new Share();
            share.setUid(ToolUtil.getUUid());
            share.setActivityuid(sourceId);
            share.setShareuseruid(userId);
            share.setSharetime(new Date());
            share.setFreshnewsuid(freshNews.getUid());
            share.setIsactive(ActiveType.ACTIVE);
            share.setCreateby(Constant.CREATE_BY);
            share.setCreatedate(new Date());
            share.setLastupdateby(Constant.CREATE_BY);
            share.setLastupdatedate(new Date());
            shareMapper.insert(share);
        }
    }

    @Override
    public void attention(String comUserId, String objUserId) {
        User user = userMapper.selectByPrimaryKey(objUserId);
        if (user == null) {
            throw new CampusException(1100002, "查询不到数据");
        }

        AttentionUser attentionUser = new AttentionUser();
        attentionUser.setUid(ToolUtil.getUUid());
        attentionUser.setMyuseruid(comUserId);
        attentionUser.setAttenionuseruid(objUserId);
        attentionUser.setAttentiontime(new Date());
        attentionUser.setCreateby(Constant.CREATE_BY);
        attentionUser.setCreatedate(new Date());
        attentionUserMapper.insert(attentionUser);
    }

    @Override
    public void removeAttention(String comUserId, String objUserId) {
        attentionUserMapper.removeAttention(comUserId, objUserId);
    }

    @Override
    public boolean isAttention(String comUserId, String objUserId) {
        AttentionUser attention = attentionUserMapper.findAttention(comUserId, objUserId);
        if (attention != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> findMyFriends(String myUserId, String friendNickName) {
        return userMapper.findMyFriends(myUserId, friendNickName);
    }

    @Override
    public List<User> findMyFans(String myUserId, String friendNickName) {
        return userMapper.findMyFans(myUserId, friendNickName);
    }

    @Override
    public Page<FreshNews> findMyCommentPosts(String userId, Pageable pageable) {
        return freshNewsMapper.findMyCommentPosts(userId, pageable);
    }

    @Override
    public List<Comment> findMyComments(String sourceId, String userId) {
        return commentMapper.findMyComments(sourceId, userId);
    }

    @Override
    public int countMyCommentSupport(String sourceId) {
        return supportMapper.countMyCommentSupport(sourceId);
    }

    @Override
    public Page<FreshNews> findMySupportPosts(String userId, Pageable pageable) {
        return freshNewsMapper.findMySupportPosts(userId, pageable);
    }

    private void notSupport(String sourceId, String userId, String userName) {
        NotSupport notSupport = new NotSupport();
        notSupport.setUid(ToolUtil.getUUid());
        notSupport.setSourceuid(sourceId);
        notSupport.setUseruid(userId);
        notSupport.setUsernickname(userName);
        notSupport.setTypecode(TypeCode.PHOTOS);
        notSupport.setIsactive(ActiveType.ACTIVE);
        notSupport.setCreateby(Constant.CREATE_BY);
        notSupport.setCreatedate(new Date());
        notSupport.setLastupdateby(Constant.CREATE_BY);
        notSupport.setLastupdatedate(new Date());
        notSupportMapper.insert(notSupport);
    }

    private void support(String sourceId, String userId, String userName) {
        Support support = new Support();
        support.setUid(ToolUtil.getUUid());
        support.setSourceuid(sourceId);
        support.setSupportuseruid(userId);
        support.setUsernickname(userName);
        support.setTypecode(TypeCode.PHOTOS);
        support.setIsactive(ActiveType.ACTIVE);
        support.setCreateby(Constant.CREATE_BY);
        support.setCreatedate(new Date());
        support.setLastupdateby(Constant.CREATE_BY);
        support.setLastupdatedate(new Date());
        supportMapper.insert(support);
    }

}
