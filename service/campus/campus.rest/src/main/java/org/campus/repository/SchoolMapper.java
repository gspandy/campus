package org.campus.repository;

import java.util.List;

import org.campus.model.School;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolMapper {
    int deleteByPrimaryKey(String uid);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
    
    List<School> selectAll();
}