package com.zaqbest.study.foundation.dp.creation.singleton;

public class Singleton {
    private Singleton(){}
    private Singleton instance = null;

    public Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
