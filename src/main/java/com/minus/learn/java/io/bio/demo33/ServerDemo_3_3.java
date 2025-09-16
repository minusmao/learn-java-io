package com.minus.learn.java.io.bio.demo33;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 章节3.3：传统的BIO编程实例回顾 —— BIO服务端示例
 *
 * @author minus
 * @since 2025/9/15 17:01
 */
public class ServerDemo_3_3 {

    public static void main(String[] args) throws Exception {
        System.out.println("==服务器的启动==");
        // （1）注册端口
        ServerSocket serverSocket = new ServerSocket(8888);
        //（2）开始在这里暂停等待接收客户端的连接,得到一个端到端的Socket管道
        Socket socket = serverSocket.accept();
        //（3）从Socket管道中得到一个字节输入流。
        InputStream is = socket.getInputStream();
        //（4）把字节输入流包装成自己需要的流进行数据的读取。
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //（5）读取数据
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("服务端收到：" + line);
        }
    }

}
