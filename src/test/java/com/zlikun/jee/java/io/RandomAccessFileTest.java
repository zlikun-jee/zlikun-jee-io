package com.zlikun.jee.java.io;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

import static org.junit.Assert.assertEquals;

/**
 * 随机访问（读写）文件类测试
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/7 17:24
 */
public class RandomAccessFileTest {

    @Test
    public void test() throws IOException {

        // 实现对文件的随机访问
        try (RandomAccessFile raf = new RandomAccessFile("LICENSE", "rw")) {
            // 文件访问指针
            assertEquals(0L, raf.getFilePointer());
            // 移动文件访问指针
            raf.seek(33);
            assertEquals(33L, raf.getFilePointer());
            // 读取文件该位置内容
            assertEquals('A', raf.readByte());
            // 再向后跳7个字节
            raf.seek(raf.getFilePointer() + 6);
            // 这时候访问的是L
            assertEquals('L', raf.readByte());

            // 修改指定位置字符(32)
            raf.seek(0);
            raf.writeByte('H');

            // 复位文件修改（如果要观察效果，把复位代码注掉）
            raf.seek(0);
            raf.writeByte(32);
        }

    }

}
