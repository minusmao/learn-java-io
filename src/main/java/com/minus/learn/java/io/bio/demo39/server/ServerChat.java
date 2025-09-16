package com.minus.learn.java.io.bio.demo39.server;

import com.minus.learn.java.io.bio.demo39.util.Constants;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务端
 *
 * @author minus
 * @since 2025/9/16 11:47
 */
public class ServerChat {

    /** 定义一个集合存放所有在线的socket  */
    public static Map<Socket, String> onLineSockets = new HashMap<>();

    public static void main(String[] args) {
        try {
            /** 1.注册端口   */
            ServerSocket serverSocket = new ServerSocket(Constants.PORT);

            /** 2.循环一直等待所有可能的客户端连接 */
            while(true){
                Socket socket = serverSocket.accept();
                /**3. 把客户端的socket管道单独配置一个线程来处理 */
                new ServerReader(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
