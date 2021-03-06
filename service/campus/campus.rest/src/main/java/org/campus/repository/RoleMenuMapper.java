package org.campus.repository;

import org.campus.model.RoleMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_rolemenu
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int deleteByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_rolemenu
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insert(RoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_rolemenu
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insertSelective(RoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_rolemenu
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    RoleMenu selectByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_rolemenu
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKeySelective(RoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_rolemenu
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKey(RoleMenu record);
}