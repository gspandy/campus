package org.campus.repository;

import java.util.List;

import org.campus.model.College;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeMapper {
    int deleteByPrimaryKey(String uid);

    int insert(College record);

    int insertSelective(College record);

    College selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);
    
    /**获取某一学校的院系清单*/
    List<College> selectCollegeBySchoolUID(String schoolUid);
}