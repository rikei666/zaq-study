package com.zaqbest.study.foundation.alg.zcy.basic.zdemo.class01;

public class Code07_EvenTimesOddTimes_Study {

    public static void printOddTimesNum1(int[] arr) {
        int res = 0;

        for (int n: arr){
            res ^= n;
        }

        System.out.println(res);
    }


    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;

        for (int n: arr){
            eor ^= n;
        }

        int rightOne = eor & (~eor + 1);

        int one = 0;
        for (int n: arr){

            if ((n & rightOne) != 0){
                one ^= n;
            }
        }

        System.out.println(one);
        System.out.println(one ^ eor);
    }

    public static void main(String[] args) {
        printOddTimesNum1(new int[]{1,2,2,4,5,5,4});
        System.out.println("========================");
        printOddTimesNum2(new int[]{2,2,3,3,4,6,6,8});
    }
}
