package com.github.net.nio;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

/**
 * TODO 文件说明
 *
 * @author jiquanxi
 * @date 2019/06/21
 */
public class BasicBufferExample {
    public static void main(String[] args) {

        /**
         * Buffer是一块内存
         */

        /**
         * Buffer的类型：
         * 1 ByteBuffer
         * 2 MappedByteBuffer
         * 3 CharBuffer
         * 4 DoubleBuffer
         * 5 FloatBuffer
         * 6 IntBuffer
         * 7 LongBuffer
         * 8 ShortBuffer
         */

        /**  ByteBuffer 基本使用
         // 四步搞定

         // 写数据到Buffer中
         // 调用 buffer.flip()
         // 从buffer中读取数据
         // 调用 buffer.clear() 或者 buffer.compact()
         */


        /**
         * buffer主要有三个属性
         * 1 capacity
         * 2 position
         * 3 limit
         */

        /**
         * position和limit依赖于Buffer处于读模式还是写模式,capacity的意义在读模式和写模式都是一样的
         */

        /**
         * 1 capacity 容量：亘古不变    你能读取或者写入的最大值
         *
         * 2 position 位置：写模式下：初始值为0， 当你write，WriteInt时，position累加，position最大值是capacity - 1
         *                 读模式下：调用Buffer.flip从写模式切换到读模式，position被重置为0,其他与写模式的偏移累加一致
         *
         * 3 limit: 写模式下：limit的值与capacity一致
         *          读模式下：limit用来标志你能够读取了多少数据，意思就是你只能读取写入的数据量。
         *
         */


        // 基本使用
        // 新建一个缓冲区,可以存放10个整数
        IntBuffer intBuffer = IntBuffer.allocate(10);

        // 没有元素的时候，注意：get的内容int为0，你有可能put 0,所以可以判断一下position
        // 调用intBuffer.position()
        // 否则get 会让position+1
        System.out.println("current position should be 0, actually: " + intBuffer.position());
        if (intBuffer.position() > 0) {
            int empty_get = intBuffer.get();
        }

        // 连续放入10个元素
        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.put(3);
        intBuffer.put(4);
        intBuffer.put(5);
        intBuffer.put(6);
        intBuffer.put(7);
        intBuffer.put(8);
        intBuffer.put(9);
        intBuffer.put(10);

        // 放入第11个元素时会溢出
        try {
            intBuffer.put(11);
        } catch (BufferOverflowException e) {
            System.out.println("here should be overflow");
            e.printStackTrace();
        }

        // 写模式切换到读模式
        intBuffer.flip();

        // 从buffer中读取数据
        int one = intBuffer.get();
        System.out.println("read from IntBuffer: should be 1, actually: " + one);
        // 重定向读取位置为0
        intBuffer.rewind();
        one = intBuffer.get();
        System.out.println("read from IntBuffer: should be 1, actually: " + one);

        //intBuffer.get(new int[9]);

        // 读模式切换到写模式
        // 将position设置为0， limit设置为capacity
        // 如果不想继续读取未被读取的数据，intBuffer.clear();
        intBuffer.compact();

        intBuffer.put(11);

        intBuffer.flip();

        // 读取第一个元素，应该是2
        int two = intBuffer.get();
        System.out.println("read from IntBuffer: should be 2, actually: " + two);
    }
}
