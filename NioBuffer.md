#Java NIO Buffer基本使用
Buffer是一块内存，主要用在NIO Channel，比如FileChannel,SocketChannel。

对Channel的读写都是直接操作Buffer对象。

Buffer是一个工具类，提供了操作这个内存块的方法。

Buffer的实现主要有以下几种：
Buffer的类型：<br>
**ByteBuffer**<br>
**MappedByteBuffer**<br>
**CharBuffer**<br>
**DoubleBuffer**<br>
**FloatBuffer**<br>
**IntBuffer**<br>
**LongBuffer**<br>
**ShortBuffer**<br>

ByteBuffer基本使用，四步搞定<br>
1. 写数据到Buffer中<br>
2. 调用buffer.flip()<br>
3. 从buffer中读取数据<br>
4. 调用buffer.clear()或者buffer.compact()<br>

buffer主要有四个主要属性：<br>
**capacity: Buffer最大容量**<br>
**position: 读写偏移**<br>
**limit: 最大可读容量**<br>
**mark: 标记，可以调用reset重读或重写**<br>
只要理解了这四个属性，基本就掌握了Buffer的使用和原理。

我们来看下这三个属性的说明：
position和limit依赖于Buffer处于读模式还是写模式,capacity的意义在读模式和写模式都是一样的
1 capacity：Buffer的最大容量

2 position位置：写模式下：初始值为0，当你write，WriteInt时，position累加，position最大值是capacity-1
读模式下：调用Buffer.flip从写模式切换到读模式，position被重置为0,其他与写模式的偏移累加一致

3 limit:写模式下：limit的值与capacity一致
读模式下：limit用来标志你能够读取了多少数据，意思就是你只能读取写入的数据量。

4 mark:重置读写偏移 

读写可以参考下图：
![Alt text](img/buffers-modes.png "Buffer Capacity, position, limit在读写模式下的展示")

写模式下，posistion累加，limit和capacity不变，即可以写入的最大字数。
每次调用一次put()或者putXXXX()时，position+n(n为你写入的字数，如果写入一个字，则position+1，如果你写入字数组，position+数组.长度)

读模式：当调用flip()时，将limit设置为position，position设置为0，mark设置为-1，limit为能够读取的最大字数。
每调用一次get或者getXXX()时，position+n(n为你读取的字数，如果读取一个字，则position+1，如果你读取字数组，position+数组.长度)

Capacity在读模式和写模式下都不变。

Buffer.clear()和Buffer.compact()
读取完数据后，如果想要Buffer能够再次写入，可以调用clear和compact函数。

如果调用clear函数，position设置为0.limit设置为capacity，其实里面的数据并没有被清除，只是覆盖写。
但是有一个场景是，数据没有读取完成之前，你需要要先写入一些数据时，可以调用compact函数，这个函数会把没有读取完的数据拷贝到Buffer开始处，然后把position设置为n（n为未读取数据的长度），limit设置为capacity。


下面是一个简单的Demo：
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
