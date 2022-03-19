package com.zaqbest.study.foundation.alg.zcy.basic.zdemo.class03;

import com.alibaba.fastjson.JSON;
import com.zaqbest.study.foundation.alg.utils.ArrayUtil;

import java.util.Arrays;

public class Code01_MergeSort_Study {
    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2)
            return;

        process(arr, 0, arr.length -1);
    }

    private static void process(int[] arr, int L, int R){
        if (L == R){
            return;
        }

        int mid = (L + R) /2;
        process(arr, L , mid);
        process(arr, mid +1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R){
        int[] ans = new int[R - L + 1]; //R-L+1
        int p1 = L;
        int p2 = M+1;
        int index = 0;
        while (p1 <= M && p2 <= R){
            if (arr[p1] <= arr[p2]){
               ans[index++] = arr[p1++];
            } else {
                ans[index++] = arr[p2++];
            }
        }

        while (p1 <= M){
            ans[index++] = arr[p1++];
        }

        while (p2 <= R){
            ans[index++] = arr[p2++];
        }

        for (int i =0; i < ans.length;i++){
            arr[L+i] = ans[i]; // L+i
        }
    }

    public static void main(String[] args) {
        for (int i =0; i < 20; i++){
            int[] arr = ArrayUtil.generateRandomArray(10, 10);
            int[] arr2 = ArrayUtil.copyOfArray(arr);

            mergeSort(arr);
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
