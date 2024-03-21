package com.zaqbest.study.basics.designpattern.creation.factory;

public class MacButtonFactory implements ButtonFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }
}
