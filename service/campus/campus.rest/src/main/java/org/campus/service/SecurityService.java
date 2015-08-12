package org.campus.service;

import org.campus.vo.LoginRequestVO;
import org.campus.vo.LoginResponseVO;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    public LoginResponseVO handleLogin(LoginRequestVO requestVo){
        LoginResponseVO loginResponseVO = new LoginResponseVO();
        
        return loginResponseVO;
    }
}
