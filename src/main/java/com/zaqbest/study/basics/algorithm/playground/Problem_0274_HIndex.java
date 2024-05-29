package com.zaqbest.study.basics.algorithm.playground;

public class Problem_0274_HIndex {
    public static int hIndex(int[] citations) {

        int low = 0, high = citations.length;
        int mid;
        int ans=0;
        while (low <= high){
            mid = (low + high) / 2;

            boolean r = check(citations, mid);
            if (r) {
                low = mid + 1;
                ans = mid;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private static boolean check(int[] citations, int h){
        int r = 0;
        for(int i = 0; i < citations.length; i++){
            if (citations[i] >= h){
                r++;
            }
        }

        return r >= h;
    }

    public static void main(String[] args) {

        System.out.println(hIndex(new int[]{3,0,6,1,5}));
        System.out.println(hIndex(new int[]{1,3,1}));
    }
}
