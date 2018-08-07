package com.zlikun.jee.java.util;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/7 16:17
 */
public class FilterStreamTest {

    @Test
    public void test() throws IOException {

        byte[] data = null;

        // 输出流，实现序列化
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream output = new DataOutputStream(baos);
        ) {
            output.writeInt(1024);
            output.writeUTF("Hello!");
            output.writeBoolean(true);
            output.writeByte('x');
            output.writeBytes("yz");
            output.writeShort(1);
            output.writeLong(1);
            output.writeFloat(2.0F);
            output.writeDouble(3.0);
            output.writeChar('X');
            output.writeChars("YZ");
            data = baos.toByteArray();
        }


        // 输入流，实现反序列化
        try (
                // 字节数组输入流
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                // DataInputStream 是 FilterInputStream 子类，另一个常用子类是BufferedInputStream类
                DataInputStream input = new DataInputStream(bais);
        ) {

            // 以Java类型读取输入流
            assertEquals(1024, input.readInt());
            assertEquals("Hello!", input.readUTF());
            assertEquals(true, input.readBoolean());
            assertEquals('x', input.readByte());
            assertEquals('y', input.readByte());
            assertEquals('z', input.readUnsignedByte());
            assertEquals(1, input.readShort());
            assertEquals(1, input.readLong());
            assertEquals(2.0, input.readFloat(), 0);
            assertEquals(3.0, input.readDouble(), 0);
            assertEquals('X', input.readChar());
            assertEquals('Y', input.readChar());
            assertEquals('Z', input.readChar());
        }

    }

}
