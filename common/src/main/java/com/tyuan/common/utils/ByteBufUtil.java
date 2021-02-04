/**
 * @ClassName ByteBufUtil
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/10/31 11:07
 */
package com.tyuan.common.utils;

import io.netty.buffer.ByteBuf;
import com.tyuan.common.user.AUTH;

import java.io.ByteArrayOutputStream;

public class ByteBufUtil {


    public static byte[] CRLF = new byte[]{'\r', '\n'};

    /**
     * 读取http报文的第一行数据，用于放置token的
     *
     * @param byteBuf
     * @return
     */
    public static byte[] readFirstRowAndDiscard(ByteBuf byteBuf) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        while (byteBuf.isReadable()) {
            byte b = byteBuf.readByte();
            if (b == '\n') {
                break;
            }
            if (b == '\r'){
                continue;
            }
            bytes.write(b);
        }
        byteBuf.discardReadBytes();
        return bytes.toByteArray();
    }

    /**
     * 获取token，以后优化
     *
     * @param buf
     * @return
     */
    public static String getToken(ByteBuf buf) {
        byte[] bytes = readFirstRowAndDiscard(buf);
        return new String(bytes);
    }


    public static int bytesByConnStatus(byte[] bytes) {
        try {
            String s = new String(bytes);
            String[] sb = s.split(";");
            for (String str : sb) {
                String[] item = str.split("=");
                if (AUTH.STATUS.equals(item[0])) {
                    return Integer.parseInt(item[1]);
                }
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }
}
