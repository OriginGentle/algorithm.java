package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/8/23-08:38
 */
public class Problem_0782_TransformToChessboard {

    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int rowMask = 0, colMask = 0;

        for (int i = 0; i < n; i++) {
            // 第一行
            rowMask |= (board[0][i] << i);
            // 第一列
            colMask |= (board[i][0] << i);
        }

        int reverseRowMask = ((1 << n) - 1) ^ rowMask;
        int reverseColMask = ((1 << n) - 1) ^ colMask;
        int rowCnt = 0, colCnt = 0;

        for (int i = 0; i < n; i++) {
            int curRowMask = 0, curColMask = 0;

            for (int j = 0; j < n; j++) {
                curRowMask |= (board[i][j] << j);
                curColMask |= (board[j][i] << j);
            }

            // 检查每一行的状态是否合法
            if (curRowMask != rowMask && curRowMask != reverseRowMask)
                return -1;
            else if (curRowMask == rowMask)
                rowCnt++;

            // 检查每一列的状态是否合法
            if (curColMask != colMask && curColMask != reverseColMask)
                return -1;
            else if (curColMask == colMask)
                colCnt++;
        }

        int rowMoves = getMoves(rowMask, rowCnt, n);
        int colMoves = getMoves(colMask, colCnt, n);

        return (rowMoves == -1 || colMoves == -1) ? -1 : rowMoves + colMoves;
    }

    public int getMoves(int mask, int count, int n) {
        int oneBits = Integer.bitCount(mask);

        if ((n & 1) == 1) {
            // 如果n为奇数，则每一行中 1 与 0 的数目相差为 1 ，且满足相邻行交替
            if (Math.abs(n - 2 * oneBits) != 1 ||
                    Math.abs(n - 2 * count) != 1)
                return -1;

            if (oneBits == (n >> 1))
                // 以 0 为开头的最小交换次数
                return n / 2 - Integer.bitCount(mask & 0xAAAAAAAA);
            else
                return (n + 1) / 2 - Integer.bitCount(mask & 0x55555555);

        } else {

            /* 如果 n 为偶数，则每一行中 1 与 0 的数目相等，且满足相邻行交替 */
            if (oneBits != (n >> 1) || count != (n >> 1)) {
                return -1;
            }
            /* 找到行的最小交换次数 */
            int count0 = n / 2 - Integer.bitCount(mask & 0xAAAAAAAA);
            int count1 = n / 2 - Integer.bitCount(mask & 0x55555555);
            return Math.min(count0, count1);
        }
    }
}
