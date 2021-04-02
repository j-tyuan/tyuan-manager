/**
 * @ClassName NettyUtils
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/7/16 11:06
 */
package org.springmg.common.utils;

import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springmg.common.socket.Host;

public class NettyUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(NettyUtils.class);

    public static char[] CRLF = new char[]{'\r', '\n'};

    public static Host httpObjectToHost(HttpObject msg) {
        //转成 HttpRequest
        HttpRequest req = (HttpRequest) msg;
        String headerHost = req.headers().get("Host");    //获取请求头中的Host字段
        String host = "";
        int port = 80;                                    //端口默认80
        String[] split = headerHost.split(":");            //可能有请求是 host:port的情况，
        host = split[0];
        if (split.length > 1) {
            port = Integer.valueOf(split[1]);
        }
        Host h = new Host();
        h.setHost(host);
        h.setPort(port);
        h.setHttpMethod(req.method());
        return h;
    }

    public enum RequestCode {
        OK(200),
        ACCOUNT_ERROR(1),
        ERROR(500);

        private int code;

        RequestCode(int s) {
            this.code = s;
        }

        public int getCode() {
            return code;
        }

        public static RequestCode get(int code) {
            switch (code) {
                case 200:
                    return OK;
                case 1:
                    return ACCOUNT_ERROR;
                default:
                    return ERROR;
            }
        }
    }


    public static byte[] setRequestCodeToBytes(RequestCode connStatus) {
        String s = "status:" + connStatus.getCode() + String.valueOf(CRLF);
        return s.getBytes();
    }

    /**
     * bytes转连接状态
     *
     * @param bytes
     * @return
     */
    public static RequestCode readBytesToRequestCode(byte[] bytes) {
        try {
            String s = new String(bytes);
            String[] sb = s.split(":");
            int status = Integer.parseInt(sb[1]);
            return RequestCode.get(status);
        } catch (Exception e) {
            LOGGER.error("【readBytesToRequestCode】读取异常:{}", e.getMessage());
            return RequestCode.ACCOUNT_ERROR;
        }

    }

    public static double getKB(long size) {
        return DeciminalUtils.scale(size * 8 / 1024d, 2);
    }

    public static double getMB(long size) {
        return DeciminalUtils.scale(getKB(size) / 1024, 2);
    }

    /**
     * 展示给用户看,b 为 位
     *
     * @param size
     * @return
     */
    public static String getPrintSize(long size) {
        size = size * 8;
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }
}
