package com.zaqbest.study.foundation.designpattern.creation.factory;

public class Setup {
    public static void main(String[] args) {
        Button button = new MacButtonFactory().createButton();
    }
}
