package com.lianxi.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;


public class Dome5 {
    public static void main(String[] args) {
        ArrayList<String> a=new ArrayList<>();
        Collections.addAll(a,"zhangsna,23","lisi,24","wangwu,25");
        Map<String, Integer> collect = a.stream().filter( s->  Integer.parseInt(s.split(",")[1])>=24 ).collect(Collectors.toMap( s-> {
                String[] b = s.split(",");
                return b[0];
        },  s ->{
                String[] b = s.split(",");
                return Integer.parseInt(b[1]);
   }));
        System.out.println(collect);

    }
}
