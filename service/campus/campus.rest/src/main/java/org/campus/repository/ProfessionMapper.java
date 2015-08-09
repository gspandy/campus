package org.campus.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.campus.model.Profession;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionMapper {
    int deleteByPrimaryKey(String uid);

    int insert(Profession record);

    int insertSelective(Profession record);

    Profession selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(Profession record);

    int updateByPrimaryKey(Profession record);
    
    /**
     * 查询某一院系的专业信息
     * @param collegeId 院系编号
     * @param schoolId  学校编号
     * @return
     */
    List<Profession> selectByCollege(@Param("collegeId")String collegeId,@Param("schoolId")String schoolId);
}