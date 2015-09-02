package org.campus.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.campus.config.SystemConfig;
import org.campus.model.FreshNews;
import org.campus.model.Integral;
import org.campus.model.Signin;
import org.campus.model.User;
import org.campus.model.enums.IntegralType;
import org.campus.repository.CommentMapper;
import org.campus.repository.FreshNewsMapper;
import org.campus.repository.IntegralMapper;
import org.campus.repository.PostsCheckMapper;
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

    @Autowired
    private PostsCheckMapper postsCheckMapper;

    @Autowired
    private FreshNewsMapper freshNewsMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public long integral(String userId, String postId, IntegralType integralType) {
        long integral = 0;
        switch (integralType) {
            case LOGIN:
                integral = loginIntegral(userId);
                break;
            case POSTS:
                integral = postsIntegral(userId);
                break;
            case CHECk_POSTS:
                integral = checkIntegral(userId);
                break;
            case COMMENT:
                integral = commentIntegral(userId, postId);
                break;
            case SHARE:

            case TRANSFER:
                integral = 0;
                break;
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

    private long postsIntegral(String userId) {
        long integral = SystemConfig.getLong("POSTS_PASS_CHECK");
        User user = userMapper.selectByPrimaryKey(userId);
        Integral record = new Integral();
        record.setUid(ToolUtil.getUUid());
        record.setUserid(userId);
        record.setIntegraltype(IntegralType.POSTS);
        record.setIntegraltime(getDate(0));
        record.setIntegralincome(integral);
        record.setIntegralexpend(0L);
        record.setIntegralbalance((user.getIntegral() + record.getIntegralincome()) - record.getIntegralexpend());
        record.setRemark("发帖通过审核");
        integralMapper.insert(record);

        user.setIntegral(user.getIntegral() + integral);
        userMapper.updateByPrimaryKey(user);
        return integral;
    }

    private long checkIntegral(String userId) {
        long integral = 0;
        int count = postsCheckMapper.findIntradayCountByUserId(userId, getDate(0));
        if (count <= SystemConfig.getInt("CHECK_POSTS_UPPER_LIMIT")) {
            integral = SystemConfig.getLong("CHECK_POST_GET_INTEGRAL");
            User user = userMapper.selectByPrimaryKey(userId);
            Integral record = new Integral();
            record.setUid(ToolUtil.getUUid());
            record.setUserid(userId);
            record.setIntegraltype(IntegralType.CHECk_POSTS);
            record.setIntegraltime(getDate(0));
            record.setIntegralincome(integral);
            record.setIntegralexpend(0L);
            record.setIntegralbalance((user.getIntegral() + record.getIntegralincome()) - record.getIntegralexpend());
            record.setRemark("审帖获取积分");
            integralMapper.insert(record);

            user.setIntegral(user.getIntegral() + integral);
            userMapper.updateByPrimaryKey(user);
        }
        return integral;
    }

    private long commentIntegral(String userId, String postId) {
        long integral = 0;
        FreshNews fresh = freshNewsMapper.selectByPrimaryKey(postId);
        Date limitDate = getDate(SystemConfig.getInt("COMMENT_DAYS"));
        int count = commentMapper.selectNeedIntegralNum(postId, fresh.getCheckDate(), limitDate);
        int nowCount = count + 1;
        User user = userMapper.selectByPrimaryKey(userId);
        if (nowCount <= 50) {
            integral = SystemConfig.getLong("COMMENT_POST_GET_INTEGRAL");
            Integral record = new Integral();
            record.setUid(ToolUtil.getUUid());
            record.setUserid(userId);
            record.setIntegraltype(IntegralType.CHECk_POSTS);
            record.setIntegraltime(getDate(0));
            record.setIntegralincome(integral);
            record.setIntegralexpend(0L);
            record.setIntegralbalance((user.getIntegral() + record.getIntegralincome()) - record.getIntegralexpend());
            record.setRemark("审帖获取积分");
            integralMapper.insert(record);

            user.setIntegral(user.getIntegral() + integral);
            userMapper.updateByPrimaryKey(user);
        }
        if (nowCount >= SystemConfig.getInt("COMMENT_POST_ONE_OFF_NUM_1")
                && nowCount < SystemConfig.getInt("COMMENT_POST_ONE_OFF_NUM_2")) {
            integral = SystemConfig.getLong("COMMENT_POST_ONE_OFF_INTEGRAL_1");
            Integral record = new Integral();
            record.setUid(ToolUtil.getUUid());
            record.setUserid(userId);
            record.setIntegraltype(IntegralType.CHECk_POSTS);
            record.setIntegraltime(getDate(0));
            record.setIntegralincome(integral);
            record.setIntegralexpend(0L);
            record.setIntegralbalance((user.getIntegral() + record.getIntegralincome()) - record.getIntegralexpend());
            record.setRemark("审帖获取积分");
            integralMapper.insert(record);

            user.setIntegral(user.getIntegral() + integral);
            userMapper.updateByPrimaryKey(user);
        }
        if (nowCount >= SystemConfig.getInt("COMMENT_POST_ONE_OFF_NUM_2")) {
            integral = SystemConfig.getLong("COMMENT_POST_ONE_OFF_INTEGRAL_2");
            Integral record = new Integral();
            record.setUid(ToolUtil.getUUid());
            record.setUserid(userId);
            record.setIntegraltype(IntegralType.CHECk_POSTS);
            record.setIntegraltime(getDate(0));
            record.setIntegralincome(integral);
            record.setIntegralexpend(0L);
            record.setIntegralbalance((user.getIntegral() + record.getIntegralincome()) - record.getIntegralexpend());
            record.setRemark("审帖获取积分");
            integralMapper.insert(record);

            user.setIntegral(user.getIntegral() + integral);
            userMapper.updateByPrimaryKey(user);
        }
        return integral;
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
