package com.zaqbest.study.basics.algorithm.playground;

import java.util.HashMap;
import java.util.Random;

public class Problem_0180_RandomSet {

    public static class RandomizedSet{
        //val => index
        HashMap<Integer, Integer> map;
        int[] nums;
        int idx;
        Random random;

        public RandomizedSet() {
            map = new HashMap<>();
            nums = new int[2*100000];
            idx = 0;
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)){
                return false;
            }
            map.put(val, idx);
            nums[idx++] = val;

            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)){
                return false;
            }
            int loc = map.remove(val);
            nums[loc] = nums[idx-1];
            map.put(nums[idx-1], loc);
            idx--;

            return true;
        }

        public int getRandom() {
            return nums[random.nextInt(idx)];
        }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.remove(2);
        randomizedSet.insert(2);
        int r = randomizedSet.getRandom();
        System.out.println(r);
        randomizedSet.remove(1);
        randomizedSet.insert(2);
        r= randomizedSet.getRandom();
        System.out.println(r);
    }

}

