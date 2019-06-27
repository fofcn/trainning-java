package com.github.net.nio;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * TODO 文件说明
 *
 * @author jiquanxi
 * @date 2019/06/21
 */
public class BasicSelectorExample {

    /**
     * 什么是selector?
     *
     * 用来测试Channel实例，并且可以确定哪些Channel可读，可写等事件
     *
     */


    /**
     * 为什么使用Selector?
     *
     * 使用Selector的好处就是单线程就可以处理多个channel事件
     *
     * 操作系统进行线程上下文切换代价昂贵，而且一个线程就需要占用多个系统的资源。
     *
     */

    public static void main(String[] args) throws IOException {
        // 1 创建Selector
        Selector selector = Selector.open();

        // 2 向Selector注册Channel
        SocketChannel channel = SocketChannel.open();
        // channel必须设置为非阻塞模式
        // 所以你无法向selector注册FileChannel，因为FileChannel无法切换到非阻塞模式
        // SocketChannel可以
        channel.configureBlocking(false);

        // register 第二个参数可选值：
        // Connect
        // Accept
        // Read
        // Write
        // 如果对多个事件感兴趣，就可以注册多个事件
        //int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

        // 现在开始遍历感兴趣的事件集合
        int interestSet = key.interestOps();
        boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
        boolean isInterestedInConnect = (interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;
        boolean isInterestedInRead    = (interestSet & SelectionKey.OP_READ) == SelectionKey.OP_READ;
        boolean isInterestedInWrite   = (interestSet & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE;

        key.isAcceptable();
        key.isConnectable();
        key.isReadable();
        key.isWritable();


        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

        while(keyIterator.hasNext()) {

            SelectionKey keyTest = keyIterator.next();

            if(keyTest.isAcceptable()) {
                // a connection was accepted by a ServerSocketChannel.

            } else if (keyTest.isConnectable()) {
                // a connection was established with a remote server.

            } else if (keyTest.isReadable()) {
                // a channel is ready for reading

            } else if (keyTest.isWritable()) {
                // a channel is ready for writing
            }

            keyIterator.remove();
        }

        selector.close();
    }

}
