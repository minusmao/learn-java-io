package com.minus.learn.java.io.bio.demo37;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * 章节3.7：基于BIO形式下的文件上传 —— BIO客户端示例<br>
 *
 * @author minus
 * @since 2025/9/15 16:58
 */
public class ClientDemo_3_7 {

    /**
     目标：实现客户端上传任意类型的文件数据给服务端保存起来。

     */
    public static void main(String[] args) {
        try (
                InputStream is = new FileInputStream("/Users/minus/Pictures/download/11534244.png");
        ) {
            //  1、请求与服务端的Socket链接
            Socket socket = new Socket("127.0.0.1", 8888);
            //  2、把字节输出流包装成一个数据输出流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            //  3、先发送上传文件的后缀给服务端
            dos.writeUTF(".png");
            //  4、把文件数据发送给服务端进行接收
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                dos.write(buffer, 0, len);
            }
            dos.flush();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
