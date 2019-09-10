package com.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingEchoServer {
    public static int port = 9999;

    public static void main(String[] args) {
        System.out.println("listening on port :" + port);
        ServerSocketChannel serverSocketChannel;
        Selector selector = null;

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

            while(iterator.hasNext()){
                SelectionKey next = iterator.next();
                iterator.remove();

                if(next.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel)next.channel();
                    try {
                        SocketChannel accept = channel.accept();
                        System.out.println("accept " + accept);

                        accept.configureBlocking(false);

                        SelectionKey selectionKey = accept.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        ByteBuffer buffer = ByteBuffer.allocate(100);

                        selectionKey.attach(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(next.isReadable()){
                    SocketChannel channel = (SocketChannel)next.channel();
                    ByteBuffer buffer = (ByteBuffer) next.attachment();
                    try {
                        int read = channel.read(buffer);

                        System.out.println(new String(buffer.array()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(next.isWritable()){
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer buffer = (ByteBuffer) next.attachment();

                    buffer.flip();
                    buffer.put("server".getBytes());
                    try {
                        channel.write(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    buffer.compact();
                }
            }
        }
    }
}
