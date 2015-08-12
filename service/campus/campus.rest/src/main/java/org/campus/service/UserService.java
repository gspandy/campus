package org.campus.service;

import org.campus.model.FreshNews;
import org.campus.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * 
     * 功能描述：<br>
     * 查询用户相册，即发表的新鲜事
     *
     * @param userId
     * @return
     *
     */
    Page<FreshNews> findUserPhotos(String userId, Pageable pageable);

}
