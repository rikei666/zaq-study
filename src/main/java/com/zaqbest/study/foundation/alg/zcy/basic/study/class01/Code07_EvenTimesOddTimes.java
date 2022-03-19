package com.zaqbest.study.foundation.alg.zcy.basic.study.class01;

import cn.hutool.core.util.StrUtil;

public class Code07_EvenTimesOddTimes {
    //只有两个数字出现了奇数次，其他的都是偶数次
    public static void printOddTimes2(int[] arr){
        int xor = 0;
        for (int i = 0; i < arr.length; i++){
            xor ^= arr[i];
        }

        //获取最右边的一个1
        int rightOne = xor & (~xor + 1); //小tip

        int num1 = 0;
        for(int i =0; i < arr.length; i++){
            if ((arr[i] & rightOne) != 0){
                num1 ^= arr[i];
            }
        }

        String s = StrUtil.format("num1 = {}, num2 = {}", num1, num1 ^ xor);
        System.out.println(s);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,2,3,3,4,4,5,6};
        printOddTimes2(arr);
    }
}
