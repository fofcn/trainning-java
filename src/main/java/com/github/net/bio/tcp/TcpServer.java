package com.github.net.bio.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TODO 文件说明
 *
 * @author jiquanxi
 * @date 2019/06/21
 */
public class TcpServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            boolean isStopped = false;
            while(!isStopped) {
                Socket clientSocket = serverSocket.accept();

                //do something with clientSocket
                System.out.println("client address:" + clientSocket.getInetAddress().getHostName());
                InputStream clientInput = clientSocket.getInputStream();
                byte[] clientContents = new byte[1024];
                int readBytes = clientInput.read(clientContents);
                System.out.println("read from client: " + new String(clientContents));
                OutputStream out = clientSocket.getOutputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
