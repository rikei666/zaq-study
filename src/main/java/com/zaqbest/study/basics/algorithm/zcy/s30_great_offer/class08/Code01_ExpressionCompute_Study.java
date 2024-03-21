package com.zaqbest.study.basics.algorithm.zcy.s30_great_offer.class08;

import java.util.LinkedList;

public class Code01_ExpressionCompute_Study {
    public static int calc(String str) {
        return f(str.toCharArray(), 0)[0];
    }

    // int[0]计算值，int[1]最后来到的位置
    private static int[] f(char[] str, int i){
        LinkedList<String> que = new LinkedList();
        int cur = 0;

        while (i < str.length && str[i] != ')'){
            //数紫
            if (str[i] >= '0' && str[i] <= '9'){
                cur = cur * 10 + (str[i] - '0');
                i++;
            } else if (str[i] != '('){
                addNum(que, cur);
                que.addLast(String.valueOf(str[i]));
                cur = 0;
                i++;
            } else {
                int[] bra = f(str, i+1);
                cur = bra[0];
                i = bra[1] + 1;
            }
        }

        addNum(que, cur);
        return new int[]{getNum(que), i};
    }

    private static int getNum(LinkedList<String> que) {
        String cur;
        int res = 0;
        boolean add = true;
        while (!que.isEmpty()){
            cur = que.pollLast();
            if (cur.equals("+")){
                add = true;
            } else if (cur.equals("-")){
                add = false;
            } else {
                res += add ? Integer.valueOf(cur) : -Integer.valueOf(cur);
            }
        }

        return res;
    }

    private static void addNum(LinkedList<String> que, int num){
        if (!que.isEmpty()){
            String top = que.pollLast();

            int preValue = Integer.valueOf(que.pollLast());
            if (top.equals("+") || top.equals("-")){
                num = top.equals("+") ? preValue + num : preValue - num;
            } else {
                num = top.equals("*") ? preValue * num: preValue / num;
            }
        }

        que.addLast(String.valueOf(num));
    }
}
