package org.campus.service;

import org.campus.core.exception.CampusException;
import org.campus.model.SysUser;
import org.campus.model.User;
import org.campus.repository.SysUserMapper;
import org.campus.repository.UserMapper;
import org.campus.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注册服务实现。包括修改密码，用户资料变更等服务提供；
 * @author Medeson.Zh
 *
 */
@Service
public class SecurityService {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 用户注册
	 * @param sysUser
	 * @param appUser
	 */
	@Transactional
	public void registe(SysUser sysUser,User appUser){
		//sys_user操作
		sysUserMapper.insert(sysUser);
		//app_user操作
		userMapper.insert(appUser);
	}
	
	/**
	 * 检查登录号和密码是否匹配
	 * @param loginName
	 * @param password
	 * @return
	 */
	public SysUser checkUserAndPassword(String loginName,String password){
		SysUser sysUser = getUserByAccount(loginName);
		if(sysUser == null){
			throw new CampusException(100002,"用户未注册");
		}
		
		String md5Passwd = MD5Util.encrypt(password);
		if(!md5Passwd.equals(sysUser.getUserpwd())){
			throw new CampusException(100002,"用户名或者密码错误");
		}
		return sysUser;
	}
	
	/**
	 * 根据登录号获取有效用户信息
	 * @param loginName
	 * @return
	 */
	public SysUser getUserByAccount(String loginName){
		return sysUserMapper.selectByAccount(loginName);
	}
	
	/**
	 * 查询用户的基本信息
	 * @param uid
	 * @return
	 */
	public User getAppUserInfo(String uid){
		return userMapper.selectByPrimaryKey(uid);
	}
	
	/**
	 * 验证昵称是否已经被占用
	 * @param nickName
	 * @return
	 */
	public boolean nickNameExsit(String nickName){
		User usr = userMapper.selectByNickName(nickName);
		if(usr != null) return true;
		return false;
	}
	
	/**
	 * 更新 sysUser 表
	 * @param user
	 */
	public void updateSysUser(SysUser user){
		sysUserMapper.updateByPrimaryKeySelective(user);
	}
	
	/**
	 * 更新appUser表
	 * @param user
	 */
	public void updateUser(User user){
		userMapper.updateByPrimaryKeySelective(user);
	}
}
