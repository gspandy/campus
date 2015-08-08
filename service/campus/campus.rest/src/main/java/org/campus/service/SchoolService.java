package org.campus.service;

import java.util.List;

import org.campus.model.College;
import org.campus.model.School;
import org.campus.repository.CollegeMapper;
import org.campus.repository.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

	@Autowired
	private SchoolMapper mapper;
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	/**
	 * 获取所有学校
	 * @return
	 */
	public List<School> getAllSchool(){
		return mapper.selectAll();
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
	
}
