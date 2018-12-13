package com.ld.sbjwtjpa.utils;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("b","a");
        map.put("ab","b");
        map.put("c","c");
        map.put("d","d");
        map.forEach((k,v)->{
            System.out.println(k);
        });
    }
}
