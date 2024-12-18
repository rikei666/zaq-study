package com.zaqbest.study.basics.algorithm.zcy.s11_trainingcamp.term03.class02;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 长度为N的数组arr，一定可以组成N^2个数值对。
 * 例如arr = [3,1,2]，
 * 数值对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)，
 * 也就是任意两个数都有数值对，而且自己和自己也算数值对。
 * 数值对怎么排序？规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。所以上面的数值对排序的结果为：
 * (1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
 *
 * 给定一个数组arr，和整数k，返回第k小的数值对。
 */
public class Code07_KthMinPair {

	public static class Pair {
		public int x;
		public int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class PairComparator implements Comparator<Pair> {

		@Override
		public int compare(Pair arg0, Pair arg1) {
			return arg0.x != arg1.x ? arg0.x - arg1.x : arg0.y - arg1.y;
		}

	}

	// O(N^2 * log (N^2))的复杂度，你肯定过不了
	// 返回的int[]  长度是2，{3,1}  int[2] = [3,1]
	public static int[] kthMinPair1(int[] arr, int k) {
		int N = arr.length;
		if (k > N * N) {
			return null;
		}
		Pair[] pairs = new Pair[N * N];
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				pairs[index++] = new Pair(arr[i], arr[j]);
			}
		}
		Arrays.sort(pairs, new PairComparator());
		return new int[] { pairs[k - 1].x, pairs[k - 1].y };
	}

	// O(N*logN)的复杂度，你肯定过了
	public static int[] kthMinPair2(int[] arr, int k) {
		int N = arr.length;
		if (k > N * N) {
			return null;
		}
		// O(N*logN)
		Arrays.sort(arr);	
		// 第K小的数值对，第一维数字，是什么   是arr中
		int firstNum = arr[(k - 1) / N];
		int lessFirstNumSize = 0;// 数出比fristNum小的数有几个
		int firstNumSize = 0; // 数出==fristNum的数有几个
		// <= fristNum
		for (int i = 0; i < N && arr[i] <= firstNum; i++) {
			if (arr[i] < firstNum) {
				lessFirstNumSize++;
			} else {
				firstNumSize++;
			}
		}
		int rest = k - (lessFirstNumSize * N);
		return new int[] { firstNum, arr[(rest - 1) / firstNumSize] };
	}

	// O(N)的复杂度，你肯定蒙了
	public static int[] kthMinPair3(int[] arr, int k) {
		int N = arr.length;
		if (k > N * N) {
			return null;
		}
		// 在无序数组中，找到第K小的数，返回值
		// 第K小，以1作为开始
		int firstNum = getMinKth(arr, (k-1)/N + 1);
		//int firstNum = getMinKthByBFPRT(arr, ((k - 1) / N) + 1);
		int lessFirstNumSize = 0;
		int firstNumSize = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] < firstNum) {
				lessFirstNumSize++;
			}
			if (arr[i] == firstNum) {
				firstNumSize++;
			}
		}
		int rest = k - (lessFirstNumSize * N);
		return new int[] { firstNum, getMinKthByBFPRT(arr, ((rest - 1) / firstNumSize) + 1) };
	}

	/**
	 * 改写快排，时间复杂度O(N）
	 * 含义：在无序数组arr中，如果排序，arr[index]是什么
	 * @param arr
	 * @param index
	 * @return
	 */
	public static int getMinKth(int[] arr, int index){
		int L = 0;
		int R = arr.length - 1;
		int pivot = 0;
		int[] range = null;

		while (L < R){
			pivot = arr[L + (int)Math.random() * (R - L + 1)];
			range = partition(arr, L, R, pivot);
			if (index < range[0]){
				R = range[0] - 1;
			} else if (index > range[1]){
				L = range[1] + 1;
			} else {
				return pivot;
			}
		}

		return arr[L];
	}

	// 利用bfprt算法求解，是O(N)的过程
	// 在arr上，找到第K小的数，并返回。K范围是[1,N]，范围不是[0,N-1]
	// 对你来讲，它可能永远不会被你想起，但确实本题最优解的算法原型
	public static int getMinKthByBFPRT(int[] arr, int K) {
		return select(arr, 0, arr.length - 1, K - 1);
	}

	public static int select(int[] arr, int begin, int end, int i) {
		if (begin == end) {
			return arr[begin];
		}
		int pivot = medianOfMedians(arr, begin, end);
		int[] pivotRange = partition(arr, begin, end, pivot);
		if (i >= pivotRange[0] && i <= pivotRange[1]) {
			return arr[i];
		} else if (i < pivotRange[0]) {
			return select(arr, begin, pivotRange[0] - 1, i);
		} else {
			return select(arr, pivotRange[1] + 1, end, i);
		}
	}

	public static int medianOfMedians(int[] arr, int begin, int end) {
		int num = end - begin + 1;
		int offset = num % 5 == 0 ? 0 : 1;
		int[] mArr = new int[num / 5 + offset];
		for (int i = 0; i < mArr.length; i++) {
			int beginI = begin + i * 5;
			int endI = beginI + 4;
			mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
		}
		return select(mArr, 0, mArr.length - 1, mArr.length / 2);
	}

	public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
		int small = begin - 1;
		int cur = begin;
		int big = end + 1;
		while (cur != big) {
			if (arr[cur] < pivotValue) {
				swap(arr, ++small, cur++);
			} else if (arr[cur] > pivotValue) {
				swap(arr, cur, --big);
			} else {
				cur++;
			}
		}
		int[] range = new int[2];
		range[0] = small + 1;
		range[1] = big - 1;
		return range;
	}

	public static int getMedian(int[] arr, int begin, int end) {
		insertionSort(arr, begin, end);
		int sum = end + begin;
		int mid = (sum / 2) + (sum % 2);
		return arr[mid];
	}

	public static void insertionSort(int[] arr, int begin, int end) {
		for (int i = begin + 1; i != end + 1; i++) {
			for (int j = i; j != begin; j--) {
				if (arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
				} else {
					break;
				}
			}
		}
	}

	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	// 为了测试，生成值也随机，长度也随机的随机数组
	public static int[] getRandomArray(int max, int len) {
		int[] arr = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return arr;
	}

	// 为了测试
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// 随机测试了百万组，保证三种方法都是对的
	public static void main(String[] args) {
		int max = 100;
		int len = 30;
		int testTimes = 100000;
		System.out.println("test bagin, time times : " + testTimes);
		for (int i = 0; i < testTimes; i++) {
			int[] arr = getRandomArray(max, len);
			int[] arr1 = copyArray(arr);
			int[] arr2 = copyArray(arr);
			int[] arr3 = copyArray(arr);
			int N = arr.length * arr.length;
			int k = (int) (Math.random() * N) + 1;
			int[] ans1 = kthMinPair1(arr1, k);
			int[] ans2 = kthMinPair2(arr2, k);
			int[] ans3 = kthMinPair3(arr3, k);
			if (ans1[0] != ans2[0] || ans2[0] != ans3[0] || ans1[1] != ans2[1] || ans2[1] != ans3[1]) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
