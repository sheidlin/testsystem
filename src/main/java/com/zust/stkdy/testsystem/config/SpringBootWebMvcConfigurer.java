package com.zust.stkdy.testsystem.config;

import com.zust.stkdy.testsystem.config.annotation.TokenToStudent;
import com.zust.stkdy.testsystem.config.handler.TokenToStudentMethodArgumentReslover;
import com.zust.stkdy.testsystem.config.handler.TokenToTeacherMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
@Configuration
public class SpringBootWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private TokenToTeacherMethodArgumentResolver tokenToTeacherMethodArgumentResolver;
    @Autowired
    private TokenToStudentMethodArgumentReslover tokenToStudentMethodArgumentReslover;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tokenToTeacherMethodArgumentResolver);
        argumentResolvers.add(tokenToStudentMethodArgumentReslover);
    }

}
