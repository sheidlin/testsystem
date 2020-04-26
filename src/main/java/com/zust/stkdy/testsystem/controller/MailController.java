package com.zust.stkdy.testsystem.controller;


import com.zust.stkdy.testsystem.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/common/getCheckCode",method = RequestMethod.GET)
    @ResponseBody
    public String getCheckCode(String email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(email, "注册验证码", message);
        }catch (Exception e){
            return "";
        }
        return checkCode;
    }
}
