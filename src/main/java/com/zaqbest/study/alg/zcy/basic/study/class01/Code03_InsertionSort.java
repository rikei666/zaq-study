package com.zaqbest.study.alg.zcy.basic.study.class01;

import com.zaqbest.study.alg.utils.ArrayUtil;

import java.util.Arrays;

public class Code03_InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2){
            return;
        }
        //0~0已经有序
        //使1-N变成有序
        for (int i = 1; i < arr.length; i++){
          for (int j = i; j > 0 && arr[j] < arr[j-1]; j--){
              ArrayUtil.swap(arr, j, j-1);
          }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++){
            int[] arr1 = ArrayUtil.generateRandomArray(1000, 100);
            int[] arr2 = ArrayUtil.copyOfArray(arr1);

            insertionSort(arr1);
            Arrays.sort(arr2);

            if (!Arrays.equals(arr1, arr2)){
                System.out.println("NG");
                break;
            }
        }
        System.out.println("OK");
    }
}
