package com.minus.learn.java.io.bio.demo35;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 章节3.5：BIO模式下接收多个客户端 —— BIO客户端示例<br>
 * 相比ClientDemo_3_4并没有变动，只需在服务端代码中加入多线程即可，启动多个客户端（ClientDemo_3_4、ClientDemo_3_5）进行测试
 *
 * @author minus
 * @since 2025/9/15 16:58
 */
public class ClientDemo_3_5 {

    /**
     目标: Socket网络编程。

     功能1：客户端可以反复发，一个服务端可以接收无数个客户端的消息！！

     小结：
     服务器如果想要接收多个客户端，那么必须引入线程，一个客户端一个线程处理！！

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
