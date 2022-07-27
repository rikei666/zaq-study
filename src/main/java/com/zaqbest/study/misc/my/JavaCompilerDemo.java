package com.zaqbest.study.misc.my;

import cn.hutool.core.compiler.JavaSourceCompiler;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.JarClassLoader;
import cn.hutool.json.JSONUtil;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JavaCompilerDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
//        ClassLoader classLoader = JavaSourceCompiler.create(null)
//                .addSource("MyClass",
//                        FileUtil.readUtf8String("classpath:myfile/other/MyClass.java"))
//                .compile();
//        Class<?> myClass = classLoader.loadClass("mypackage.MyClass");
//        Object obj = myClass.newInstance();
//        System.out.println(JSONUtil.toJsonPrettyStr(obj));

        String jarPath="myfile/other/Customer.jar";
        JarClassLoader jarClassLoader =
                JarClassLoader.loadJar(FileUtil.file(jarPath));
        Class<?> myClass = jarClassLoader.loadClass("com.fusionfintrade.brms.engine.facade.Customer");

        System.out.println(myClass.getName());
    }
}
