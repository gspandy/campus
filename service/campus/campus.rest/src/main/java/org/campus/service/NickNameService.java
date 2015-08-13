package org.campus.service;

import org.campus.model.NickName;

public interface NickNameService {

    void insert(NickName nickName);

    void deleteAll();

    NickName findRandomNickName();

}
