package com.zaqbest.study.foundation.alg.zcy.s10_primary.class03;

public class Code02_SmallSum_Study {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2)
            return 0;

        return process(arr, 0, arr.length -1);
    }

    private static int process(int[] arr, int L, int R){
        if (L == R){
            return 0;
        }

        int mid = (L + R) / 2;
        return process(arr, L , mid)
                + process(arr, mid +1, R)
                + merge(arr, L, mid, R);
    }

    private static int merge(int[] arr, int L, int M, int R){
        int[] help = new int[R - L + 1]; //R-L+1
        int p1 = L;
        int p2 = M+1;
        int index = 0;
        int ans = 0;
        while (p1 <= M && p2 <= R){
            if (arr[p1] < arr[p2]){
                ans += arr[p1] * (R - p2 + 1);
                help[index++] = arr[p1++];
            } else {
                help[index++] = arr[p2++];
            }
        }

        while (p1 <= M){
            help[index++] = arr[p1++];
        }

        while (p2 <= R){
            help[index++] = arr[p2++];
        }

        for (int i =0; i < help.length;i++){
            arr[L+i] = help[i]; // L+i
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr= {1,3,4,2,5};
        int ans = smallSum(arr);
        System.out.println(ans);
    }
}
