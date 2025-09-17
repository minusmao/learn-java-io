package com.minus.learn.java.io.nio.demo45;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 通道测试5：从目标通道中去复制原通道数据
 *
 * @author minus
 * @since 2025/9/17 11:46
 */
public class ChannelTest5TransferFrom {

    public static void main(String[] args) {
        try {
            // 1、字节输入管道
            FileInputStream is = new FileInputStream("/Users/minus/Desktop/data01.txt");
            FileChannel isChannel = is.getChannel();
            // 2、字节输出流管道
            FileOutputStream fos = new FileOutputStream("/Users/minus/Desktop/data04.txt");
            FileChannel osChannel = fos.getChannel();
            // 3、复制
            osChannel.transferFrom(isChannel, isChannel.position(), isChannel.size());
            isChannel.close();
            osChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
