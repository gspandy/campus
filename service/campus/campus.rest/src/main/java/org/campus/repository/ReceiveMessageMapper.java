package org.campus.repository;

import org.campus.model.ReceiveMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveMessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_receivemessage
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int deleteByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_receivemessage
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insert(ReceiveMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_receivemessage
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insertSelective(ReceiveMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_receivemessage
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    ReceiveMessage selectByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_receivemessage
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKeySelective(ReceiveMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_app_receivemessage
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKey(ReceiveMessage record);
}