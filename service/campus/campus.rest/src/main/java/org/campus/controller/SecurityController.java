package org.campus.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campus.config.SystemConfig;
import org.campus.constant.Constant;
import org.campus.constant.ErrorCode;
import org.campus.core.exception.CampusException;
import org.campus.model.SysUser;
import org.campus.model.User;
import org.campus.model.enums.DisplayModel;
import org.campus.model.enums.SMSType;
import org.campus.service.SecurityService;
import org.campus.service.SendMessage;
import org.campus.util.MD5Util;
import org.campus.util.ToolUtil;
import org.campus.util.VerificationCode;
import org.campus.vo.LoginRequestVO;
import org.campus.vo.LoginResponseVO;
import org.campus.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
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

	@Autowired
	private SecurityService securitySvc;
	
	@Autowired
	private SendMessage sendSmsSvc;
	
    @ApiOperation(value = "*登录:1.0", notes = "登录[API-Version=1.0]")
    @RequestMapping(value = "/login", headers={"API-Version=1.0"}, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "登录成功"), @ApiResponse(code = 500, message = "内部处理错误"),
            @ApiResponse(code = 1000002, message = "登录失败") })
    public LoginResponseVO login(@ApiParam(name = "loginVO", value = "登录信息体") @RequestBody LoginRequestVO loginVO,HttpSession session) {
    	Assert.notNull(loginVO,"请求参数错误.");
    	Assert.notNull(loginVO.getLoginName(),"用户名不允许为空.");
    	Assert.notNull(loginVO.getPassword(),"请输入正确的用户密码.");
    	if(session.getAttribute(Constant.VERFICATION_CODE)!=null){
    		Assert.notNull(loginVO.getVerificationCode(),"验证码不允许为空.");
    		if(!loginVO.getVerificationCode().equals(session.getAttribute(Constant.VERFICATION_CODE))){
    			throw new CampusException(100002,"验证码错误.");
    		}
    	}
    	SysUser sysUser = securitySvc.checkUserAndPassword(loginVO.getLoginName(), loginVO.getPassword());
    	//创建SignId
    	sysUser.setSignid(ToolUtil.getUUid());
        LoginResponseVO responseVO = new LoginResponseVO();
        responseVO.setSignId(sysUser.getSignid());
        responseVO.setUserId(sysUser.getUid());
        responseVO.setUserAccount(sysUser.getUseraccount());
        
        User user = securitySvc.getAppUserInfo(sysUser.getUid());
//        responseVO.setCollegeId(user.getCollegeuid());
//        responseVO.setCollegeName(user.getCollegename());
        responseVO.setHeadPic(user.getHeadpic());
//        responseVO.setInSchoolYear(user.getInschoolyear());
        responseVO.setNickName(user.getNickname());
//        responseVO.setProfessionId(user.getProfessionuid());
//        responseVO.setProfessionName(user.getProfessionname());
//        responseVO.setSchoolId(user.getSchooluid());
//        responseVO.setSchoolName(user.getSchoolname());
        
        session.setAttribute(Constant.CAMPUS_SECURITY_SESSION, responseVO);
        
        //默认设置为白天模式:
        session.setAttribute(Constant.CAMPUS_DISPLAYMODEL, DisplayModel.SUN);
        
        //修改最后登录时间
        SysUser udpUser = new SysUser();
        udpUser.setUid(sysUser.getUid());
        udpUser.setLastlogintime(Calendar.getInstance().getTime());
        udpUser.setSignid(sysUser.getSignid());
        securitySvc.updateSysUser(udpUser);
        
        return responseVO;
    }

    
    @ApiOperation(value = "*检查昵称是否可用:1.0", notes = "检查昵称是否可用[API-Version=1.0]")
    @RequestMapping(value = "/nickNameUseable", headers={"API-Version=1.0"}, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 500, message = "内部处理错误")})
    public boolean checkNickNameUseabled(@ApiParam(name = "nickname", value = "昵称") @RequestParam("nickname") String nickName){
    	Assert.notNull(nickName,"请输入有效的昵称.");
    	return !securitySvc.nickNameExsit(nickName);
    }
    
    @ApiOperation(value = "*注册:1.0", notes = "注册[API-Version=1.0]")
    @RequestMapping(value = "/register", headers={"API-Version=1.0"}, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "注册成功"), @ApiResponse(code = 500, message = "内部处理错误"),
    		@ApiResponse(code = 1000003, message = "注册失败")})
    public void register(@ApiParam(name = "registerVO", value = "注册信息体") @RequestBody RegisterVO registerVO,HttpSession session) {
    	Assert.notNull(registerVO,"参数错误");
    	Assert.hasLength(registerVO.getLoginName(),"用户名不允许为空.");
    	Assert.hasLength(registerVO.getPassword(),"请输入正确的密码.");
    	Assert.hasLength(registerVO.getSecPassword(),"请输入正确的密码.");
    	if(!registerVO.getPassword().equals(registerVO.getSecPassword())){
    		throw new CampusException(100003,"请输入正确的密码.");
    	}
//    	Assert.notNull(registerVO.getSchoolId(),"请选择学校.");
//    	Assert.notNull(registerVO.getCollegeId(),"请选择院系.");
//    	Assert.notNull(registerVO.getProfessionId(),"请选择专业.");
//    	Assert.notNull(registerVO.getInSchoolYear(),"请选择入学年份.");
    	Assert.hasLength(registerVO.getMobileCheckCode(),"请输入短信验证码");
    	
    	//SMS验证码检查
    	if(!registerVO.getMobileCheckCode().equals((String)session.getAttribute(Constant.SMS_CHECKCODE))){
    		throw new CampusException(100003,"短信验证码错误.");
    	}
    	
//    	//昵称唯一验证
//    	if(StringUtils.hasText(registerVO.getNickName()) && securitySvc.nickNameExsit(registerVO.getNickName())){
//    		throw new CampusException(100003,"昵称不可用.");
//    	}
    	
    	SysUser sysUser = new SysUser();
    	sysUser.setUseraccount(registerVO.getLoginName());
    	sysUser.setUid(ToolUtil.getUUid());
    	sysUser.setUserpwd(MD5Util.encrypt(registerVO.getPassword()));
    	sysUser.setIscheck(1);
//    	sysUser.setSignid(ToolUtil.getUUid());
    	sysUser.setCreatedate(Calendar.getInstance().getTime());
    	sysUser.setIsactive(1);
    	
    	User appUser = new User();
    	appUser.setUseruid(sysUser.getUid());
//    	appUser.setCitycode(registerVO.getCityCode());
//    	appUser.setCityname(registerVO.getCityName());
//    	appUser.setCollegeuid(registerVO.getCollegeId());
//    	appUser.setCollegename(registerVO.getCollegeName());
    	appUser.setCreatedate(sysUser.getCreatedate());
//    	appUser.setInschoolyear(registerVO.getInSchoolYear());
    	appUser.setIsactive(1);
//    	appUser.setSextype(registerVO.getSex());
//    	appUser.setSchooluid(registerVO.getSchoolId());
//    	appUser.setSchoolname(registerVO.getSchoolName());
//    	appUser.setProfessionuid(registerVO.getProfessionId());
//    	appUser.setProfessionname(registerVO.getProfessionName());
//    	appUser.setProvincecode(registerVO.getProvinceCode());
//    	appUser.setProvincename(registerVO.getProvinceName());
    	appUser.setIsgraduate(0);
    	appUser.setIslocked(0);
    	appUser.setIsopen(1);
    	appUser.setIsvalidated(0);
//    	appUser.setNickname(registerVO.getNickName());
    	
    	this.securitySvc.registe(sysUser, appUser);
    	
    }

    @ApiOperation(value = "*忘记密码:1.0", notes = "忘记密码[API-Version=1.0]")
    @RequestMapping(value = "/password/reset", headers={"API-Version=1.0"}, method = RequestMethod.PUT)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "修改密码成功"), @ApiResponse(code = 500, message = "内部处理错误"),
    		@ApiResponse(code = 1000004, message = "找回密码失败") })
    public void forgetPassword(
            @ApiParam(name = "loginName", value = "登录号") @RequestParam(value = "loginName", required = true) String loginName,
            @ApiParam(name = "checkCode", value = "验证码") @RequestParam(value = "checkCode", required = true) String checkCode,
            @ApiParam(name = "password", value = "新密码") @RequestParam(value = "password", required = true) String password,
            @ApiParam(name = "secPassword", value = "第二次密码") @RequestParam(value = "secPassword", required = true) String secPassword,
            HttpSession session) {
    	Assert.notNull(password,"请输入正确的密码.");
    	Assert.notNull(secPassword,"请输入正确的密码.");
    	Assert.notNull(checkCode,"请输入短信验证码.");
    	Assert.notNull(loginName,"请输入登录号.");
    	
    	if(password.equals(secPassword))
    		throw new CampusException(100004,"请输入正确的密码.");
    	
    	if (!checkCode.equals((String)session.getAttribute(Constant.SMS_CHECKCODE)))
    		throw new CampusException(100004,"短信验证码错误.");
    	
    	SysUser sysUser = securitySvc.getUserByAccount(loginName);
    	SysUser udpUser = new SysUser();
    	udpUser.setUid(sysUser.getUid());
    	udpUser.setUserpwd(MD5Util.encrypt(password));
    	
    	securitySvc.updateSysUser(udpUser);
    }

    @ApiOperation(value = "*修改手机号码:1.0", notes = "修改手机号码[API-Version=1.0]")
    @RequestMapping(value = "/phone/change", headers={"API-Version=1.0"}, method = RequestMethod.PUT)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "修改手机号码成功"),@ApiResponse(code = 500, message = "内部处理错误"),
    		@ApiResponse(code = 1000007, message = "修改密码失败") })
    public void changePhone(
            @ApiParam(name = "phone", value = "老手机号码") @RequestParam(value = "phone", required = true) String phone,
            @ApiParam(name = "newPhone", value = "新手机号码") @RequestParam(value = "newPhone", required = true) String newPhone,
            @ApiParam(name = "checkCode", value = "验证码") @RequestParam(value = "checkCode", required = false) String checkCode,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
    	Assert.notNull(phone,"请输入旧手机号.");
    	Assert.notNull(newPhone,"请输入新的手机号码.");
    	Assert.notNull(checkCode,"请输入正确的手机验证码.");
    	
    	if(phone.equals(newPhone))
    		throw new CampusException(100007,"新旧手机号码不能相同.");
    	
    	LoginResponseVO vo = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
    	if(vo == null)
    		throw new CampusException(100007,"请登录.");
    	
    	if (!checkCode.equals((String)session.getAttribute(Constant.SMS_CHECKCODE)))
    		throw new CampusException(100007,"短信验证码错误.");
    	
    	//如果新手机号码已经存在，则置为失效;
    	SysUser sysUser = securitySvc.getUserByAccount(newPhone);
    	if(sysUser!=null){
    		SysUser userTmp = new SysUser();
    		userTmp.setUid(sysUser.getUid());
    		userTmp.setIsactive(0);
    		userTmp.setLastupdatedate(Calendar.getInstance().getTime());
    		securitySvc.updateSysUser(userTmp);
    	}
    	
    	//修改手机号码
    	sysUser = new SysUser();
    	sysUser.setUid(vo.getUserId());
    	sysUser.setUseraccount(newPhone);
    	sysUser.setLastupdatedate(Calendar.getInstance().getTime());
    	securitySvc.updateSysUser(sysUser);
    	
    }

    @ApiOperation(value = "*修改密码:1.0", notes = "修改密码[API-Version=1.0]")
    @RequestMapping(value = "/password/change", headers={"API-Version=1.0"}, method = RequestMethod.PUT)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "修改密码成功"), @ApiResponse(code = 500, message = "内部处理错误"),
    		@ApiResponse(code = 1000005, message = "修改密码失败") })
    public void changePassword(
            @ApiParam(name = "oldPassword", value = "新密码") @RequestParam(value = "oldPassword", required = true) String oldPassword,
            @ApiParam(name = "newPassword", value = "新密码") @RequestParam(value = "newPassword", required = true) String newPassword,
            @ApiParam(name = "newSecPassword", value = "新密码第二次输入") @RequestParam(value = "newSecPassword", required = true) String newSecPassword,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
    	Assert.notNull(newPassword,"请输入正确的密码.");
    	Assert.notNull(newSecPassword,"请输入正确的密码.");
    	Assert.notNull(oldPassword,"需要提供旧密码.");
    	if(newPassword.equals(newSecPassword))
    		throw new CampusException(100005,"请输入正确的密码.");
    	
    	LoginResponseVO vo = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
    	if(vo == null)
    		throw new CampusException(100005,"请登录.");

    	//验证旧密码的正确性
    	SysUser sysUser = securitySvc.checkUserAndPassword(vo.getUserAccount(), oldPassword);
    	SysUser udpUser = new SysUser();
    	udpUser.setUid(sysUser.getUid());
    	udpUser.setUserpwd(MD5Util.encrypt(newPassword));
    	
    	securitySvc.updateSysUser(udpUser);
    }

    @ApiOperation(value = "*设置、修改昵称:1.0", notes = "设置、修改昵称[API-Version=1.0]")
    @RequestMapping(value = "/nickname/set", headers={"API-Version=1.0"}, method = RequestMethod.POST)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "设置、修改昵称成功"),@ApiResponse(code = 500, message = "内部处理错误"),
    		@ApiResponse(code = 1000006, message = "修改昵称失败") })
    public void setNickName(
            @ApiParam(name = "nickName", value = "昵称") @RequestParam(value = "nickName", required = true) String nickName,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {
    	Assert.notNull(nickName,"请输入正确的密码.");
    	LoginResponseVO vo = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
    	if(vo == null)
    		throw new CampusException(100006,"请登录.");
    	
    	//昵称唯一验证
    	if(securitySvc.nickNameExsit(nickName)){
    		throw new CampusException(100006,"昵称不可用.");
    	}
    	
    	User user = new User();
    	user.setUseruid(vo.getUserId());
    	user.setNickname(nickName);
    	
    	securitySvc.updateUser(user);
    }

    public void setSignature(
            @ApiParam(name = "signature", value = "个性签名") @RequestParam(value = "signature", required = true) String signature,
            @ApiParam(name = "signId", value = "登录返回的唯一signId") @RequestParam(value = "signId", required = true) String signId,
            HttpSession session) {

    	//TODO:待完成;个性签名功能暂时不需实现。等需要时再做。
    	
    }

    @ApiOperation(value = "*图片验证码生成:1.0", notes = "图片验证码生成[API-Version=1.0]")
    @RequestMapping(value = "/randomCode", headers={"API-Version=1.0"}, method = RequestMethod.GET)
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

    @ApiOperation(value = "*获取短信验证码:1.0", notes = "获取短信验证码[API-Version=1.0]")
    @RequestMapping(value = "/checkCode", headers={"API-Version=1.0"}, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "获取短信验证码成功"),
            @ApiResponse(code = 500, message = "内部处理错误") })
    public void getCheckCode(
            @ApiParam(name = "phone", value = "手机号码") @RequestParam(value = "phone", required = true) String phone,
            @ApiParam(name = "type", value = "短信验证码类型(1.注册短信;2.找回密码;3.修改手机号)") @RequestParam(value = "type", required = true) String type,
            HttpSession session) {
    	Assert.notNull(phone,"手机号码不能为空.");
    	Assert.notNull(type,"请指定短信验证码类型.");
    	String checkCode = ToolUtil.getSmsCheckColde();
    	String smsTemplate = SystemConfig.getString("SMS_CHECK_CODE_TEMPLATE");
    	String smsContent = String.format(smsTemplate, checkCode);
    	sendSmsSvc.sendMessage(phone, smsContent, SMSType.getSMSTypeByCode(type));
    	session.setAttribute(Constant.SMS_CHECKCODE, checkCode);
    }

    @ApiOperation(value = "*登出:1.0", notes = "登出[API-Version=1.0]")
    @RequestMapping(value = "/logout", headers={"API-Version=1.0"}, method = RequestMethod.GET)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "登出成功"), @ApiResponse(code = 500, message = "内部处理错误") })
    public void logout(HttpSession session) {
    	session.invalidate();
    }

}
