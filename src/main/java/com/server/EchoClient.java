package com.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoClient {
    private static Selector selector = null;
    public static void main(String[] args) {
        try {

            selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));

            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            while(true){
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while(iterator.hasNext()){
                    SelectionKey next = iterator.next();

                    if(next.isConnectable()){
                        SocketChannel channel = (SocketChannel)next.channel();
                        if(channel.finishConnect()){
                            channel.register(selector,SelectionKey.OP_WRITE);
                        }
                    }

                    if(next.isReadable()){
                        SocketChannel channel = (SocketChannel)next.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.clear();
                        int temp = channel.read(buffer);

                        if(temp > 0) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);

                            System.out.println("read:" + new String(bytes));
                        }
                        channel.register(selector,SelectionKey.OP_WRITE);

                    }

                    if(next.isWritable()){
                        System.out.println("write:");
                        SocketChannel channel = (SocketChannel) next.channel();

                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.clear();
                        buffer.put("hello server".getBytes());

                        buffer.flip();

                        try {
                            while(buffer.hasRemaining()) {
                                channel.write(buffer);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        channel.register(selector,SelectionKey.OP_READ);

                    }
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void handle(SelectionKey key) throws IOException {
        // 连接就绪
        try {
            if (key.isConnectable()) {
                handleConnectable(key);
            }
            // 读就绪
            if (key.isReadable()) {
                handelReadable(key);
            }
        } catch (Exception e) {
            key.cancel();
            if (key.channel() != null) {
                try {
                    key.channel().close();
                } catch (IOException e1) {
                }
            }
        }
    }

    private static void handelReadable(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int temp = sc.read(buffer); // 从channel读到buffer
        String content = "来自服务端的: ";
        if (temp > 0) {// 代表读完毕了,准备写(即打印出来)
            buffer.flip(); // 为write()准备
            // =====取出buffer里的数据
            byte[] bytes = new byte[buffer.remaining()]; // 创建字节数组
            buffer.get(bytes);// 将数据取出放到字节数组里
            content += new String(bytes);
            content += "============";
            System.out.println(content);
            // doWrite(sc, content);
        }
        // key.interestOps(SelectionKey.OP_READ);// TODO:
    }
    private static void handleConnectable(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        if (sc.finishConnect()) {
            // 将关注的事件变成read
            sc.register(selector, SelectionKey.OP_READ);
            doWrite(sc, "dddddd");
        }
    }
    private static void doWrite(SocketChannel sc, String data) throws IOException {
        byte[] req = data.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(req);
        byteBuffer.put(req);
        byteBuffer.flip();
        sc.write(byteBuffer);
        // if (!byteBuffer.hasRemaining()) {
        // System.out.println("Send successed : " + data);
        // }
    }
}
