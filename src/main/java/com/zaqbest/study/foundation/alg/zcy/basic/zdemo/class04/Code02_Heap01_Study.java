package com.zaqbest.study.foundation.alg.zcy.basic.zdemo.class04;

public class Code02_Heap01_Study {
    class MyMaxHeap{
        int[] heap;
        int heapSize;
        int limit;

        public MyMaxHeap(int l){
            heap = new int[l];
            heapSize = 0;
            limit = l;
        }

        public void add(int value){
            if (heapSize == limit){
                throw new RuntimeException("满了");
            }

            heap[heapSize] = value;
            heapInsert(heapSize++);
        }

        private void heapInsert(int index){
            while (heap[index] > heap[(index-1)/2]){

            }
        }
    }
}
