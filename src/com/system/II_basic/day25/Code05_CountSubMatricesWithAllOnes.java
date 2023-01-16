package com.system.II_basic.day25;

/**
 * @Author ycb
 * @Date 2021/5/31-15:39
 * @Description https://leetcode.com/problems/count-submatrices-with-all-ones
 */
public class Code05_CountSubMatricesWithAllOnes {

    // 假设a位置的高度是x
    // 左边比它小的位置在b,值为y
    // 右边比它小的位置在c,值为z
    // 则 (x - max(y,z)) * (L*(L+1) /2) 即高度算到 x - max(y,z)
    // 单调栈结构中的位置具备联通性 --> 解决位置值相等的情况
    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int nums = 0;
        int[] height = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            nums += countFromBottom(height);
        }
        return nums;
    }

    public static int countFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int nums = 0;
        int[] stack = new int[height.length];
        int si = -1;
        for (int i = 0; i < height.length; i++) {
            while (si != -1 && height[stack[si]] >= height[i]) {
                int cur = stack[si--];
                // 高度相等的时候不算，等到后面统一结算的时候再算
                if (height[cur] > height[i]) {
                    int left = si == -1 ? -1 : stack[si];
                    int n = i - left - 1;
                    int down = Math.max(left == -1 ? 0 : height[left], height[i]);
                    nums += (height[cur] - down) * num(n);
                }
            }
            stack[++si] = i;
        }
        while (si != -1) {
            int cur = stack[si--];
            int left = si == -1 ? -1 : stack[si];
            int n = height.length - left - 1;
            int down = left == -1 ? 0 : height[left];
            nums += (height[cur] - down) * num(n);
        }
        return nums;
    }

    public static int num(int n) {
        return ((n * (1 + n)) >> 1);
    }
}
