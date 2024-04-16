package com.lianxi.domain;

import java.io.IOException;
import java.net.*;
import java.text.ParseException;

public class Test {
    public static void main(String[] args) throws ParseException, IOException {
        InetAddress byName = InetAddress.getByName("192.168.31.246");
//        System.out.println(byName);
//        System.out.println(byName.getHostAddress());
//        System.out.println(byName.getHostName());
        DatagramSocket ds=new DatagramSocket();
        String str="你好";
        int port=10086;
        byte[] bytes=str.getBytes();
        DatagramPacket dp=new DatagramPacket(bytes,bytes.length,byName,port);
        ds.send(dp);
        ds.close();;
    }

}

