package com.github.net.bio.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TODO 文件说明
 *
 * @author jiquanxi
 * @date 2019/06/21
 */
public class TcpClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8080);) {
            OutputStream out = socket.getOutputStream();
            for (int i = 0; i < 100; i++) {
                //DataOutputStream dis = new DataOutputStream(out);
                //dis.writeInt(1000);
                out.write("some data".getBytes());
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
