package org.campus.service;

import org.campus.model.enums.IntegralType;

public interface IntegralService {

    /**
     * 
     * 功能描述: <br>
     * 积分计算
     *
     * @param userId
     * @param integralType
     * @return
     *
     */
    long integral(String userId, String postId, IntegralType integralType);

}
