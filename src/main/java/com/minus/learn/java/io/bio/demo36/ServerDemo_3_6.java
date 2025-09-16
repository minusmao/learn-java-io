package com.minus.learn.java.io.bio.demo36;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 章节3.6：伪异步I/O编程 —— BIO服务端示例<br>
 * 可启动多个客户端（ClientDemo_3_4、ClientDemo_3_5、ClientDemo_3_6）进行测试<br>
 *
 * @author minus
 * @since 2025/9/15 17:01
 */
public class ServerDemo_3_6 {
    public static void main(String[] args) {
        try {
            System.out.println("----------服务端启动成功------------");
            ServerSocket ss = new ServerSocket(8888);

            // 一个服务端只需要对应一个线程池
            HandlerSocketThreadPool handlerSocketThreadPool =
                    new HandlerSocketThreadPool(3, 1000);

            // 客户端可能有很多个
            while (true) {
                // 阻塞式的！
                Socket socket = ss.accept();
                System.out.println("有人上线了！！");
                // 每次收到一个客户端的socket请求，都需要为这个客户端分配一个独立的线程 专门负责对这个客户端的通信！！
                handlerSocketThreadPool.execute(new ReaderClientRunnable(socket));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class ReaderClientRunnable implements Runnable {

    private Socket socket;

    public ReaderClientRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 读取一行数据
            InputStream is = socket.getInputStream();
            // 转成一个缓冲字符流
            Reader fr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(fr);
            // 一行一行的读取数据
            String line = null;
            // 阻塞式的！！
            while ((line = br.readLine()) != null) {
                System.out.println("服务端收到了数据：" + line);
            }
        } catch (Exception e) {
            System.out.println("有人下线了");
        }

    }
}

// 线程池处理类
class HandlerSocketThreadPool {

    // 线程池
    private ExecutorService executor;

    public HandlerSocketThreadPool(int maxPoolSize, int queueSize) {

        this.executor = new ThreadPoolExecutor(
                3,
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable task) {
        this.executor.execute(task);
    }
}
