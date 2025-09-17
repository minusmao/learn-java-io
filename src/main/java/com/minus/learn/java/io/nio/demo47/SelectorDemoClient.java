package com.minus.learn.java.io.nio.demo47;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * 选择器测试：NIO非阻塞式网络通信-客户端
 *
 * @author minus
 * @since 2025/9/17 15:25
 */
public class SelectorDemoClient {

    public static void main(String[] args) {
        try {
            //1. 获取通道
            SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
            //2. 切换非阻塞模式
            sChannel.configureBlocking(false);
            //3. 分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //4. 发送数据给服务端
            Scanner scan = new Scanner(System.in);
            while (scan.hasNext()) {
                String str = scan.nextLine();
                buf.put((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis())
                        + "\n" + str).getBytes());
                buf.flip();
                sChannel.write(buf);
                buf.clear();
            }
            //5. 关闭通道
            sChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
