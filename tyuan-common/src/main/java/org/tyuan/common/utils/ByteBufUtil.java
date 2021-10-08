/**
 * @ClassName ByteBufUtil
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/10/31 11:07
 */
/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.common.utils;

import io.netty.buffer.ByteBuf;
import org.tyuan.common.user.AUTH;

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
