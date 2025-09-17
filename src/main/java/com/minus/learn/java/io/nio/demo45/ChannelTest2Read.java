package com.minus.learn.java.io.nio.demo45;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通道测试2：本地文件读数据
 *
 * @author minus
 * @since 2025/9/17 10:55
 */
public class ChannelTest2Read {

    public static void main(String[] args) {
        try {
            // 1、定义一个文件字节输入流与源文件接通
            FileInputStream is = new FileInputStream("/Users/minus/Desktop/data01.txt");
            // 2、需要得到文件字节输入流的文件通道
            FileChannel channel = is.getChannel();
            // 3、定义一个缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 4、读取数据到缓冲区
            channel.read(buffer);
            buffer.flip();
            // 5、读取出缓冲区中的数据并输出即可
            String rs = new String(buffer.array(), 0, buffer.remaining());
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
