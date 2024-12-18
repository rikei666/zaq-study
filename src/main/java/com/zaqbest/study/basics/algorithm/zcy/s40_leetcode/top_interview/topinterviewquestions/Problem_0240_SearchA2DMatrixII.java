package com.zaqbest.study.basics.algorithm.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 有序的二维矩阵搜索问题
 *
 * 思路
 * 坐下或者右上开始搜索
 */
public class Problem_0240_SearchA2DMatrixII {

	public static boolean searchMatrix(int[][] m, int target) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return false;
		}
		int N = m.length;
		int M = m[0].length;
		int row = 0;
		int col = M - 1;
		while (row < N && col >= 0) {
			if (m[row][col] > target) {
				col--;
			} else if (m[row][col] < target) {
				row++;
			} else {
				return true;
			}
		}
		return false;
	}

}
