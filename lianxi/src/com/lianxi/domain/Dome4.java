package com.lianxi.domain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Dome4 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080); // 监听指定端口
        System.out.println("server is running...");
        while (true) {
            Socket sock = ss.accept();
            System.out.println("connected from " + sock.getRemoteSocketAddress());
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
            String first = in.readLine();
            if (first.startsWith("POST /")) {
                System.out.println(first);
                StringBuilder headers = new StringBuilder();
                String header;
                // 读取完整的请求头
                while ((header = in.readLine()) != null && !header.isEmpty()) {
                    System.out.println(header);
                    headers.append(header).append("\r\n");
                }
                StringBuilder requestBody = new StringBuilder();
                int contentLength = getContentLength(headers.toString());
                for (int i = 0; i < contentLength; i++) {
                    requestBody.append((char) in.read());
                }
                System.out.println("Request Body: " + requestBody.toString());
                // 发送响应

//                String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1>Hello, Client!</h1></body></html>";
                String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n";
                response += "Access-Control-Allow-Origin: *\r\n";
                response += "Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS\r\n";
                response += "Access-Control-Allow-Headers: Content-Type\r\n\r\n";
                response += "<html><body><h1>Hello, Client!</h1></body></html>";
                out.write(response);
                out.flush();
                // 关闭套接字
                sock.close();
                System.out.println("Client disconnected.");
            }else if (first.startsWith("GET /")){
                System.out.println(first);
                StringBuilder headers = new StringBuilder();
                String header;
                // 读取完整的请求头
                while ((header = in.readLine()) != null && !header.isEmpty()) {
                    System.out.println(header);
                    headers.append(header).append("\r\n");
                }
                // 发送响应
//                String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1>Hello, Client!</h1></body></html>";
                   String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n";
                response += "Access-Control-Allow-Origin: *\r\n";
                response += "Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS\r\n";
                response += "Access-Control-Allow-Headers: Content-Type\r\n\r\n";
                response += "<html><body><h1>Hello, Client!</h1></body></html>";
                out.write(response);
                out.flush();
                // 关闭套接字
                sock.close();
                System.out.println("Client disconnected.");
            }
        }
    }
    private static int getContentLength(String headers) {
        String[] lines = headers.split("\r\n");
        for (String line : lines) {
            if (line.startsWith("Content-Length:")) {
                return Integer.parseInt(line.substring("Content-Length:".length()).trim());
            }
        }
        return 0;
    }

}

