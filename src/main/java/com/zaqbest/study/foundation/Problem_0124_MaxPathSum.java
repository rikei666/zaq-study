package com.zaqbest.study.foundation;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Info{
    public int sumAll; //整体的sum
    public int sumFromHead; //从头节点开始的最大值

    public Info (int s, int sh){
        sumAll = s;
        sumFromHead = sh;
    }
}

public class Problem_0124_MaxPathSum {
    public static int maxPathSum(TreeNode root) {
        Info info  = process(root);

        return info.sumAll;
    }

    public static Info process(TreeNode x){
        if (x == null){
            return null;
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null){
            p1 = leftInfo.sumAll;
        }

        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null){
            p2 = rightInfo.sumAll;
        }

        //只包含x节点
        int p3 = x.val;

        // x节点 + 左分支
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null){
            p4 = x.val + leftInfo.sumFromHead;
        }

        //x节点 + 右分支
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null){
            p5 = x.val + rightInfo.sumFromHead;
        }

        //x节点+左分支+右分支
        int p6 = Integer.MIN_VALUE;
        if (leftInfo != null
                && rightInfo != null){
            p6 = x.val + leftInfo.sumFromHead + rightInfo.sumFromHead;
        }

        int sumAll = Math.max(Math.max(p1,p2), Math.max(Math.max(p3, p4), Math.max(p5, p6)));
        int sumFromHead = Math.max(p3, Math.max(p4, p5));

        return new Info(sumAll, sumFromHead);
    }

    public static void main(String[] args) {
        TreeNode node2 = new TreeNode(-2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1, node2, node3);
//        TreeNode node1 = new TreeNode(-3);

        int res= maxPathSum(node1);
        System.out.println(res);
    }
}
