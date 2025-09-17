package com.minus.learn.java.io.nio.demo45;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通道测试3：文件复制
 *
 * @author minus
 * @since 2025/9/17 11:03
 */
public class ChannelTest3Copy {

    public static void main(String[] args) {
        try {
            // 源文件
            File srcFile = new File("/Users/minus/Desktop/data01.txt");
            File destFile = new File("/Users/minus/Desktop/data02.txt");
            // 得到一个字节字节输入流
            FileInputStream fis = new FileInputStream(srcFile);
            // 得到一个字节输出流
            FileOutputStream fos = new FileOutputStream(destFile);
            // 得到的是文件通道
            FileChannel isChannel = fis.getChannel();
            FileChannel osChannel = fos.getChannel();
            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                // 必须先清空缓冲然后再写入数据到缓冲区
                buffer.clear();
                // 开始读取一次数据
                int flag = isChannel.read(buffer);
                if (flag == -1) {
                    break;
                }
                // 已经读取了数据 ，把缓冲区的模式切换成可读模式
                buffer.flip();
                // 把数据写出到
                osChannel.write(buffer);
            }
            isChannel.close();
            osChannel.close();
            System.out.println("复制完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
