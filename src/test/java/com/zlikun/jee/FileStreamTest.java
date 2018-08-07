package com.zlikun.jee;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * 文件读写流测试
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/7 15:40
 */
public class FileStreamTest {

    /**
     * 字节流读取文件
     *
     * @throws IOException
     */
    @Test
    public void byte_file_input_stream() throws IOException {

        try (FileInputStream input = new FileInputStream("LICENSE")) {

            assertEquals(11558, input.available());

            // 读取文件
            StringBuilder builder = new StringBuilder();
            // 字节数组可以视为缓冲区
            byte[] buf = new byte[16];
            int length;
            while ((length = input.read(buf)) != -1) {
                builder.append(new String(buf, 0, length, StandardCharsets.UTF_8));
            }
            // 输出文件内容
            System.out.println(builder.toString());

        }

    }

    /**
     * 字符流读取文件
     *
     * @throws IOException
     */
    @Test
    public void character_file_input_stream() throws IOException {
        try (FileReader reader = new FileReader("pom.xml")) {
            // 注意没有中间的短横
            assertEquals("UTF8", reader.getEncoding());
            // 遍历文件内容
            StringBuilder builder = new StringBuilder();
            char[] buf = new char[16];
            int length;
            while ((length = reader.read(buf)) != -1) {
                builder.append(new String(buf, 0, length));
            }
            // 输出文件内容
            System.out.println(builder.toString());
        }
    }

    /**
     * 字节流写入文件
     *
     * @throws IOException
     */
    @Test
    public void byte_file_output_stream() throws IOException {
        // Hello!
        try (FileOutputStream output = new FileOutputStream("target/file.txt")) {
            output.write("Hello!".getBytes());
        }
    }

    @Test
    public void character_file_output_stream() throws IOException {
        // Hello IO !
        try (FileWriter writer = new FileWriter("target/file.txt")) {
            writer.append("Hello ");
            writer.write("IO !");
        }
    }

}
