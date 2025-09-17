package com.minus.learn.java.io.nio.demo44;

import java.nio.ByteBuffer;

/**
 * 测试Buffer
 *
 * @author minus
 * @since 2025/9/16 16:48
 */
public class TestBuffer {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        String str = "itheima";
        //1. 分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("-----------------allocate()----------------");
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        //2. 利用 put() 存入数据到缓冲区中
        buf.put(str.getBytes());
        System.out.println("-----------------put()----------------");
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        //3. 切换读取数据模式
        buf.flip();
//        buf.put(str.getBytes());    // 读模式下put()会失败，可以理解为limit==capacity时才可以写
        System.out.println("-----------------flip()----------------");
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        //4. 利用 get() 读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);

        System.out.println("-----------------get()----------------");
        System.out.println(new String(dst, 0, dst.length));
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());
        //5. rewind() : 可重复读
        buf.rewind();
        System.out.println("-----------------rewind()----------------");
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        //6. clear() : 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态
        buf.clear();
        System.out.println("-----------------clear()----------------");
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());
        System.out.println("clear()后还可以读出来：" + (char) buf.get());
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        // 7. compact()：把未读完的数据搬到缓冲区前面，position 放在最后未读数据之后，方便继续往后写
        buf.compact();
        System.out.println("-----------------compact()----------------");
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());
    }

    public static void test2() {
        String str = "itheima";

        ByteBuffer buf = ByteBuffer.allocate(1024);

        buf.put(str.getBytes());

        buf.flip();
        System.out.println("=============flip()=============");
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println("=============get(dst, 0, 2)=============");
        System.out.println(new String(dst, 0, 2));
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        //mark() : 标记
        buf.mark();
        System.out.println("=============mark()=============");
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        System.out.println("=============get()=============");
        System.out.println(buf.get());
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        buf.get(dst, 2, 2);
        System.out.println("=============get(dst, 2, 2)=============");
        System.out.println(new String(dst, 2, 2));
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        //reset() : 恢复到 mark 的位置
        buf.reset();
        System.out.println("position：" + buf.position());
        System.out.println("limit：   " + buf.limit());
        System.out.println("capacity：" + buf.capacity());

        //判断缓冲区中是否还有剩余数据
        if (buf.hasRemaining()) {
            //获取缓冲区中可以操作的数量
            System.out.println(buf.remaining());
        }
    }

    public static void test3() {
        //分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        System.out.println(buf.isDirect());
    }

}
