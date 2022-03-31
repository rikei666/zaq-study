package com.zaqbest.study.foundation.alg.zcy.top_interview.topinterviewquestions;

import java.util.HashMap;

/**
 * 分数转换为循环小数
 *
 * 思路：
 * coding问题
 *
 * 俗称烂题，没啥算法技巧
 */
public class Problem_0166_FractionToRecurringDecimal {

	public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) {
			return "0";
		}
		StringBuilder res = new StringBuilder();
		// "+" or "-"
		res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
		long num = Math.abs((long) numerator);
		long den = Math.abs((long) denominator);
		// integral part
		res.append(num / den);
		num %= den;
		if (num == 0) {
			return res.toString();
		}
		// fractional part
		res.append(".");
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		map.put(num, res.length());
		while (num != 0) {
			num *= 10;
			res.append(num / den);
			num %= den;
			if (map.containsKey(num)) {
				int index = map.get(num);
				res.insert(index, "(");
				res.append(")");
				break;
			} else {
				map.put(num, res.length());
			}
		}
		return res.toString();
	}

}
