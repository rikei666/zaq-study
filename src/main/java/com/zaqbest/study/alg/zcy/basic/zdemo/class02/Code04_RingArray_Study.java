package com.zaqbest.study.alg.zcy.basic.zdemo.class02;

public class Code04_RingArray_Study {
    class MyQueue{
        int[] arr;
        int pushi;
        int popi;
        int size;
        int limit;

        public MyQueue(int l){
            arr = new int[l];
            pushi = 0;
            popi = 0;
            size = 0;
            limit = l;
        }

        public void add(int value){
            if (size == limit){
                throw new RuntimeException("满了");
            }

            arr[pushi] = value;
            pushi = getNextIndex(pushi);
            size++;
        }

        public int pop(){
            if (size == 0){
                throw new RuntimeException("空了");
            }

            int ans = arr[popi];
            popi = getNextIndex(popi);
            size--;

            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private int getNextIndex(int cur){
            if (cur == limit - 1)
                return 0;
            else
                return cur + 1;
        }
    }
}
