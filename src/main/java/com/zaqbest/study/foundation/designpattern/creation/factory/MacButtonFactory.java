package com.zaqbest.study.foundation.designpattern.creation.factory;

public class MacButtonFactory implements ButtonFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }
}
