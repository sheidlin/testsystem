package com.zust.stkdy.testsystem.utils;

import org.thymeleaf.util.NumberUtils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SystemUtil {
    public static String getNewToken(String sessionId, int userId) {
        String src = sessionId + userId + NumberUtil.genRandomNum(4);
        return genToken(src);
    }
    public static String genToken(String src){
        if (null == src || "".equals(src)){
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return null;
        }
    }
}
