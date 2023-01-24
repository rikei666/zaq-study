package com.zaqbest.study.misc.my;

public class Temp {
    public static void main(String[] args) {
        final String a1 = "hello";
        String a2 = "hello";
        String b = "hello2";
        String c1 = a1 + 2;
        String c2 = a2 + 2;
        System.out.println(b == c1); //true
        System.out.println(b == c2); //false
    }
}
