package com.zaqbest.study.misc;

import cn.hutool.core.util.StrUtil;

public class Temp {
    public static void main(String[] args) {
        //a*31 + b*30 + c*29 = 366
    for (int a = 0; a <= 366 / 31 + 1; a++){
        for (int b = 0; b <= 366 / 30 + 1; b++){
            for (int c = 0; c <= 366 / 29 +1; c++){
                if (a * 31 + b * 30 + c * 29 == 366){
                    System.out.println(StrUtil.format("{}-{}-{}", a, b ,c ));
                }
            }
        }
    }
    }
}
