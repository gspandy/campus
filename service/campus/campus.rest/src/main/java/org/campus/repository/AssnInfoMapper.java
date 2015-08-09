package org.campus.repository;

import org.campus.model.AssnInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface AssnInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_assninfo
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int deleteByPrimaryKey(String useruid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_assninfo
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insert(AssnInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_assninfo
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insertSelective(AssnInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_assninfo
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    AssnInfo selectByPrimaryKey(String useruid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_assninfo
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKeySelective(AssnInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_sys_assninfo
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKey(AssnInfo record);
}