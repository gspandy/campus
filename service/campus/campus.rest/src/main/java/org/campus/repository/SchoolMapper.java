package org.campus.repository;

import org.campus.model.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolMapper {
    int deleteByPrimaryKey(String uid);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
    
    Page<School> selectAll(Pageable pageable);
}