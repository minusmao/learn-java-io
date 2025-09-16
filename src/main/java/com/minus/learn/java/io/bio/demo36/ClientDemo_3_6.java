package com.minus.learn.java.io.bio.demo36;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 章节3.6：伪异步I/O编程 —— BIO客户端示例<br>
 *
 * @author minus
 * @since 2025/9/15 16:58
 */
public class ClientDemo_3_6 {

    public static void main(String[] args) throws Exception {
        try {
            // 1.简历一个与服务端的Socket对象：套接字
            Socket socket = new Socket("127.0.0.1", 8888);
            // 2.从socket管道中获取一个输出流，写数据给服务端
            OutputStream os = socket.getOutputStream() ;
            // 3.把输出流包装成一个打印流
            PrintWriter pw = new PrintWriter(os);
            // 4.反复接收用户的输入
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = null ;
            while((line = br.readLine()) != null){
                pw.println(line);
                pw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
