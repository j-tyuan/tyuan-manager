/**
 * @ClassName Headers
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/7/8 17:48
 */
package com.tyuan.common.socket;


import com.tyuan.common.http.HttpMethod;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
public class Headers {

    Logger logger = LoggerFactory.getLogger(Headers.class);

    private Map<String, String> headers;

    private String host;

    private Integer port;

    private Headers() {
    }

    public Headers(byte[] bytes) throws IOException {
        this.headers = new LinkedHashMap();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        List<String> list = IOUtils.readLines(inputStream, "UTF-8");
        for (String header : list) {
            if ("".equals(header)) {
                break;
            };
            logger.info(header);
            String key = header.substring(0, header.indexOf(' '));
            String val = header.substring(header.indexOf(' ') + 1);
            int last = key.lastIndexOf(':');
            if (key.lastIndexOf(':') == key.length() - 1) {
                key = key.substring(0, last);
            }
            headers.put(key, val);
        }
        String[] host = headers.get("Host").split(":");
        int port = 80;
        if (host.length == 2) {
            port = Integer.parseInt(host[1]);
        } else if (headers.get("CONNECT") != null) {
            port = 443;
        }
        this.port = port;
        this.host = host[0];
    }

    public void add(String key, String val) {
        headers.put(key, val);
    }

    public byte[] getBytes() throws IOException {
        Set<Map.Entry<String, String>> entrySet = this.headers.entrySet();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String header = "";
            if ("CONNECT".equals(key) || HttpMethod.resolve(key) != null) {
                header = key + " " + entry.getValue();
            } else {
                header = key + ": " + entry.getValue();
            }
            header += "\r\n";
            outputStream.write(header.getBytes());
        }
        outputStream.write("\r\n".getBytes());
        return outputStream.toByteArray();
    }

    public String getHost() {
        return this.host;
    }

    public Integer getPort() {
        return this.port;
    }

    public boolean isSsl() {
        return this.headers.get("CONNECT") != null;
    }
}
