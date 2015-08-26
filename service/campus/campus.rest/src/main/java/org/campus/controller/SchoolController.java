package org.campus.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.campus.annotation.NeedRoles;
import org.campus.constant.Constant;
import org.campus.model.College;
import org.campus.model.Profession;
import org.campus.model.School;
import org.campus.model.User;
import org.campus.service.SchoolService;
import org.campus.service.SecurityService;
import org.campus.vo.CollegeVO;
import org.campus.vo.LoginResponseVO;
import org.campus.vo.ProfessionVO;
import org.campus.vo.SchoolVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/school")
@Api(value = "SchoolController", description = "院校相关操作")
public class SchoolController {

    @Autowired
    private SchoolService schoolSvc;
    
    @Autowired
    private SecurityService securitySvc;

    @ApiOperation(value = "*修改用户学校信息:1.0", notes = "*修改用户学校信息 [API-Version=1.0]")
    @RequestMapping(value = "/user/modify", headers = { "API-Version=1.0" }, method = RequestMethod.PUT)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    @NeedRoles
    public LoginResponseVO getUserSchool(
            @ApiParam(name = "schoolName", value = "学校名称") @RequestParam(value = "schoolName", required = true) String schoolName,
            HttpSession session) {
    	LoginResponseVO vo = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        
        User user = new User();
        user.setUseruid(vo.getUserId());
        user.setSchoolname(schoolName);
        securitySvc.updateUser(user);
        
        vo.setSchoolName(schoolName);
    	return vo;
    }

    @ApiOperation(value = "*学校信息:1.0", notes = "学校信息 [API-Version=1.0]")
    @RequestMapping(value = "/schools", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<SchoolVO> findSchools(
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<SchoolVO> schoolVOs = new ArrayList<SchoolVO>();

        Page<School> pageSchools = this.schoolSvc.getAllSchool(pageable);
        List<School> schools = pageSchools.getContent();
        for (School school : schools) {
            SchoolVO schoolVO = new SchoolVO();
            schoolVO.setSchoolId(school.getUid());
            schoolVO.setSchoolName(school.getSchoolname());
            schoolVO.setCityCode(school.getCitycode());
            schoolVO.setCityName(school.getCityname());
            schoolVO.setProvinceCode(school.getProvincecode());
            schoolVO.setProvinceName(school.getProvincename());
            schoolVOs.add(schoolVO);
        }

        Page<SchoolVO> page = new PageImpl<SchoolVO>(schoolVOs, pageable, schoolVOs.size());
        return page;
    }

    @ApiOperation(value = "*院系信息:1.0", notes = "院系信息 [API-Version=1.0]")
    @RequestMapping(value = "/{schoolId}/colleges", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public List<CollegeVO> findColleges(@ApiParam(name = "schoolId", value = "学校ID") @PathVariable String schoolId) {
        List<CollegeVO> collegeVOs = new ArrayList<CollegeVO>();

        List<College> colleges = this.schoolSvc.getSchoolCollege(schoolId);
        for (College college : colleges) {
            CollegeVO collegeVO = new CollegeVO();
            collegeVO.setCollegeId(college.getUid());
            collegeVO.setCollegeName(college.getCollegename());
            collegeVOs.add(collegeVO);
        }

        return collegeVOs;
    }

    @ApiOperation(value = "*专业信息:1.0", notes = "专业信息 [API-Version=1.0]")
    @RequestMapping(value = "/{schoolId}/{collegeId}/professions", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public List<ProfessionVO> findProfessions(
            @ApiParam(name = "collegeId", value = "院系ID") @PathVariable("collegeId") String collegeId,
            @ApiParam(name = "schoolId", value = "学校ID") @PathVariable("schoolId") String schoolId) {
        List<ProfessionVO> professionVOs = new ArrayList<ProfessionVO>();

        List<Profession> professions = this.schoolSvc.getCollegeProfession(collegeId, schoolId);
        for (Profession profession : professions) {
            ProfessionVO professionVO = new ProfessionVO();
            professionVO.setProfessionId(profession.getUid());
            professionVO.setProfessionName(profession.getProfessionname());
            professionVOs.add(professionVO);
        }
        return professionVOs;
    }

    @ApiOperation(value = "*入学年份:1.0", notes = "入学年份 [API-Version=1.0]")
    @RequestMapping(value = "/inSchoolYear", headers = { "API-Version=1.0" }, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public List<String> getInSchoolYear() {
        List<String> integers = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        int thisYear = cal.get(Calendar.YEAR);
        for (int i = 2006; i <= thisYear; i++) {
            integers.add(Integer.toString(i));
        }
        return integers;
    }

}
