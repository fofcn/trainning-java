package com.github.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * TODO 文件说明
 *
 * @author jiquanxi
 * @date 2019/06/21
 */
public class BasicSocketChannelExample {

    public static void main(String[] args) throws IOException {
        BlockedSocketChannel();

    }

    private static void nonblockedSocketChannel() throws IOException {
        // 1 打开一个SocketChanel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        socketChannel.configureBlocking(false);

        // 从SocketChannel中读取数据
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);


        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf1 = ByteBuffer.allocate(48);
        buf1.clear();
        buf1.put(newData.getBytes());

        buf1.flip();

        while(buf1.hasRemaining()) {
            socketChannel.write(buf);
        }

        // 关闭SocketChannel
        socketChannel.close();
    }

    private static void BlockedSocketChannel() throws IOException {
        // 1 打开一个SocketChanel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        // 从SocketChannel中读取数据
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);


        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf1 = ByteBuffer.allocate(48);
        buf1.clear();
        buf1.put(newData.getBytes());

        buf1.flip();

        while(buf1.hasRemaining()) {
            socketChannel.write(buf);
        }

        // 关闭SocketChannel
        socketChannel.close();
    }
}
