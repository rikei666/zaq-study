package com.zaqbest.study.misc.my;

import cn.hutool.core.compiler.JavaSourceCompiler;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;

public class JavaCompilerDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = JavaSourceCompiler.create(null)
                .addSource("MyClass",
                        FileUtil.readUtf8String("classpath:myfile/other/MyClass.java"))
                .compile();
        Class<?> myClass = classLoader.loadClass("MyClass");
        Object obj = myClass.newInstance();
        System.out.println(JSONUtil.toJsonPrettyStr(obj));
    }
}
