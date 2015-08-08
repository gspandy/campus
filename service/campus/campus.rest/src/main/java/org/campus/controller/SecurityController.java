package org.campus.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campus.constant.Constant;
import org.campus.constant.ErrorCode;
import org.campus.core.exception.CampusException;
import org.campus.util.ToolUtil;
import org.campus.util.VerificationCode;
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
            @ApiParam(name = "secPassword", value = "第二次密码") @RequestParam(value = "secPassword", required = true) String secPassword) {

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

    @ApiOperation(value = "图片验证码生成", notes = "图片验证码生成")
    @RequestMapping(value = "/randomCode", method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "验证码生成成功"),
            @ApiResponse(code = 1100001, message = "验证码生成失败"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void createCode(HttpServletRequest request, HttpServletResponse response) {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        VerificationCode code = new VerificationCode(120, 40, 4, 100);
        session.setAttribute(Constant.VERFICATION_CODE, code.getCode());
        try {
            code.write(response.getOutputStream());
        } catch (IOException e) {
            throw new CampusException(ErrorCode.VERFICATION_CODE_ERROR, "验证码生成失败");
        }
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
