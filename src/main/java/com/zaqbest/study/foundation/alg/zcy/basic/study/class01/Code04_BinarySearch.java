package com.zaqbest.study.foundation.alg.zcy.basic.study.class01;

import cn.hutool.core.util.StrUtil;
import com.zaqbest.study.foundation.alg.utils.ArrayUtil;

import java.util.Arrays;
import java.util.Random;

public class Code04_BinarySearch {

    /**
     * 二分查找
     *
     * @param sortedArr 已经排好序的数组
     * @param target 目标值
     * @return
     */
    public static boolean exist(int[] sortedArr, int target){
        if (sortedArr == null || sortedArr.length == 0){
            return false;
        }

        int L = 0, R = sortedArr.length-1;
        while (L <= R) {
            int M = (L+R)/2;
            if (sortedArr[M] == target){
                return true;
            }
            if (sortedArr[M] > target){
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {


        for (int i = 0; i < 1000; i++){
            int[] arr = ArrayUtil.generateRandomArray(1000, 1000);
            ArrayUtil.sort(arr);
            int target = new Random().nextInt(1200);

            boolean r1 = exist(arr, target);
            boolean r2 = Arrays.binarySearch(arr, target) > 0;

            if (r1 != r2){
                String s = StrUtil.format("arr={},arr.len={},target={}, res1={}",
                        arr, arr.length, target, r1);
                System.out.println(s);
                throw new RuntimeException("NG");
            }
        }

        System.out.println("OK");
    }
}
