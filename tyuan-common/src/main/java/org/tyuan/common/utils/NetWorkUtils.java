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

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guiqijiang on 10/7/19.
 */
public class NetWorkUtils {

    /**
     * 测试本机端口是否被使用
     *
     * @param port
     * @return
     */
    public static boolean isLocalPortUsing(int port) {
        boolean flag = true;
        try {
            //如果该端口还在使用则返回true,否则返回false,127.0.0.1代表本机
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }

    /***
     * 测试主机Host的port端口是否被使用
     * @param host
     * @param port
     * @throws UnknownHostException
     */
    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress Address = InetAddress.getByName(host);
        try {
            new Socket(Address, port);  //建立一个Socket连接
            flag = true;
        } catch (IOException e) {
        }
        return flag;
    }

    public static List<String> inputStreamToStringList(InputStream in) {
        return inputStreamToStringList(in, null);
    }

    public static List<String> inputStreamToStringList(InputStream in, String charsetName) {
        InputStreamReader inputStream;
        try {
            if (StringUtils.isNotBlank(charsetName)) {
                inputStream = new InputStreamReader(in, charsetName);

            } else {
                inputStream = new InputStreamReader(in);
            }
            BufferedReader reader = new BufferedReader(inputStream);
            List<String> workServices = new ArrayList<>();
            String line;
            while (StringUtils.isNotBlank(line = reader.readLine())) {
                workServices.add(line);
            }
            return workServices;
        } catch (IOException e) {
        }
        return new ArrayList<>();
    }
}
