package com.zaqbest.study.misc.my;

public class StaticValidDemo {
    public static void main(String[] args) {
        System.out.println(MyClass.get().hashCode());
        System.out.println(MyClass.get().hashCode());
    }

    public static class MyClass{
        public static String get(){
            String s = new String();
            return s;
        }
    }
}


