package com.zaqbest.study.misc;

import java.math.BigInteger;

public class Factorial {
    public static void main(String[] args) {
        BigInteger res = new BigInteger("1");
        for (int i = 1; i <= 15; i++){
            res = res.multiply(new BigInteger(String.valueOf(i)));
        }
        System.out.println(res);
    }
}
