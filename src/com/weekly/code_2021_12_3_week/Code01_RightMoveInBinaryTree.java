package com.weekly.code_2021_12_3_week;

/**
 * @author ycb
 * @date 2021/12/18-14:04
 * @description https://www.nowcoder.com/test/33701596/summary
 */
public class Code01_RightMoveInBinaryTree {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 数组右移的技巧：左逆 右逆 整体逆

    // N <= 10 ^ 6
    public static TreeNode[] queue = new TreeNode[300000];

    // 记录每一层的结束位置
    // 左闭右开的形式,
    public static int[] ends = new int[50];

    public static TreeNode cyclicShiftTree1(TreeNode root, int k) {
        int l = 0;
        int r = 0;
        queue[r++] = root;
        int level = 0;
        // 按层遍历
        // 把每一层的结束位置在end中生成好
        while (l != r) {
            ends[level] = r;
            while (l < ends[level]) {
                TreeNode cur = queue[l++];
                if (cur != null) {
                    queue[r++] = cur.left;
                    queue[r++] = cur.right;
                }
            }
            level++;
        }
        for (int i = level - 1; i > 0; i--) {
            // 当前层 : curLeft....curRight
            //            3(null) 4(a) 5(null) 6(b)
            // 下一层 ：downLeft....downRight
            //              7 8 9 10
            // downIndex : 下一层需要根据，k和下一层的长度，来右移。右移之后，从哪个位置开始，分配节点给当前层第一个不空的节点
            // 下一层的左边界
            int downLeft = ends[i - 1];
            // 下一层的右边界
            int downRight = ends[i] - 1;
            rightMove(queue, downLeft, downRight, k);
            int curLeft = i - 2 >= 0 ? ends[i - 2] : 0;
            int curRight = ends[i - 1] - 1;
            for (int j = curLeft; j <= curRight; j++) {
                if (queue[j] != null) {
                    queue[j].left = queue[downLeft++];
                    queue[j].right = queue[downLeft++];
                }
            }
        }
        return root;
    }

    // 把当前的层节点右移k位
    public static void rightMove(TreeNode[] arr, int l, int r, int k) {
        int e = r + 1 - (k % (r - l + 1));
        if (e <= r) {
            // 左边逆序
            for (int i = l, j = e - 1; i < j; i++, j--) {
                swap(arr, i, j);
            }
            // 右边逆序
            for (int i = e, j = r; i < j; i++, j--) {
                swap(arr, i, j);
            }
            // 整体逆序
            for (int i = l, j = r; i < j; i++, j--) {
                swap(arr, i, j);
            }
        }
    }

    public static void swap(TreeNode[] arr, int i, int j) {
        TreeNode tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /*
    ====================================================================================================================
     */

    public static TreeNode cyclicShiftTree2(TreeNode root, int k) {
        int l = 0;
        int r = 0;
        queue[r++] = root;
        int level = 0;
        while (l != r) {
            ends[level] = r;
            while (l < ends[level]) {
                TreeNode cur = queue[l++];
                if (cur != null) {
                    queue[r++] = cur.left;
                    queue[r++] = cur.right;
                }
            }
            level++;
        }
        for (int i = level - 1; i > 0; i--) {

            // 当前层 : curLeft....curRight
            //            3(null) 4(a) 5(null) 6(b)
            // 下一层 ：downLeft....downRight
            //              7 8 9 10
            // downIndex : 下一层需要根据，k和下一层的长度，来右移。右移之后，从哪个位置开始，分配节点给当前层第一个不空的节点
            int downLeft = ends[i - 1];
            int downRight = ends[i] - 1;
            int downRightSize = k % (downRight - downLeft + 1);
            int downIndex = downRightSize == 0 ? downLeft : (downRight - downRightSize + 1);
            int curLeft = i - 2 >= 0 ? ends[i - 2] : 0;
            int curRight = ends[i - 1] - 1;
            for (int j = curLeft; j <= curRight; j++) {
                if (queue[j] != null) {
                    queue[j].left = queue[downIndex];
                    downIndex = nextIndex(downIndex, downLeft, downRight);
                    queue[j].right = queue[downIndex];
                    downIndex = nextIndex(downIndex, downLeft, downRight);
                }
            }
        }
        return root;
    }

    // l......r    i -> next index
    // 4.....9   i = 7 8 9 4
    public static int nextIndex(int i, int l, int r) {
        return i == r ? l : i + 1;
    }
}
