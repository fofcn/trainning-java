package com.github.net.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Channel的实现
 *
 * FileChannel 读取文件
 * DatagramChannel UDP
 * SocketChannel TCP
 * ServerSocketChannel TCP 服务端
 *
 * @author jiquanxi
 * @date 2019/06/21
 */
public class BasicFileChannelExample {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            buf.flip();

            while(buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

}
