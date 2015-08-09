package org.campus.service;

import java.util.List;

import org.campus.model.College;
import org.campus.model.Profession;
import org.campus.model.School;
import org.campus.repository.CollegeMapper;
import org.campus.repository.ProfessionMapper;
import org.campus.repository.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

	@Autowired
	private SchoolMapper mapper;
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	@Autowired
	private ProfessionMapper professionMapper;
	
	/**
	 * 获取所有学校
	 * @return
	 */
	public Page<School> getAllSchool(Pageable pageable){
		return mapper.selectAll(pageable);
	}
	
	/**
	 * 根据UID获取某一学校的详细信息
	 * @param uid
	 * @return
	 */
	public School selectSchool(String uid){
		return mapper.selectByPrimaryKey(uid);
	}
	
	/**
	 * 获取某一学校的院系清单
	 * @param schoolUid
	 * @return
	 */
	public List<College> getSchoolCollege(String schoolUid){
		return collegeMapper.selectCollegeBySchoolUID(schoolUid);
	}
	
	/**
	 * 获取某院系的专业清单
	 * @param collegeId 院系编号
	 * @param schoolId 学校编号
	 * @return
	 */
	public List<Profession> getCollegeProfession(String collegeId,String schoolId){
		return professionMapper.selectByCollege(collegeId, schoolId);
	}
	
}
