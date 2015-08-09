package org.campus.repository;

import org.campus.model.TwoCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface TwoCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_twocategory
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int deleteByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_twocategory
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insert(TwoCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_twocategory
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insertSelective(TwoCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_twocategory
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    TwoCategory selectByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_twocategory
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKeySelective(TwoCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_twocategory
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKey(TwoCategory record);
}