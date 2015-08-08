package org.campus.controller;

import org.campus.util.ToolUtil;
import org.campus.vo.LoginRequestVO;
import org.campus.vo.LoginResponseVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
    @ApiOperation(value = "学校信息", notes = "登录")
    @RequestMapping(value = "/schools", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误")})
    public LoginResponseVO login(@ApiParam(name = "loginVO", value = "登录信息体") @RequestBody LoginRequestVO loginVO) {
        LoginResponseVO responseVO = new LoginResponseVO();
        responseVO.setSignId(ToolUtil.getUUid());
        return responseVO;
    }

}
