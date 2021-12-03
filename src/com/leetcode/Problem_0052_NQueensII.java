package com.leetcode;

/**
 * @author ycb
 * @since 2021/12/3-13:56
 */
public class Problem_0052_NQueensII {

    public static int totalNQueens1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    // 当前来到index行，一共是 0 ~ N-1 行
    // 在i行上放皇后，所有列都尝试
    // 必须保证跟之前所有的皇后不打架
    // 之前皇后的位置信息都保存在record中
    // record[x] = y 之前的第x行的皇后，放在了y列上
    // 返回:不关心i以上发生了什么,i...后续有多少合法的方法数
    public static int process(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        // 尝试每一列
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    /*
    ====================================================================================================================
     */

    public static int totalNQueens2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process1(limit, 0, 0, 0);
    }

    // 7皇后问题
    // limit : 0....0 1 1 1 1 1 1 1
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    public static int process1(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process1(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }
}
