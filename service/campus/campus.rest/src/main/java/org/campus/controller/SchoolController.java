package org.campus.controller;

import java.util.ArrayList;
import java.util.List;

import org.campus.vo.CollegeVO;
import org.campus.vo.ProfessionVO;
import org.campus.vo.SchoolVO;
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

    @ApiOperation(value = "学校信息", notes = "学校信息")
    @RequestMapping(value = "/schools", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<SchoolVO> findSchools(
            @ApiParam(name = "schoolName", value = "学校名称，支持模糊匹配") @RequestParam(value = "schoolName", required = false) String schoolName,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        List<SchoolVO> schoolVOs = new ArrayList<SchoolVO>();
        SchoolVO schoolVO = new SchoolVO();
        schoolVO.setSchoolId("12312312");
        schoolVO.setSchoolName("北京大学");
        schoolVOs.add(schoolVO);
        Page<SchoolVO> page = new PageImpl<SchoolVO>(schoolVOs, pageable, schoolVOs.size());
        return page;
    }

    @ApiOperation(value = "院系信息", notes = "院系信息")
    @RequestMapping(value = "/{schoolId}/colleges", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<CollegeVO> findColleges(
            @ApiParam(name = "schoolId", value = "学校ID") @PathVariable String schoolId,
            @ApiParam(name = "collegeName", value = "院系名称，支持模糊匹配") @RequestParam(value = "collegeName", required = false) String collegeName,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        List<CollegeVO> collegeVOs = new ArrayList<CollegeVO>();
        CollegeVO collegeVO = new CollegeVO();
        collegeVO.setCollegeId("312312");
        collegeVO.setCollegeName("计算机学院");
        collegeVOs.add(collegeVO);
        Page<CollegeVO> page = new PageImpl<CollegeVO>(collegeVOs, pageable, collegeVOs.size());
        return page;
    }

    @ApiOperation(value = "院系信息", notes = "院系信息")
    @RequestMapping(value = "/{collegeId}/professions", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public Page<ProfessionVO> findProfessions(
            @ApiParam(name = "collegeId", value = "院系ID") @PathVariable String collegeId,
            @ApiParam(name = "collegeName", value = "院系名称，支持模糊匹配") @RequestParam(value = "collegeName", required = false) String collegeName,
            @ApiParam(name = "pageable", value = "分页信息,传参方式：?page=0&size=10") @PageableDefault(page = 0, size = 10) Pageable pageable,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId) {
        List<ProfessionVO> professionVOs = new ArrayList<ProfessionVO>();
        ProfessionVO professionVO = new ProfessionVO();
        professionVO.setProfessionId("3123121");
        professionVO.setProfessionName("网路工程");
        professionVOs.add(professionVO);
        Page<ProfessionVO> page = new PageImpl<ProfessionVO>(professionVOs, pageable, professionVOs.size());
        return page;
    }

}
