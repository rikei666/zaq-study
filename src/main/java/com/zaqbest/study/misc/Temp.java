package com.zaqbest.study.misc;


import cn.hutool.core.util.StrUtil;

public class Temp {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++){
            for (int j = 1; j <= i; j++){
                System.out.print(StrUtil.format("{} ^ {} = {} ", i, j, i ^ j));
            }
            System.out.println();
        }
    }
}
