package org.campus.repository;

import java.util.List;

import org.campus.model.SmsRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRecordMapper {

    int deleteByPrimaryKey(String phonenumber);

    int insert(SmsRecord record);

    int insertSelective(SmsRecord record);

    SmsRecord selectByPrimaryKey(String phonenumber);

    int updateByPrimaryKeySelective(SmsRecord record);

    int updateByPrimaryKey(SmsRecord record);

    void batchInsert(List<SmsRecord> records);

    void batchUpdate(List<SmsRecord> records);

}