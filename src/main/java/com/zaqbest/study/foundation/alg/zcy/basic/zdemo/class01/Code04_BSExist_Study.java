package com.zaqbest.study.foundation.alg.zcy.basic.zdemo.class01;

import com.zaqbest.study.foundation.alg.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 二分查找
 */
public class Code04_BSExist_Study {

	public static boolean exist(int[] sortedArr, int num) {
		if (sortedArr == null || sortedArr.length == 0)
			return false;

		int L = 0, R = sortedArr.length-1;

		while (L <= R){
			int mid = (L + R) /2;
			if (sortedArr[mid] == num){
				return true;
			} else if (sortedArr[mid] > num){
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		for (int i =0; i < 1000; i++){
			int[] arr = ArrayUtil.generateRandomArray(500, 1000);
			Arrays.sort(arr);

			boolean res1 = Arrays.binarySearch(arr, 500) >= 0;
			boolean res2 = exist(arr, 500);

			if (res1 != res2){
				System.out.println("oops!");
				break;
			}
		}

		System.out.println("fininsh!");
	}

}
