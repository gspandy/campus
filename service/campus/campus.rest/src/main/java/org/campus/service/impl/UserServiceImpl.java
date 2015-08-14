package org.campus.service.impl;

import java.util.Date;

import org.campus.constant.Constant;
import org.campus.core.exception.CampusException;
import org.campus.model.Comment;
import org.campus.model.FreshNews;
import org.campus.model.NotSupport;
import org.campus.model.Support;
import org.campus.model.User;
import org.campus.model.enums.ActiveType;
import org.campus.model.enums.InteractType;
import org.campus.model.enums.TypeCode;
import org.campus.repository.AttentionUserMapper;
import org.campus.repository.CommentMapper;
import org.campus.repository.FreshNewsMapper;
import org.campus.repository.NotSupportMapper;
import org.campus.repository.SupportMapper;
import org.campus.repository.UserMapper;
import org.campus.service.UserService;
import org.campus.util.ToolUtil;
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
        } else {
            freshNewsMapper.updateNotSupport(sourceId);
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
    }
}
