package com.skye.srms_backend.config;

import com.skye.srms_backend.utils.JwtValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private JwtValidateInterceptor jwtValidateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(jwtValidateInterceptor);

        interceptorRegistration.addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/signin",
                        "/user/signup",
                        "/user/signout"
                );
    }
}
