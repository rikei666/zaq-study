package com.zaqbest.study.misc;

import cn.hutool.core.util.RandomUtil;

public class Temp {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++){
            System.out.println(RandomUtil.randomInt(700, 1100));
        }
    }
}
