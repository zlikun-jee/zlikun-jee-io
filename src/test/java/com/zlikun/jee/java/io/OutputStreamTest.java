package com.zlikun.jee.java.io;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/7 15:33
 */
public class OutputStreamTest {

    @Test
    public void test() throws IOException {

        OutputStream output = new ByteArrayOutputStream();

        // 写入一个字节
        output.write('H');
        // 写入一个字节数组
        output.write(new byte[]{'e', 'l'});
        // 写入一个字节数组一部分
        output.write("Hello!".getBytes(), 3, 3);

        // 使用ByteArrayOutputStream里的方法，测试输出流大小、内容等
        ByteArrayOutputStream baos = (ByteArrayOutputStream) output;
        assertEquals(6, baos.size());
        // 如果使用中文，可以指定编码参数
        assertEquals("Hello!", baos.toString());
        // 如果只是为了传输，可以使用字节数组
        assertEquals("Hello!", new String(baos.toByteArray()));

        // ByteArrayOutputStream 类没有实现下面两个方法，所以是不需要关闭
        // 它使用了父类OutputStream的实现（空实现），所以为了一致性，还是关闭吧（可以使用try-with-resources语句简化）
        output.flush();
        output.close();

    }

}
