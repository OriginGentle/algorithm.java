package com.system.III_training.day30;

/**
 * @author ycb
 * @since 2021/10/16-21:45
 */
public class Problem_0108_ConvertSortedArrayToBinarySearchTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    public static TreeNode process(int[] arr, int L, int R) {
        if (L > R) {
            return null;
        }
        if (L == R) {
            return new TreeNode(arr[L]);
        }
        int M = L + ((R - L) >> 1);
        TreeNode head = new TreeNode(arr[M]);
        head.left = process(arr, L, M - 1);
        head.right = process(arr, M + 1, R);
        return head;
    }
}
