/**
 * @ClassName HardWareUtils
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/7/16 10:08
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

import java.applet.Applet;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;


public class HardWareUtils extends Applet {
    public HardWareUtils() {
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void paint(Graphics paint) {
        super.paint(paint);
        paint.drawString("获取硬件信息", 10, 10);
        paint.drawString("CPU  SN:" + HardWareUtils.getCpuSerial(), 10, 30);
        paint.drawString("主板  SN:" + HardWareUtils.getMotherboardSN(), 10, 50);
        paint.drawString("C盘   SN:" + HardWareUtils.getHardDiskSN("c"), 10, 70);
        paint.drawString("MAC  SN:" + HardWareUtils.getMac(), 10, 90);
    }

    /**
     * 获取主板序列号
     *
     * @return
     */
    public static String getMotherboardSN() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    /**
     * 获取硬盘序列号
     *
     * @param drive 盘符
     * @return
     */
    public static String getHardDiskSN(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber"; // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    /**
     * 获取CPU序列号
     *
     * @return
     */
    public static String getCpuSerial() {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
            file.delete();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        if (result.trim().length() < 1 || result == null) {
            result = "无CPU_ID被读取";
        }
        return result.trim();
    }

    /**
     * 获取MAC地址
     */
    public static String getMac() {
        try {
            byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            return "";
        }

    }

    public static String getDeviceInfo() {
        return getMac().replace("-", "");
    }


    public static void main(String[] args) throws Exception {
        System.out.println(getCpuSerial());//CPU
        System.out.println(getMotherboardSN());//主板
        System.out.println(getHardDiskSN("c"));//c盘
        System.out.println(getMac());//MAC
        String msg = getCpuSerial() + getMotherboardSN().replace(".", "") + getHardDiskSN("c") + getMac().replace("-", "");
        System.out.println("原始数据:" + msg);
    }
}
