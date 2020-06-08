package com.jyc.o2o_demo;

import com.jyc.o2o_demo.utils.PwdUtil;

import java.util.Base64;
import java.util.Date;

public class PwdUtilTest {
    public static void main(String[] args) {
        String p = "jyc112515";
        System.out.println("加密前：" + p);

        // String p1 = PwdUtil.AESEncode("AES++",p);
        String p1 = Base64.getEncoder().encodeToString(p.getBytes());

        System.out.println("加密后：" + p1);

        // String p2 = PwdUtil.AESDecode("AES++",p1);
        String p2 = new String(Base64.getDecoder().decode(p1));

        System.out.println("解密后：" + p2);

        System.out.println(new Date());
    }
}
