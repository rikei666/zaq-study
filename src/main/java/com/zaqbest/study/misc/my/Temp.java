package com.zaqbest.study.misc.my;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;

public class Temp {
    public static void main(String[] args) {
        double d = 9999.9999511111;
        BigDecimal bigDecimal = NumberUtil.round(d, 4);
        d= bigDecimal.doubleValue();
        System.out.println(d);
    }
}
