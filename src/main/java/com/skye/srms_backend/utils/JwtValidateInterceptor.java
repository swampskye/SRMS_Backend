package com.skye.srms_backend.utils;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtValidateInterceptor implements HandlerInterceptor {
    @Autowired
    private Jwt jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug(request.getRequestURL()+"--------------need check");
        String token = request.getHeader("token");
        if (token != null){
            try {
                jwt.parseToken(token);
                log.debug(request.getRequestURL()+"-----------check passed");
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        log.debug(request.getRequestURL()+"--------------check failed");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.fail("jwt is invalid")));
        return false;
    }
}
