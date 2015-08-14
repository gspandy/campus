package org.campus.service.impl;

import org.campus.model.NickName;
import org.campus.repository.NickNameMapper;
import org.campus.service.NickNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NickNameServiceImpl implements NickNameService {

    @Autowired
    private NickNameMapper nickNameMapper;

    @Override
    public void insert(NickName nickName) {
        nickNameMapper.insert(nickName);
    }

    @Override
    public void deleteAll() {
        nickNameMapper.deleteAll();
    }

    @Override
    public NickName findRandomNickName() {
        int count = nickNameMapper.count();
        int uid = (int) (Math.random() * count);
        NickName nickName = nickNameMapper.selectByPrimaryKey(uid);
        if (nickName == null) {
            nickName = new NickName();
        }
        return nickName;
    }
}
