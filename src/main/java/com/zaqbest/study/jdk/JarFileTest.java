package com.zaqbest.study.jdk;

import java.io.IOException;
import java.util.jar.JarFile;

public class JarFileTest {
    public static void main(String[] args) throws IOException {
        JarFile jarFile = new JarFile(JarFileTest.class.getResource("/myfile/tools.jar").getFile());
        jarFile.stream()
                .filter(x->x.getName().endsWith(".class"))
                .forEach(x-> System.out.println(x.getName()));
    }
}
