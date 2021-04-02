/**
 * @ClassName HttpUtils
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/7/11 10:13
 */
package org.springmg.common.socket;

import io.netty.buffer.ByteBuf;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.util.function.Function;

public class HttpUtils {

    public static final String SOCKET_PASSWORD = "1234567887654123";

    /**
     * 获取请求头部
     * TODO jiangguiqi 可优化项
     *
     * @return header Bytes or null
     */
    public static byte[] byteBuftoHeader(ByteBuf byteBuf) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean flag = false;
        while (byteBuf.isReadable() && !flag) {
            byte b = byteBuf.readByte();
            if (b == '\n') {
                byteArrayOutputStream.write(b);
                byte[] isCRLF = new byte[2];
                byteBuf.readBytes(isCRLF);
                byteArrayOutputStream.write(isCRLF);
                if (isCRLF[0] == '\r' && isCRLF[1] == '\n') {
                    flag = true;
                    break;
                }
                continue;
            }
            byteArrayOutputStream.write(b);
        }
        if (flag) {
            //如果找到头部结束符，就扔掉已读的数据
            byteBuf.isReadable();
            return byteArrayOutputStream.toByteArray();
        } else {
            //如果没找到头部结束符，重置已读数据
            byteBuf.resetReaderIndex();
            return null;
        }
    }

    /**
     * 加密
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) {

        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = null;
            desKey = new DESKeySpec(password.getBytes());

            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 真正开始解密操作
            return cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "".getBytes();

    }

    public static void byteChange(InputStream inputStream, OutputStream outputStream, Function<byte[], byte[]> fun) throws IOException {
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }

    }


    public static void main(String[] a) throws Exception {
        String str = "CONNECT translate.google.com:443 HTTP/1.1\n" +
                "Host: translate.google.com:443\n" +
                "Proxy-Connection: keep-alive\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36\n" +
                "\r\n" +
                "\n" +
                "\t\t[-] Pipe End  : -----------------\n" +
                "\t[-] HTTPSession.run():End ID : 39\n" +
                "\t[*] ThreadCount:12\n" +
                "\t[+] HTTPSession.run():StartID : 59\n" +
                "\t\t[+] Pipe Start: -----------------\n" +
                "\n" +
                "Process finished with exit code -1\n";
        byte[] bytes = str.getBytes();
        byte[] encBytes = encrypt(bytes, SOCKET_PASSWORD);
        ByteArrayInputStream in = new ByteArrayInputStream(encBytes);

        // Headers headers = new Headers(hs);
    }
}
