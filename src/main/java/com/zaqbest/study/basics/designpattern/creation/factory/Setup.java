package com.zaqbest.study.basics.designpattern.creation.factory;

public class Setup {
    public static void main(String[] args) {
        Button button = new MacButtonFactory().createButton();
    }
}
