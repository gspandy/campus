package org.campus.service;

import java.util.List;

import org.campus.model.School;
import org.campus.repository.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

	@Autowired
	SchoolMapper mapper;
	
	public List<School> getAllSchool(){
		return mapper.selectAll();
	}
	
	public School selectSchool(String uid){
		return mapper.selectByPrimaryKey(uid);
	}
	
}
