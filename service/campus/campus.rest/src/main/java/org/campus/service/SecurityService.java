package org.campus.service;

import java.util.Calendar;
import java.util.Date;

import org.campus.api.TencentApi;
import org.campus.api.WeiboApi;
import org.campus.api.WeixinApi;
import org.campus.api.domain.QqUserinfo;
import org.campus.api.domain.SnsapiUserinfo;
import org.campus.api.domain.WeiboUserInfo;
import org.campus.cache.RedisCache;
import org.campus.config.SystemConfig;
import org.campus.core.exception.CampusException;
import org.campus.model.Install;
import org.campus.model.SysUser;
import org.campus.model.User;
import org.campus.model.Version;
import org.campus.model.enums.ApiType;
import org.campus.repository.InstallMapper;
import org.campus.repository.SysUserMapper;
import org.campus.repository.UserMapper;
import org.campus.repository.VersionMapper;
import org.campus.util.MD5Util;
import org.campus.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注册服务实现。包括修改密码，用户资料变更等服务提供；
 * 
 * @author Medeson.Zh
 *
 */
@Service
public class SecurityService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeixinApi weixinApi;

    @Autowired
    private TencentApi tencentApi;

    @Autowired
    private WeiboApi weiboApi;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private InstallMapper installMapper;

    @Autowired
    private VersionMapper versionMapper;

    /**
     * 用户注册
     * 
     * @param sysUser
     * @param appUser
     */
    @Transactional
    public void registe(SysUser sysUser, User appUser) {
        SysUser user = sysUserMapper.selectByAccount(sysUser.getUseraccount());
        if (user != null) {
            throw new CampusException(100003, "该用户已注册");
        }
        // sys_user操作
        sysUserMapper.insert(sysUser);
        // app_user操作
        userMapper.insert(appUser);
    }

    /**
     * 检查登录号和密码是否匹配
     * 
     * @param loginName
     * @param password
     * @return
     */
    public SysUser checkUserAndPassword(String loginName, String password) {
        SysUser sysUser = getUserByAccount(loginName);
        if (sysUser == null) {
            throw new CampusException(100002, "用户未注册");
        }

        String md5Passwd = MD5Util.encrypt(password);
        if (!md5Passwd.equals(sysUser.getUserpwd())) {
            throw new CampusException(100002, "用户名或者密码错误");
        }
        return sysUser;
    }

    /**
     * 根据登录号获取有效用户信息
     * 
     * @param loginName
     * @return
     */
    public SysUser getUserByAccount(String loginName) {
        return sysUserMapper.selectByAccount(loginName);
    }

    /**
     * 查询用户的基本信息
     * 
     * @param uid
     * @return
     */
    public User getAppUserInfo(String uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    /**
     * 验证昵称是否已经被占用
     * 
     * @param nickName
     * @return
     */
    public boolean nickNameExsit(String nickName) {
        User usr = userMapper.selectByNickName(nickName);
        if (usr != null)
            return true;
        return false;
    }

    /**
     * 更新 sysUser 表
     * 
     * @param user
     */
    public void updateSysUser(SysUser user) {
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 更新appUser表
     * 
     * @param user
     */
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public User apiLogin(String accessToken, String openId, ApiType apiType) {
        User user = null;
        switch (apiType) {
            case QQ:
                QqUserinfo qqUserinfo = null;
                try {
                    qqUserinfo = tencentApi.getQqUserinfo(accessToken, openId);
                    user = apiRegist(apiType, qqUserinfo.getOpenId(), qqUserinfo.getNickname(),
                            qqUserinfo.getFigureurl_qq_2());
                } catch (Exception e) {
                    throw new CampusException(1000002, "登录失败", e);
                }
                break;
            case WEIBO:
                WeiboUserInfo weiboUserinfo = null;
                try {
                    weiboUserinfo = weiboApi.getWeiboUserinfo(accessToken, openId);
                    user = apiRegist(apiType, String.valueOf(weiboUserinfo.getId()), weiboUserinfo.getScreen_name(),
                            weiboUserinfo.getProfile_image_url());
                } catch (Exception e) {
                    throw new CampusException(1000002, "登录失败", e);
                }
                break;
            case WEIXIN:
                SnsapiUserinfo snsapiUserInfo = null;
                try {
                    snsapiUserInfo = weixinApi.getSnsapiUserinfo(accessToken, openId);
                    user = apiRegist(apiType, snsapiUserInfo.getOpenid(), snsapiUserInfo.getNickname(),
                            snsapiUserInfo.getHeadimgurl());
                } catch (Exception e) {
                    throw new CampusException(1000002, "登录失败", e);
                }
                break;
            default:
                break;
        }

        return user;
    }

    public void loginFailCount(String loginName) {
        redisCache.setCacheTime(86400);
        Object value = redisCache.getValue(loginName + "_login_fail_count");
        int count = 1;
        if (value != null) {
            count = (int) value + 1;
        }
        redisCache.cache(loginName + "_login_fail_count", count);
    }

    public boolean isNeedVerifyCode(String loginName) {
        boolean flag = false;
        Object value = redisCache.getValue(loginName + "_login_fail_count");
        if (value != null) {
            if ((int) value >= SystemConfig.getInt("LOGIN_FAILED_NEED_VERIFY_CODE")) {
                flag = true;
            }
        }
        return flag;
    }

    public void andriodInstall(String source, String sourceName) {
        Install install = installMapper.findBySource(source);
        if (install != null) {
            install.setCount(install.getCount() + 1);
            installMapper.updateCount(install);
        } else {
            Install record = new Install();
            record.setUid(ToolUtil.getUUid());
            record.setSource(source);
            record.setSourceName(sourceName);
            record.setCount(1);
            record.setCreatedate(new Date());
            installMapper.insert(record);
        }
    }

    public Version getVersion(Integer type) {
        return versionMapper.findByTypeCode(type);
    }

    private User apiRegist(ApiType apiType, String apiId, String nickName, String headImgUrl) {
        User user = userMapper.findByApiId(apiId);
        if (user == null) {
            SysUser sysUser = new SysUser();
            sysUser.setUid(ToolUtil.getUUid());
            sysUser.setIscheck(1);
            sysUser.setCreatedate(Calendar.getInstance().getTime());
            sysUser.setIsactive(1);
            User appUser = new User();
            appUser.setUseruid(sysUser.getUid());
            appUser.setNickname(nickName);
            appUser.setCreatedate(sysUser.getCreatedate());
            appUser.setIsactive(1);
            appUser.setIsgraduate(0);
            appUser.setIslocked(0);
            appUser.setIsopen(1);
            appUser.setIsvalidated(0);
            appUser.setIntegral(0L);
            appUser.setLogincount(0);
            appUser.setApiType(apiType);
            appUser.setApiId(apiId);
            appUser.setHeadpic(headImgUrl);
            registe(sysUser, appUser);
            user = appUser;
        }
        return user;
    }

}
