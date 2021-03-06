package org.campus.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.campus.model.ConversationDetail;
import org.campus.model.ReceiveMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiveMessageMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * ts_app_receivemessage
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int deleteByPrimaryKey(String uid);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * ts_app_receivemessage
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int insertSelective(ReceiveMessage record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * ts_app_receivemessage
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    ReceiveMessage selectByPrimaryKey(String uid);

    List<ReceiveMessage> selectByreceiveUserId(@Param("receiveUserId") String receiveUserId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * ts_app_receivemessage
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    int updateByPrimaryKeySelective(ReceiveMessage record);

    List<ConversationDetail> selectMessageDetailSingle(@Param("sessionUserId") String sessionUserId,
            @Param("objUserId") String objUserId);

    List<ConversationDetail> selectMessageDetailSingleUp(@Param("sessionUserId") String sessionUserId,
            @Param("objUserId") String objUserId, @Param("date") Date lastMsgDate);

    List<ConversationDetail> selectMessageDetailSingleDown(@Param("sessionUserId") String sessionUserId,
            @Param("objUserId") String objUserId, @Param("date") Date lastMsgDate);

}