package com.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class NioServerHandler implements Runnable{
    private SelectionKey selectionKey;

    public NioServerHandler(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }

    @Override
    public void run() {
        try {
            if (selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                // 从通道读取数据到缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                // 输出客户端发送过来的消息
                socketChannel.read(buffer);
                buffer.flip();
                System.out.println("收到客户端" + socketChannel.socket().getInetAddress().getHostName() + "的数据：" + new String(buffer.array()));

                //将数据添加到key中
                ByteBuffer outBuffer = ByteBuffer.wrap(buffer.array());

                // 将消息回送给客户端
                socketChannel.write(outBuffer);
                selectionKey.cancel();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
