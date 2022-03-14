package com.zaqbest.study.dp.creation.factory;

public class MacButtonFactory implements ButtonFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }
}
