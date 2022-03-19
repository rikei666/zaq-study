package com.zaqbest.study.foundation.alg.zcy.basic.study.class02;

import com.zaqbest.study.foundation.alg.common.Node;
import com.zaqbest.study.foundation.alg.utils.NodeUtil;

public class Code02_DeleteGivenValue {

    /**
     * 从链表删除指定值
     *
     * @param head
     * @param num
     * @return
     */
    public static Node removeValue(Node head, int num) {

        //找到第一个不是num的节点
        while (head != null){
            if (head.val == num){
                head = head.next;
            } else {
                break;
            }
        }

        Node pre = head, cur = head;

        while (cur != null){
            if (cur.val == num){
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {

        for(int i = 0; i < 1000; i++) {
            Node head1 = NodeUtil.generateRandomLinkedList(1000, 50);
            int target = 30;

            //NodeUtil.printList(listNode1);
            //System.out.println(StrUtil.format("是否包含target:{}", NodeUtil.contains(listNode1, target)));

            boolean b1 = NodeUtil.contains(head1, target);
            if (!b1) continue;

            head1 = removeValue(head1, target);
            boolean b2 = NodeUtil.contains(head1, target);
            if (b2){
                System.out.println("NG");
            }

            //NodeUtil.printList(listNode1);
            //System.out.println(StrUtil.format("是否包含target:{}", NodeUtil.contains(listNode1, target)));
        }

        System.out.println("over");
    }
}
