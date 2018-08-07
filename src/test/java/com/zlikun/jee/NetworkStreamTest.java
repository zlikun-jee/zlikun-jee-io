package com.zlikun.jee;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 网络流测试
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/7 16:09
 */
public class NetworkStreamTest {

    @Test
    public void test() throws IOException {

        // 构造一个URL
        URL url = new URL("https://zlikun.com");

        // 使用BufferedReader包装网络流
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()), 1024)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

    }

}
