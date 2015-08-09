package org.campus.service;

import java.util.List;

import org.campus.BaseTest;
import org.campus.model.College;
import org.campus.model.Profession;
import org.campus.model.School;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class SchoolServiceTest extends BaseTest {

	@Autowired
	private SchoolService service;
	
	@Test
	public void getAllSchool(){
		Pageable pageable = new PageRequest(0,100);
		Page<School> pschools = service.getAllSchool(pageable);
		Assert.assertNotNull(pschools);
		
		List<School> schools = pschools.getContent();
		Assert.assertEquals(schools.size(), 100);
		for(School school:schools){
			if(school.getSchoolcode().equals("10001")){
				Assert.assertEquals(school.getSchoolname(), "北京大学");
				return;
			}
		}
		Assert.assertEquals(1, 2);
	}
	
	@Test
	public void getSchoolByUID(){
		School school = service.selectSchool("1D754DA8-AB89-4418-AC20-5115E53B1722");
		Assert.assertEquals(school.getSchoolname(), "北京大学");
	}
	
	@Test
	public void getCollegeBySchoolUID(){
		List<College> colleges = service.getSchoolCollege("1D754DA8-AB89-4418-AC20-5115E53B1722");
		Assert.assertNotNull(colleges);
		for(College college : colleges){
			if(college.getUid().equals("470F7BC5-0CB7-45AF-962A-2DAC106D1229")){
				Assert.assertEquals(college.getCollegename(), "中国语言文学系");
				return;
			}
		}
		Assert.assertEquals(1, 2);
	}
	
	@Test
	public void getProfessionTest(){
		List<Profession> professions = service.getCollegeProfession("470F7BC5-0CB7-45AF-962A-2DAC106D1229", "1D754DA8-AB89-4418-AC20-5115E53B1722");
		Assert.assertNotNull(professions);
		for(Profession profession:professions){
			if(profession.getUid().equals("77E21677-A3D3-49E9-9F6C-488DB2532149")){
				Assert.assertEquals(profession.getProfessionname(), "汉语言文学");
				return;
			}
		}
		Assert.assertEquals(1, 2);
	}
}
