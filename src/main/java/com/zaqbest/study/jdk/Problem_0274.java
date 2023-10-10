package com.zaqbest.study.jdk;

public class Problem_0274 {

    public static int hIndex(int[] citations) {
        int size = citations.length;

        int l = 0;
        int h = size;

        int ans = 0;
        while (l <= h){
            int mid = (l+h)/2;
            boolean ok = process(citations, mid);
            if (ok){
                l = mid+1;
                ans = Math.max(ans, mid);
            } else {
                h = mid -1;
            }
        }

        return ans;
    }

    private static boolean process(int[] citations, int h){
        int s = 0;
        for (int i = 0; i < citations.length;i++){
            if (citations[i] >= h){
                s++;
            }
        }

        return s >= h;
    }

    public static void main(String[] args) {
        int[] citations = {1,3,1};
        int r = hIndex(citations);
        System.out.println(r);
    }
}
