package com.zlikun.jee.java.io;

import org.junit.Test;

import java.io.*;
import java.util.concurrent.CountDownLatch;

/**
 * 管道流，用于线程间通讯
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/7 16:32
 */
public class PipedStreamTest {

    @Test
    public void test() throws IOException, InterruptedException {

        final PipedInputStream pin = new PipedInputStream();
        final PipedOutputStream pout = new PipedOutputStream(pin);
//        // 也可像下面代码一样，创建时不关联，创建后再关联（单向去关联）
//        final PipedOutputStream pout = new PipedOutputStream();
//
//        try {
//            pin.connect(pout);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 启动两个线程，一个线程生产（生产斐波那契数列），一个线程消费
        CountDownLatch latch = new CountDownLatch(2);

        // 生产者
        new Thread() {
            final DataOutputStream output = new DataOutputStream(pout);

            @Override
            public void run() {
                int m = 1;
                int n = 1;
                try {
                    output.writeInt(m);
                    for (int i = 1; i < 10; i++) {
                        int t = m;
                        m = n;
                        n += t;
                        output.writeInt(m);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }.start();

        // 消费者
        new Thread() {
            final DataInputStream input = new DataInputStream(pin);

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.printf("receive: %d - %d%n", i, input.readInt());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                latch.countDown();
            }
        }.start();

        latch.await();

    }

}
