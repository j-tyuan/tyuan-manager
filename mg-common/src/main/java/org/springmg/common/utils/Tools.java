package org.springmg.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Author: dev@tyuan.design
 * @Description:
 * @Date: Created in 上午11:45 18-1-18
 */
public class Tools {
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (Exception e) {
            return "";
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            };
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * 生成二维码
     *
     * @param contents  二维码内容
     * @param imageType 二维码图片类型
     * @param w         图片高度
     * @param h         图片宽度
     * @return 二维码流
     * @throws Exception
     */
    public static byte[] getQrCode(String contents, String imageType, int w, int h) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, w, h);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, imageType, outputStream);
        byte[] bytes = outputStream.toByteArray();
        return bytes;
    }

    /*  *//**
     * 生成Base64图片数据
     *
     * @param contents  二维码内容
     * @param imageType 二维码图片类型
     * @param w         高度
     * @param h         宽度
     * @return base64图片数据
     * @throws Exception
     *//*
    public static String getBase64QrCodeToString(String contents, String imageType, int w, int h) throws Exception {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(getQrCode(contents, imageType, w, h));
    }


    public static String base64Encode(String string) {
        return new BASE64Encoder().encode(string.getBytes());
    }

    public static String base64Decode(String string) throws Exception {
        return new String(new BASE64Decoder().decodeBuffer(string));
    }*/
}
