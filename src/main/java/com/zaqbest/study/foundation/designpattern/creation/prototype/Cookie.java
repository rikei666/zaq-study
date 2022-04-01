package com.zaqbest.study.foundation.designpattern.creation.prototype;

public class Cookie implements Cloneable{
    public Object clone() throws CloneNotSupportedException{
        return (Cookie)super.clone();
    }
}
