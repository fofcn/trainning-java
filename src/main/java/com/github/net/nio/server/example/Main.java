package com.github.net.nio.server.example;

import com.github.net.nio.server.IMessageProcessor;
import com.github.net.nio.server.Message;
import com.github.net.nio.server.Server;
import com.github.net.nio.server.http.HttpMessageReaderFactory;

import java.io.IOException;

/**
 * Created by jjenkov on 19-10-2015.
 *
 *
 * http://tutorials.jenkov.com/java-nio/non-blocking-server.html  文档地址
 * https://github.com/jjenkov/java-nio-server  github 地址
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Length: 38\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body>Hello World!</body></html>";

        byte[] httpResponseBytes = httpResponse.getBytes("UTF-8");

        IMessageProcessor messageProcessor = (request, writeProxy) -> {
            System.out.println("Message Received from socket: " + request.socketId);

            Message response = writeProxy.getMessage();
            response.socketId = request.socketId;
            response.writeToMessage(httpResponseBytes);

            writeProxy.enqueue(response);
        };

        Server server = new Server(8080, new HttpMessageReaderFactory(), messageProcessor);

        server.start();

    }


}
