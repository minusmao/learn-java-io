package com.minus.learn.java.io.nio.demo46;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 选择器测试
 *
 * @author minus
 * @since 2025/9/17 13:58
 */
public class SelectorDemo {

    public static void main(String[] args) {
        try {
            //1. 获取通道
            ServerSocketChannel ssChannel = ServerSocketChannel.open();
            //2. 切换非阻塞模式
            ssChannel.configureBlocking(false);
            //3. 绑定连接
            ssChannel.bind(new InetSocketAddress(9898));
            //4. 获取选择器
            Selector selector = Selector.open();
            //5. 将通道注册到选择器上, 并且指定“监听接收事件”
            /*
             * 读 : SelectionKey.OP_READ （1）
             * 写 : SelectionKey.OP_WRITE （4）
             * 连接 : SelectionKey.OP_CONNECT （8）
             * 接收 : SelectionKey.OP_ACCEPT （16）
             *
             * 若注册时不止监听一个事件，则可以使用“位或”操作符连接，如：SelectionKey.OP_READ | SelectionKey.OP_WRITE
             */
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
