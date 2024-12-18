package com.zaqbest.study.basics.algorithm.zcy.s50_weekly.class_2022_01_4_week;

// things是一个N*3的二维数组，商品有N件，商品编号从1~N
// 比如things[3] = [300, 2, 6]
// 代表第3号商品：价格300，重要度2，它是6号商品的附属商品
// 再比如things[6] = [500, 3, 0]
// 代表第6号商品：价格500，重要度3，它不是任何附属，它是主商品
// 每件商品的收益是价格*重要度，花费就是价格
// 如果一个商品是附属品，那么只有它附属的主商品购买了，它才能被购买
// 任何一个附属商品，只会有1个主商品
// 任何一个主商品的附属商品数量，不会超过2件
// 主商品和附属商品的层级最多有2层
// 给定二维数组things、钱数money，返回整体花费不超过money的情况下，最大的收益总和
// 测试链接 : https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4
// 请把如下的代码的主类名改为"Main", 可以直接通过
import java.util.ArrayList;
import java.util.Scanner;

public class Code01_BuyThingsAboutCollocation {

//	// index.....货自由选择！剩余的钱数是rest，
//	// 返回：在不花超的情况下，返回最大的价值
//	public static int f(int[] prices, int[] values, int index, int rest) {
//		if(rest < 0) {
//			return -1;
//		}
//		// rest >= 0
//		if(index == prices.length) { // 没货了！
//			return 0;
//		}
//		// rest >=0 有货！
//		// 可能1 ：index.... 自由选择，不要index位置的货！
//		int p1 = f(prices, values, index + 1, rest);
//		// 可能性2：index.... 自由选择，要index位置的货！
//		int p2 = -1;
//		int next = f(prices, values, index + 1, rest - prices[index]);
//		if(next != -1) {
//			p2 = values[index] + next;
//		}
//		return Math.max(p1, p2);
//	}
//	
//	//  商品组     主商品，重要度3，价格9          附件！重要度6，价格7
//	//   0 :    [ [3,9],                      [6,7],                   [4, 3]        ]
//	//   1 :    [ [4,7] ]  没有附件，只有主商品
//	//   2 :    [ [5,100] , [2,1]    ]
//	
//	public static int p(int[][][] matrix, int index, int rest) {
//		if(rest < 0) {
//			return -1;
//		}
//		if(index == matrix.length) {
//			return 0;
//		}
//		// 有商品组！也有钱>=0;
//		int[][] team = matrix[index];
//		if(team.length == 1) { // 要 、不要
//			int p1 = p(matrix, index + 1, rest);
//			int p2 = -1;
//			int next = p(matrix, index + 1, rest - team[0][1]);
//			if(next != -1) {
//				p2 = team[0][0] * team[0][1] + next;
//			}
//			return Math.max(p1, p2);
//		}else if(team.length == 2) { // a b    不要  a   ab
//			int[] a = team[0];
//			int[] b = team[1];
//			int p1 = p(matrix, index + 1, rest);
//			
//			int p2 = -1;// 只要a
//			int next2 = p(matrix, index + 1, rest - a[1]);
//			if(next2 != -1) {
//				p2 = a[0] * a[1] + next2;
//			}
//			
//			int p3 = -1;// 要 ab
//			int next3= p(matrix, index + 1, rest - a[1] - b[1]);
//			if(next3 != -1) {
//				p3 = a[0] * a[1] + b[0] * b[1] + next3;
//			}
//			return Math.max(p1, Math.max(p2, p3));
//		}else { // a : b c    不要   a   ab   ac   abc
//			
//		}
//		
//		
//		
//	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int money = sc.nextInt();
			int size = sc.nextInt();
			ArrayList<ArrayList<int[]>> things = new ArrayList<>();
			things.add(new ArrayList<>());
			for (int i = 0; i < size; i++) {
				ArrayList<int[]> cur = new ArrayList<>();
				cur.add(new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt() });
				things.add(cur);
			}
			int n = clean(things, size);
			int ans = maxScore(things, n, money);
			System.out.println(ans);
		}
		sc.close();
	}

	public static int clean(ArrayList<ArrayList<int[]>> things, int size) {
		for (int i = 1; i <= size; i++) {
			int[] cur = things.get(i).get(0);
			if (cur[2] != 0) {
				things.get(i).clear();
				things.get(cur[2]).add(cur);
			}
		}
		int n = 0;
		for (int i = 0; i <= size; i++) {
			if (!things.get(i).isEmpty()) {
				things.set(n++, things.get(i));
			}
		}
		return n;
	}

	public static int maxScore(ArrayList<ArrayList<int[]>> things, int n, int money) {
		int[][] dp = new int[n][money + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= money; j++) {
				dp[i][j] = -2;
			}
		}
		return process(things, n, 0, money, dp);
	}

	public static int process(ArrayList<ArrayList<int[]>> things, int n, int index, int rest, int[][] dp) {
		if (rest < 0) {
			return -1;
		}
		if (index == n) {
			return 0;
		}
		if (dp[index][rest] != -2) {
			return dp[index][rest];
		}
		ArrayList<int[]> project = things.get(index);
		int[] a = project.get(0);
		int[] b = project.size() > 1 ? project.get(1) : null;
		int[] c = project.size() > 2 ? project.get(2) : null;
		int p1 = process(things, n, index + 1, rest, dp);
		int p2 = process(things, n, index + 1, rest - a[0], dp);
		if (p2 != -1) {
			p2 += a[0] * a[1];
		}
		int p3 = b != null ? process(things, n, index + 1, rest - a[0] - b[0], dp) : -1;
		if (p3 != -1) {
			p3 += a[0] * a[1] + b[0] * b[1];
		}
		int p4 = c != null ? process(things, n, index + 1, rest - a[0] - c[0], dp) : -1;
		if (p4 != -1) {
			p4 += a[0] * a[1] + c[0] * c[1];
		}
		int p5 = c != null ? process(things, n, index + 1, rest - a[0] - b[0] - c[0], dp) : -1;
		if (p5 != -1) {
			p5 += a[0] * a[1] + b[0] * b[1] + c[0] * c[1];
		}
		int ans = Math.max(Math.max(Math.max(p1, p2), Math.max(p3, p4)), p5);
		dp[index][rest] = ans;
		return ans;
	}

}
