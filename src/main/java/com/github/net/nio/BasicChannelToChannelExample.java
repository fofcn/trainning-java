package com.github.net.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO 文件说明
 *
 * @author jiquanxi
 * @date 2019/06/21
 */
public class BasicChannelToChannelExample {

    public static void main(String[] args) {
        try {
            transferFrom();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            transferTo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void transferFrom() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("data/fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("data/toFile.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
    }

    public static void transferTo() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("data/fromFile.txt", "rw");
        FileChannel      fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("data/toFile1.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }

    public static void filesCopy() throws IOException {
        Path srcPath = Paths.get("");
        Path descPath = Paths.get("");
        Files.copy(srcPath, descPath);
    }

}
