package com.zaqbest.study.foundation.alg.zcy.basic.study.class02;

import com.zaqbest.study.foundation.alg.common.DNode;

public class Code03_DoubleEndsQueueToQueueAndStack {
    //模拟实现双向队列
    public static class DoubleEndsQueue<T>{
        private DNode<T> head;
        private DNode<T> tail;

        public void addFromHead(T value) {
            DNode<T> newNode = new DNode<>(value);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.last = newNode;
                head = newNode;
            }
        }

        public void addFromBottom(T value){

        }

        public void removeFromHead(){

        }

        public void removeFromBottom(){

        }
    }
}
