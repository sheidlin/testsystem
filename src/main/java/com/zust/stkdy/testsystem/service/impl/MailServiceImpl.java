package com.zust.stkdy.testsystem.service.impl;

import com.zust.stkdy.testsystem.service.MailService;


import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;


@Service("mailService")
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void sendSimpleMail(String to, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }
}
