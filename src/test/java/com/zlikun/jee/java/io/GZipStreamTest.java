package com.zlikun.jee.java.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static org.junit.Assert.assertEquals;

/**
 * 以GZIP为例说明压缩、解压缩
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/7 16:57
 */
public class GZipStreamTest {

    @Test
    public void test() throws IOException {

        byte[] data;

        // 执行压缩
        try (
                // 压缩文件字节流
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                GZIPOutputStream output = new GZIPOutputStream(baos);
                // 读取文件
                FileInputStream input = new FileInputStream("LICENSE")
        ) {
            byte[] buf = new byte[64];
            int length;
            while ((length = input.read(buf)) != -1) {
                output.write(buf, 0, length);
                // System.out.print(new String(buf, 0, length));
            }
            output.finish();
            data = baos.toByteArray();
        }

        // 内容过少会看不出压缩的效果，可以看出效果还是很明显的
        assertEquals(3981, data.length);

        // 解压缩
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                GZIPInputStream input = new GZIPInputStream(new ByteArrayInputStream(data))
        ) {
            byte[] buf = new byte[64];
            int length;
            while ((length = input.read(buf)) != -1) {
                baos.write(buf, 0, length);
            }
            byte[] data2 = baos.toByteArray();

            // 3981（解压前） -> 11558（解压后）
            assertEquals(11558, data2.length);
        }

    }

}
