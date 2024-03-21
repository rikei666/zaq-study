package com.zaqbest.study.basics.algorithm.playground;

public class Problem_0088_Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int d = m + n;
        while (m -1 >= 0 && n - 1 >= 0){
            if (nums1[m-1] > nums2[n-1]){
                nums1[--d] = nums1[--m];
            } else {
                nums1[--d] = nums2[--n];
            }
        }

        while (m-1 >= 0){
            nums1[--d] = nums1[--m];
        }

        while (n-1 >= 0){
            nums1[--d] = nums2[--n];
        }
    }
}
