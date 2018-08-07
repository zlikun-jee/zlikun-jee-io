package com.zlikun.jee;

import org.junit.Test;

import java.io.*;
import java.util.Date;

/**
 * 使用其它流实现文件读写（增强）
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/7 16:01
 */
public class FileStreamTest2 {

    /**
     * 测试读文件
     */
    @Test
    public void read() throws IOException {
        // 使用缓冲流读取文件（字节流与之类似），缓冲区为32个字节
        try (BufferedReader reader = new BufferedReader(new FileReader("LICENSE"), 32)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    @Test
    public void write() throws IOException {

        try (PrintWriter writer = new PrintWriter(new FileWriter("target/file.txt"))) {
            writer.println("Hello !");
            writer.println(1024);
            writer.println(2400.0);
            writer.println(new Date());
            writer.write("Hello IO !");
        }

    }


}
