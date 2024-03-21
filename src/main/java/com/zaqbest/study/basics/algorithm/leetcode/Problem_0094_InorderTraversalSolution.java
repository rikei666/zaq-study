package com.zaqbest.study.basics.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 94:二叉树的中序遍历
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 */
public class Problem_0094_InorderTraversalSolution {
    private static class TreeNode {
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
    public static void main(String[] args) {
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    private void inorderTraversal(TreeNode root, List<Integer> result){
        if (root == null)
            return;
        if (root.left != null){
            inorderTraversal(root.left, result);
        }
        result.add(root.val);
        if (root.right != null){
            inorderTraversal(root.right, result);
        }
    }
}
