package com.nice.nicebaby.interceptor;

import com.nice.nicebaby.entity.User;
import com.nice.nicebaby.exception.JWTException;
import com.nice.nicebaby.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User checkLoginResult = jwtUtil.verifierToken(request.getHeader("Authorization"));
        if (checkLoginResult == null) {
            throw new JWTException();
        }
        request.setAttribute("LoginInfo", checkLoginResult);
        return true;
    }

}
