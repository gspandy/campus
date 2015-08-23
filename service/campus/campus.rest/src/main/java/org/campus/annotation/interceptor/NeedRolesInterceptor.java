package org.campus.annotation.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.campus.annotation.NeedRoles;
import org.campus.constant.Constant;
import org.campus.core.exception.CampusException;
import org.campus.vo.LoginResponseVO;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class NeedRolesInterceptor extends HandlerInterceptorAdapter {

    private final static int NOTLOGIN = 1000001;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        NeedRoles annotation = method.getAnnotation(NeedRoles.class);
        if (annotation != null) {
            checkAuthorized(request.getSession(), annotation);
        }
        return super.preHandle(request, response, handler);
    }

    private void checkAuthorized(HttpSession session, NeedRoles annotation) {
        // 获取用户对象
        LoginResponseVO account = (LoginResponseVO) session.getAttribute(Constant.CAMPUS_SECURITY_SESSION);
        if (account == null) {
            throw new CampusException(NOTLOGIN, "未登录.");
        }
    }
}
