package com.github.net.bio.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * TODO 文件说明
 *
 * @author jiquanxi
 * @date 2019/06/21
 */
public class UdpServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8080);

        byte[] buffer = new byte[10];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        datagramSocket.receive(packet);

        System.out.println("receive udp data: " + new String(buffer));
        datagramSocket.close();
    }
}
