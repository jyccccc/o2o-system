package com.jyc.o2o_demo.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES加密解密
 */
public class PwdUtil {

    /**
     * 加密
     * @param encodeRules
     * @param password
     * @return
     */
    public static String AESEncode(String encodeRules, String password) {
        try {
            // 构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            // 根据ecnodeRules规则初始化密钥生成器,生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            // 产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            // 获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            // 根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
            // 根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            // 初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byte_encode=password.getBytes("utf-8");
            // 根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES=cipher.doFinal(byte_encode);
            System.out.println(byte_AES);
            // 将加密后的数据转换为字符串
            String AES_encode = Base64.getEncoder().encodeToString(byte_AES);
            System.out.println(AES_encode);
            // 将字符串返回
            return AES_encode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String AESDecode(String encodeRules, String password) {
        try {
            System.out.println(password.getBytes());
            // 构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            // 根据ecnodeRules规则初始化密钥生成器,生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            // 产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            // 获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            SecretKey key=new SecretKeySpec(raw, "AES");
            // 根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            // 初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 将加密并编码后的内容解码成字节数组
            byte[] byte_content= Base64.getDecoder().decode(password);
            System.out.println(byte_content);
            // 解密
            byte [] byte_decode=cipher.doFinal(byte_content);
            String AES_decode=new String(byte_decode,"utf-8");
            return AES_decode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
