package com.jyc.o2o_demo.utils;

import com.jyc.o2o_demo.constant.DishConstants;

import java.io.File;
import java.io.FileInputStream;

public class PicUtil {

    /**
     * 由图片路径获取到字节数组
     * @param path
     * @return
     * @throws Exception
     */
    public static byte[] getBytesByPath(String path) throws Exception{
        File file = new File(path);
        System.out.println(path);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes,0,inputStream.available());
        return bytes;
    }

}
