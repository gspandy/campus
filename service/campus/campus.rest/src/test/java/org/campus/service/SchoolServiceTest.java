package org.campus.service;

import java.util.List;

import org.campus.BaseTest;
import org.campus.model.School;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class SchoolServiceTest extends BaseTest {

	@Autowired
	private SchoolService service;
	
	@Test
	public void getAllSchool(){
		List<School> schools = service.getAllSchool();
		Assert.assertNotNull(schools);
		for(School school:schools){
			if(school.getSchoolcode().equals("10001")){
				Assert.assertEquals(school.getSchoolname(), "北京大学");
			}
		}
	}
	
	@Test
	public void getSchoolByUID(){
		School school = service.selectSchool("1D754DA8-AB89-4418-AC20-5115E53B1722");
		Assert.assertEquals(school.getSchoolname(), "北京大学");
	}
	
}
