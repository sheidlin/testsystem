package com.zust.stkdy.testsystem;

import com.zust.stkdy.testsystem.entity.Exam;
import com.zust.stkdy.testsystem.entity.SubjectiveAnswer;
import com.zust.stkdy.testsystem.entity.SubjectiveQuestion;
import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

@SuppressWarnings("ALL")
@SpringBootApplication
@MapperScan("com.zust.stkdy.testsystem.dao")
public class TestSystemApplication {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(TestSystemApplication.class, args);
    }
}
