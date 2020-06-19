package com.jyc.o2o_demo;

import com.jyc.o2o_demo.utils.PicUtil;

import java.util.Map;

public class PicUtilTest {
    public static void main(String[] args) {
        String path = "F:\\java_workspace\\order-system\\qr\\Spcccc.png";
        try {
            PicUtil.generateQR("Spcccc",path);  // 生成二维码
            byte[] bytes = PicUtil.getBytesByPath(path);  // 从本地二维码，获取字节数组
            byte[] generateQRBytes = PicUtil.generateQRBytes("Spcccc");// 根据内容生成字节数组
            System.out.println("getBytesByPath vs " + "generateQRBytes: " + (bytes.equals(generateQRBytes)));
            Map<String, Object> map = PicUtil.readQR(path);
            map.forEach((key,value) -> {
                System.out.println(key + " : " + value.toString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
