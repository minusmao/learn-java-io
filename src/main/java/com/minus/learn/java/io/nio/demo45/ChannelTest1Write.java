package com.minus.learn.java.io.nio.demo45;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通道测试1：本地文件写数据
 *
 * @author minus
 * @since 2025/9/17 10:48
 */
public class ChannelTest1Write {

    public static void main(String[] args) {
        try {
            // 1、字节输出流通向目标文件
            FileOutputStream fos = new FileOutputStream("/Users/minus/Desktop/data01.txt");
            // 2、得到字节输出流对应的通道Channel
            FileChannel channel = fos.getChannel();
            // 3、分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello,黑马Java程序员！".getBytes());
            // 4、把缓冲区切换成写出模式
            buffer.flip();
            channel.write(buffer);
            channel.close();
            System.out.println("写数据到文件中！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
