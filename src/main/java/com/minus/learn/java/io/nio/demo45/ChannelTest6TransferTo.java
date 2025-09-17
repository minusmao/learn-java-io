package com.minus.learn.java.io.nio.demo45;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 通道测试6：把原通道数据复制到目标通道
 *
 * @author minus
 * @since 2025/9/17 11:49
 */
public class ChannelTest6TransferTo {

    public static void main(String[] args) {
        try {
            // 1、字节输入管道
            FileInputStream is = new FileInputStream("/Users/minus/Desktop/data01.txt");
            FileChannel isChannel = is.getChannel();
            // 2、字节输出流管道
            FileOutputStream fos = new FileOutputStream("/Users/minus/Desktop/data05.txt");
            FileChannel osChannel = fos.getChannel();
            // 3、复制
            isChannel.transferTo(isChannel.position(), isChannel.size(), osChannel);
            isChannel.close();
            osChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
