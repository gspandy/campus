package org.campus.controller;

import javax.servlet.http.HttpSession;

import org.campus.util.ToolUtil;
import org.campus.vo.LoginRequestVO;
import org.campus.vo.LoginResponseVO;
import org.campus.vo.RegisterVO;
import org.campus.vo.VerifyCodeReqVO;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/security")
@Api(value = "SecurityController", description = "登录、注册、退出等相关操作")
public class SecurityController {

    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "登录成功"), @ApiResponse(code = 500, message = "内部处理错误"),
            @ApiResponse(code = 1000002, message = "登录失败") })
    public LoginResponseVO login(@ApiParam(name = "loginVO", value = "登录信息体") @RequestBody LoginRequestVO loginVO) {
        LoginResponseVO responseVO = new LoginResponseVO();
        responseVO.setSignId(ToolUtil.getUUid());
        return responseVO;
    }

    @ApiOperation(value = "注册", notes = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "注册成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void register(@ApiParam(name = "registerVO", value = "注册信息体") @RequestBody RegisterVO registerVO) {

    }

    @ApiOperation(value = "忘记密码", notes = "忘记密码")
    @RequestMapping(value = "/password/reset", method = RequestMethod.PUT)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "修改密码成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void forgetPassword(
            @ApiParam(name = "loginName", value = "登录号") @RequestParam(value = "loginName", required = true) String loginName,
            @ApiParam(name = "password", value = "新密码") @RequestParam(value = "password", required = true) String password,
            @ApiParam(name = "checkCode", value = "短信验证码") @RequestParam(value = "checkCode", required = true) String checkCode) {

    }

    @ApiOperation(value = "修改手机号码", notes = "修改手机号码")
    @RequestMapping(value = "/phone/change", method = RequestMethod.PUT)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "修改手机号码成功"),
            @ApiResponse(code = 500, message = "内部处理错误") })
    public void changePhone(
            @ApiParam(name = "phone", value = "老手机号码") @RequestParam(value = "phone", required = true) String phone,
            @ApiParam(name = "newPhone", value = "新手机号码") @RequestParam(value = "newPhone", required = true) String newPhone,
            @ApiParam(name = "checkCode", value = "验证码") @RequestParam(value = "checkCode", required = false) String checkCode,
            HttpSession session) {

    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequestMapping(value = "/password/change", method = RequestMethod.PUT)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "修改密码成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void changePassword(
            @ApiParam(name = "password", value = "老密码") @RequestParam(value = "password", required = true) String password,
            @ApiParam(name = "newPassword", value = "新密码") @RequestParam(value = "newPassword", required = true) String newPassword,
            @ApiParam(name = "newSecPassword", value = "新密码第二次输入") @RequestParam(value = "newSecPassword", required = true) String newSecPassword,
            HttpSession session) {

    }

    @ApiOperation(value = "设置、修改昵称", notes = "设置、修改昵称")
    @RequestMapping(value = "/nickname/set", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "设置、修改昵称成功"),
            @ApiResponse(code = 500, message = "内部处理错误") })
    public void setNickName(
            @ApiParam(name = "nickName", value = "昵称") @RequestParam(value = "nickName", required = true) String nickName,
            HttpSession session) {

    }

    @ApiOperation(value = "验证手机验证码", notes = "验证手机验证码")
    @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "验证手机验证码成功"),
            @ApiResponse(code = 500, message = "内部处理错误") })
    public void verifyCode(
            @ApiParam(name = "verifyCodeReqVO", value = "验证验证码请求信息") @RequestBody VerifyCodeReqVO verifyCodeReqVO) {

    }

    @ApiOperation(value = "获取短信验证码", notes = "获取短信验证码")
    @RequestMapping(value = "/checkCode", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "获取短信验证码成功"),
            @ApiResponse(code = 500, message = "内部处理错误") })
    public void getCheckCode(
            @ApiParam(name = "phone", value = "手机号码") @RequestParam(value = "phone", required = true) String phone,
            @ApiParam(name = "type", value = "短信验证码类型(1.注册短信;2.找回密码;3.修改手机号)") @RequestParam(value = "type", required = true) String type) {

    }

    @ApiOperation(value = "登出", notes = "登出")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "登出成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void logout(HttpSession session) {
    }

}
