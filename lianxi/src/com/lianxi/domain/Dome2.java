package com.lianxi.domain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Dome2 {
    public static void main(String[] args) throws IOException {
//        DatagramSocket ds=new DatagramSocket(10086);
//        byte[]bytes=new byte[1024];
//        DatagramPacket dp=new DatagramPacket(bytes,bytes.length);
//        ds.receive(dp);
//        byte[]bytes1=dp.getData();
//        System.out.println(new String(bytes1));
//        ds.close();
                ServerSocket serverSocket = new ServerSocket(8080);
                System.out.println("Server is running...");
1
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Connected to client: " + clientSocket.getRemoteSocketAddress());

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String request = "";
                    String line;
                    while ((line = in.readLine()) != null && !line.isEmpty()) {
                        request += line + "\n";
                    }

                    System.out.println("Received request:\n" + request);

                    // Send a response back to the client
                    String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1>Hello, Client!</h1></body></html>";
  //                   String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n";
  //response += "Access-Control-Allow-Origin: *\r\n";
  //response += "Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS\r\n";
  //response += "Access-Control-Allow-Headers: Content-Type\r\n\r\n";
  //response += "<html><body><h1>Hello, Client!</h1></body></html>";
                    out.write(response);
                    out.write("Access-Control-Allow-Origin: *\r\n"); // 允许所有域名跨域访问，也可以设置具体的域名
                    out.flush();

                    clientSocket.close();
                    System.out.println("Client disconnected.");
                }
            }
        }

