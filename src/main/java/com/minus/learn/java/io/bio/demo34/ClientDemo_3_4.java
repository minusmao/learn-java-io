package com.minus.learn.java.io.bio.demo34;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 章节3.4：BIO模式下多发和多收消息 —— BIO客户端示例
 *
 * @author minus
 * @since 2025/9/15 16:58
 */
public class ClientDemo_3_4 {

    /**
     目标: Socket网络编程。

     功能1：客户端可以反复发消息，服务端可以反复收消息

     小结：
     通信是很严格的，对方怎么发你就怎么收，对方发多少你就只能收多少！！

     */
    public static void main(String[] args) throws Exception {
        System.out.println("==客户端的启动==");
        // （1）创建一个Socket的通信管道，请求与服务端的端口连接。
        Socket socket = new Socket("127.0.0.1", 8888);
        // （2）从Socket通信管道中得到一个字节输出流。
        OutputStream os = socket.getOutputStream();
        // （3）把字节流改装成自己需要的流进行数据的发送
        PrintStream ps = new PrintStream(os);
        // （4）开始发送消息
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请说:");
            String msg = sc.nextLine();
            ps.println(msg);
            ps.flush();
        }
    }

}
