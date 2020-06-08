package com.jyc.o2o_demo.utils;

import java.util.Base64;

/**
 * base64加解密
 */
public class Base64Util {

    public static String encode(String content) {
        return new String(Base64.getEncoder().encode(content.getBytes()));
    }

    public static String decode(String content) {
        return new String(Base64.getDecoder().decode(content));
    }
}
