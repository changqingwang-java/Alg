package com.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NonBlockingEchoServer {
    public static int port = 9999;
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 1000, TimeUnit.MILLISECONDS, new LinkedTransferQueue<Runnable>());
    public static Selector selector = null;

    private static void doWrite(SocketChannel sc, String data) throws IOException {
        byte[] req = data.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(req.length);
        byteBuffer.put(req);
        byteBuffer.flip();
        sc.write(byteBuffer);
    }

    public static void handleAcceptable(SelectionKey key) throws IOException {
        // 获得对应的ServerSocketChannel TODO: 这里为什么是socketChannel
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        // 得到对应的SocketChannel TODO:accpet是什么意思
        SocketChannel channel = ssc.accept();// 在非阻塞模式下，accept()可能为null
        // 处理socketChannel
        channel.configureBlocking(false); // TODO: 为什么设置非阻塞
        channel.register(selector, SelectionKey.OP_READ); // TODO: 将准备状态转化为读状态

        // 将key对应Channel设置为准备接受其他请求
        key.interestOps(SelectionKey.OP_ACCEPT);// TODO:
    }

    public static void handelReadable(SelectionKey key) throws IOException {
        // ==================我们要将数据从通道读到buffer里
        SocketChannel ssc = (SocketChannel) key.channel(); // TODO:
        // 为什么这里是socketChannel
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int readBytes = ssc.read(byteBuffer);// channel ==> buffer
        if (readBytes > 0) {// 代表读完毕了,准备写(即打印出来)
            byteBuffer.flip(); // 为write()准备
            // =====取出buffer里的数据
            byte[] bytes = new byte[byteBuffer.remaining()]; // 创建字节数组
            byteBuffer.get(bytes);// 将数据取出放到字节数组里
            String content = new String(bytes);

            System.out.println("write:");
            doWrite(ssc, content);
        }
    }

    public static void handle(SelectionKey key) {
        try {
            // 连接就绪
            if (key.isAcceptable()) {
                handleAcceptable(key);
            }
            // 读就绪
            if (key.isReadable()) {
                handelReadable(key);
            }
        } catch (IOException e) {
            key.cancel();
            if (key.channel() != null) {
                try {
                    key.channel().close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("listening on port :" + port);
        ServerSocketChannel serverSocketChannel;

        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));

            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            SelectionKey key = null;
            while(iterator.hasNext()){
                key = iterator.next();// 获得到当前的事件
                // ===============处理事件
                handle(key);
                // ===============
                iterator.remove(); // 移除事件
            }
        }
    }
}
