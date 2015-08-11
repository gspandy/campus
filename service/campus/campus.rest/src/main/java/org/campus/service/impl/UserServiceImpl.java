package org.campus.service.impl;

import org.campus.model.User;
import org.campus.repository.AttentionUserMapper;
import org.campus.repository.FreshNewsMapper;
import org.campus.repository.UserMapper;
import org.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AttentionUserMapper attentionUserMapper;

    @Autowired
    private FreshNewsMapper freshNewsMapper;

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

}
