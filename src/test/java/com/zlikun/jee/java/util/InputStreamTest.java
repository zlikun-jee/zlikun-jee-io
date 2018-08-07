package com.zlikun.jee.java.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/7 14:38
 */
public class InputStreamTest {

    @Test
    public void test() throws IOException {

        byte[] buf = "Hello!".getBytes();
        InputStream input = new ByteArrayInputStream(buf);

        // 有效字节数
        assertEquals(6, input.available());

        // 读取一个字节，返回这个字节（int类型）
        assertEquals('H', input.read());

        // 读取后有效字节只剩下5个
        assertEquals(5, input.available());

        // 传入字节数组，将读取的数据写入字节数组，返回读取字节数
        byte[] data = new byte[3];
        assertEquals(3, input.read(data));
        assertEquals('e', data[0]);
        assertEquals('l', data[1]);
        assertEquals('l', data[2]);

        // 复位，标记移回第一个字节处，这样可以重新读取（否则不能读取已读取过的数据）
        input.reset();

        // 有效字节数恢复为初始值
        assertEquals(6, input.available());

        // 读取2个字节，填充字节数组从索引1开始的2个元素值，未被填充的值为0
        data = new byte[4];
        assertEquals(2, input.read(data, 1, 2));
        // [0, 72, 101, 0]
        assertEquals(0, data[0]);
        assertEquals('H', data[1]);
        assertEquals('e', data[2]);
        assertEquals(0, data[3]);

        input.reset();

        // 跳过2个字节，返回实际跳过的字节数
        assertEquals(2, input.skip(2));
        // 只剩下4个字节，所以实际只跳过了4个字节，这里到达流末尾
        assertEquals(4, input.skip(7));

        // 如果继续读取，会返回-1，表示流已读取完毕
        assertEquals(-1, input.read());

        input.reset();

        // 判断流是否支持mark
        assertTrue(input.markSupported());

        // 设置mark位置，readlimit表示在该标记后多少个字节内该标记有效，超过这个限制，标记可能就无效了
        // 在ByteArrayInputStream类中，readlimit（readAheadLimit）值没有意义，随便写（关于这个参数，不同的流实现机制不同，要注意）
        // 设置mark位置后，如果执行reset方法，会回到这个位置
        // 我们先读3个字节（这里使用skip跳过，位置是第二个"l"）
        input.skip(3);
        input.mark(0);
        assertEquals('l', input.read());
        assertEquals('o', input.read());
        input.reset();  // 执行reset后，回到mark的位置，又重新读
        assertEquals('l', input.read());
        assertEquals('o', input.read());
        assertEquals('!', input.read());

        // 关闭流
        input.close();

    }

}
