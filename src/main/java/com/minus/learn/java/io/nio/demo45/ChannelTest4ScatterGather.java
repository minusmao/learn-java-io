package com.minus.learn.java.io.nio.demo45;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通道测试4：分散（Scatter）和聚集（Gather）<br>
 * 分散读取（Scatter）：是指把Channel通道的数据读入到多个缓冲区中去<br>
 * 聚集写入（Gather）：是指将多个 Buffer 中的数据“聚集”到 Channel<br>
 *
 * @author minus
 * @since 2025/9/17 11:09
 */
public class ChannelTest4ScatterGather {

    public static void main(String[] args) {
        try {
            RandomAccessFile raf1 = new RandomAccessFile("/Users/minus/Desktop/data01.txt", "rw");
            //1. 获取通道
            FileChannel channel1 = raf1.getChannel();

            //2. 分配指定大小的缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(5);
            ByteBuffer buf2 = ByteBuffer.allocate(1024);

            //3. 分散读取
            ByteBuffer[] bufs = {buf1, buf2};
            channel1.read(bufs);

            for (ByteBuffer byteBuffer : bufs) {
                byteBuffer.flip();
            }

            System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
            System.out.println("-----------------");
            System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

            //4. 聚集写入
            RandomAccessFile raf2 = new RandomAccessFile("/Users/minus/Desktop/data03.txt", "rw");
            FileChannel channel2 = raf2.getChannel();

            channel2.write(bufs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
