package org.campus.service.impl;

import org.campus.model.Comment;
import org.campus.model.FreshNews;
import org.campus.model.User;
import org.campus.model.enums.DisplayModel;
import org.campus.model.enums.InteractType;
import org.campus.repository.AttentionUserMapper;
import org.campus.repository.CommentMapper;
import org.campus.repository.FreshNewsMapper;
import org.campus.repository.SupportMapper;
import org.campus.repository.UserMapper;
import org.campus.service.UserService;
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
    public void photoSupport(String photoId, InteractType type, DisplayModel model) {

    }

}
