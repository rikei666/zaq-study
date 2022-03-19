package com.zaqbest.study.foundation.dp.structure.proxy;

public class ProxySubject implements Subject{

    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void action() {
        subject.action();
    }
}
