package com.zaqbest.study.foundation.designpattern.creation.factory;

public class WindowsButtonFactory implements ButtonFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
