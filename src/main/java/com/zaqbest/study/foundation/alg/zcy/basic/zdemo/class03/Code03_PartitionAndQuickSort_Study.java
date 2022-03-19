package com.zaqbest.study.foundation.alg.zcy.basic.zdemo.class03;

import com.alibaba.fastjson.JSON;
import com.zaqbest.study.foundation.alg.utils.ArrayUtil;

import java.util.Arrays;

public class Code03_PartitionAndQuickSort_Study {
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }


    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R){
            return new int[]{-1, -1};
        }

        if (L == R){
            return new int[]{L, R};
        }

        int less = L -1; //
        int more = R;
        int index = L;

        while (index < more){
            if (arr[index] == arr[R]){
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, less+1, index);
                less++;
                index++;
            } else {
                swap(arr, more-1, index);
                more--;
            }
        }

        swap(arr, R, more);

        return new int[]{less+1, more};
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        for (int i =0; i < 100; i++){
            int[] arr = ArrayUtil.generateRandomArray(100, 100);
            int[] arr2 = ArrayUtil.copyOfArray(arr);

            quickSort3(arr);
            Arrays.sort(arr2);

            if (!Arrays.equals(arr, arr2)){
                System.out.println("oops!");
                System.out.println(JSON.toJSONString(arr));
                System.out.println(JSON.toJSONString(arr2));
                break;
            }
        }

        System.out.println("finish");

    }
}
