package org.campus.service;

import org.campus.model.User;

public interface UserService {

    /**
     * 
     * 功能描述: <br>
     * 查询用户信息
     *
     * @param userId
     * @return
     *
     */
    User findByUserId(String userId);

    /**
     * 
     * 功能描述: <br>
     * 查询关注用户数
     *
     * @param userId
     * @return
     *
     */
    int countAttention(String userId);

    /**
     * 
     * 功能描述: <br>
     * 查询fans数
     *
     * @param userId
     * @return
     *
     */
    int countFans(String userId);

    /**
     * 
     * 功能描述: <br>
     * 统计发帖数
     *
     * @param userId
     * @return
     *
     */
    int countPost(String userId);

}
