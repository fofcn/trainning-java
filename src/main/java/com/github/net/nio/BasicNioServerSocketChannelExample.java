package com.github.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * TODO 文件说明
 *
 * @author jiquanxi
 * @date 2019/06/21
 */
public class BasicNioServerSocketChannelExample {

    public static void main(String[] args) throws IOException {
        simplest();


    }

    private static void nonblocking() throws IOException {
        boolean stopped = false;
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        while (!stopped) {
            SocketChannel socketChannel =
                    serverSocketChannel.accept();

            if(socketChannel != null){
                //do something with socketChannel...
            }
        }

    }

    private static void simplest() throws IOException {
        boolean stopped = false;
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        while(!stopped){
            SocketChannel socketChannel =
                    serverSocketChannel.accept();

            //do something with socketChannel...
        }

        serverSocketChannel.close();
    }
}
