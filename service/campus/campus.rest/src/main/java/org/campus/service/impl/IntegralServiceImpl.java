package org.campus.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.campus.config.SystemConfig;
import org.campus.model.Integral;
import org.campus.model.Signin;
import org.campus.model.User;
import org.campus.model.enums.IntegralType;
import org.campus.repository.IntegralMapper;
import org.campus.repository.SigninMapper;
import org.campus.repository.UserMapper;
import org.campus.service.IntegralService;
import org.campus.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SigninMapper signinMapper;

    @Autowired
    private IntegralMapper integralMapper;

    @Override
    public long integral(String userId, IntegralType integralType) {
        long integral = 0;
        switch (integralType) {
            case LOGIN:
                integral = loginIntegral(userId);
                break;
            case POSTS:
                integral = postsIntegral();
                break;
            case CHECk_POSTS:

            case COMMENT:

            case SHARE:

            case TRANSFER:

            default:
                integral = 0;
                break;

        }
        return integral;
    }

    private long loginIntegral(String userId) {
        long integral = 0;
        // 查询当天是否领取过
        Signin todayData = signinMapper.findDayByUserId(userId, getDate(0));
        if (todayData == null) {
            User user = userMapper.selectByPrimaryKey(userId);
            // 查询前一天是否登录过
            Signin dayBeforeData = signinMapper.findDayByUserId(userId, getDate(-1));
            if (dayBeforeData == null) {
                user.setIntegral(integral);
                user.setLogincount(0);
            }

            integral = calcLoginIntegral(SystemConfig.getLong("FIRST_LOGIN_INTEGRAL"), user.getLogincount() + 1);
            Signin signin = new Signin();
            signin.setUid(ToolUtil.getUUid());
            signin.setUserid(userId);
            signin.setLogindate(getDate(0));
            signinMapper.insert(signin);

            Integral record = new Integral();
            record.setUid(ToolUtil.getUUid());
            record.setUserid(userId);
            record.setIntegraltype(IntegralType.LOGIN);
            record.setIntegraltime(getDate(0));
            record.setIntegralincome(integral);
            record.setIntegralexpend(0L);
            record.setIntegralbalance((user.getIntegral() + record.getIntegralincome()) - record.getIntegralexpend());
            record.setRemark("连续" + (user.getLogincount() + 1) + "天登录积分奖励");
            integralMapper.insert(record);

            user.setIntegral(user.getIntegral() + integral);
            user.setLogincount(user.getLogincount() + 1);
            userMapper.updateByPrimaryKey(user);
        }
        return integral;
    }

    private long postsIntegral() {
        return SystemConfig.getLong("POSTS_PASS_CHECK");
    }

    private long calcLoginIntegral(long integral, int count) {
        return integral + SystemConfig.getLong("EVERYDAY_INCREASE_INTEGRAL") * (count - 1);
    }

    private Date getDate(int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, amount);
        return cal.getTime();
    }

}
