package com.jyc.o2o_demo.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jyc.o2o_demo.constant.DishConstants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

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

    public static final int qrSize = 300;  // 二维码大小
    public static final String picFormat = "png";  // 二维码图片格式

    /**
     * 生成二维码图片并保存到指定路径下
     * @param content
     * @param path
     */
    public static void generateQR(String content,String path) {
        Hashtable<EncodeHintType, Object> hashtable = new Hashtable<>();
        hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hashtable.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        hashtable.put(EncodeHintType.MARGIN,1);
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrSize, qrSize, hashtable);
            MatrixToImageWriter.writeToPath(matrix,picFormat,new File(path).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成png格式的二维码字节流
     * @param content
     * @return
     */
    public static byte[] generateQRBytes(String content) {
        byte[] codeBytes = null;
        try {
            // 二维字节矩阵，
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrSize, qrSize);
            BufferedImage image = toBufferedImage(bitMatrix);
            // 将缓存图片转换为输出流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image,picFormat,outputStream);
            // 转换为字节数据
            codeBytes = outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeBytes;
    }

    /**
     * 读取二维码，返回内容 + 格式 + 结果
     * @param path
     * @return
     */
    public static Map<String,Object> readQR(String path) {
        MultiFormatReader reader = new MultiFormatReader();
        File file = new File(path);
        BufferedImage image = null;
        BinaryBitmap bitmap = null;
        Result result = null;
        try {
            image = ImageIO.read(file);
            bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            result = reader.decode(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("qrCode",result);
        map.put("qrFormat",result.getBarcodeFormat());
        map.put("content",result.getText());
        return map;
    }

    /**
     * 二维字节矩阵转二维缓冲黑白图片
     * @param bitMatrix
     * @return
     */
    public static BufferedImage toBufferedImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int onColor = 0xFF000000;
        int offColor = 0xFFFFFFFF;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i,j,bitMatrix.get(i,j)?onColor:offColor);
            }
        }
        return image;
    }
}
