package com.nice.nicebaby.interceptor;

import com.nice.nicebaby.entity.User;
import com.nice.nicebaby.exception.JWTException;
import com.nice.nicebaby.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(JWTInterceptor.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[Request URI：{}]", request.getRequestURI());
        User checkLoginResult = jwtUtil.verifierToken(request.getHeader("Authorization"));
        if (checkLoginResult == null) {
            throw new JWTException();
        }
        request.setAttribute("LoginInfo", checkLoginResult);
        return true;
    }

}
