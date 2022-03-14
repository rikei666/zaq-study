package com.zaqbest.study.alg.zcy.top_interview.topinterviewquestions;

public class Problem_0007_ReverseInteger {

	public static int reverse(int x) {
		boolean neg = ((x >>> 31) & 1) == 1;//最高位为1即为负数
		x = neg ? x : -x; //按照负数处理，因为负数比正数多了1个数
		int m = Integer.MIN_VALUE / 10;
		int o = Integer.MIN_VALUE % 10;
		int res = 0;
		while (x != 0) {
			if (res < m  //乘10肯定溢出
					|| (res == m && x % 10 < o) //乘10再加上余数是否会溢出
			) {
				return 0;
			}
			res = res * 10 + x % 10;
			x /= 10;
		}
		return neg ? res : Math.abs(res);
	}

}
