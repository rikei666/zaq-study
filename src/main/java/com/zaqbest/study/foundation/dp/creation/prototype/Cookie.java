package com.zaqbest.study.foundation.dp.creation.prototype;

public class Cookie implements Cloneable{
    public Object clone() throws CloneNotSupportedException{
        return (Cookie)super.clone();
    }
}
