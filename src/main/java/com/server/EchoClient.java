package com.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class EchoClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));

            socketChannel.configureBlocking(false);

            while(true) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                byteBuffer.put("hi".getBytes());

                socketChannel.write(byteBuffer);

                Thread.sleep(2000);

                byteBuffer.flip();

                socketChannel.read(byteBuffer);

                System.out.println(new String(byteBuffer.array()));

                Thread.sleep(2000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
