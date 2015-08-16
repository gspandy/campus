package org.campus.service;

import org.campus.model.NickName;
import org.campus.model.enums.DisplayModel;

public interface NickNameService {

    void insert(NickName nickName);

    void deleteAll();

    String findRandomNickName(DisplayModel model,String sessionId);

}
