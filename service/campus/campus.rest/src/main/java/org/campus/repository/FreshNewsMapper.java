package org.campus.repository;

import org.apache.ibatis.annotations.Param;
import org.campus.model.FreshNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FreshNewsMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ts_app_freshnews
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int deleteByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ts_app_freshnews
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insert(FreshNews record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ts_app_freshnews
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insertSelective(FreshNews record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ts_app_freshnews
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    FreshNews selectByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ts_app_freshnews
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKeySelective(FreshNews record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ts_app_freshnews
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKey(FreshNews record);

    int countPost(String userId);

    Page<FreshNews> findByUserId(@Param("userId") String userId, Pageable pageable);

    int updateSupport(String uid);

    int updateNotSupport(String uid);

    void deleteAll();

    Page<FreshNews> findMyCommentPosts(@Param("userId") String userId, Pageable pageable);

    Page<FreshNews> findMySupportPosts(@Param("userId") String userId, Pageable pageable);
    
    Page<FreshNews> selectByNewTypeAndShield(@Param("newsType")String newsType,@Param("isShield")int isShield,Pageable pageable);

    /**
     * 热门帖子
     * @param pageable
     * @return
     */
    Page<FreshNews> selectHotPosts(Pageable pageable);
    
    /**
     * 查询用户的关注帖子清单
     * @param userid 登录用户ID
     * @param pageable
     * @return
     */
    Page<FreshNews> selectAttentionPosts(@Param("userId") String userId,Pageable pageable);
}