package org.campus.repository;

import org.campus.model.GroupUsers;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_groupusers
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int deleteByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_groupusers
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insert(GroupUsers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_groupusers
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insertSelective(GroupUsers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_groupusers
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    GroupUsers selectByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_groupusers
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKeySelective(GroupUsers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_groupusers
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKey(GroupUsers record);
}