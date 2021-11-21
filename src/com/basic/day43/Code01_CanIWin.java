package com.basic.day43;

/**
 * @author ycb
 * @date 2021/11/21-11:24
 * @description https://leetcode.com/problems/can-i-win/
 */
public class Code01_CanIWin {

    // 1~choose是拥有的数字
    // total 一开始的剩余
    // 返回先手会不会赢
    public static boolean canIWin1(int choose, int total) {
        if (total == 0) {
            return true;
        }
        // 1~choose 等差数列
        // 1~choose的总和 < total
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        int[] arr = new int[choose];
        for (int i = 0; i < choose; i++) {
            arr[i] = i + 1;
        }
        // arr[i] != -1 表示arr[i]这个数字还没被拿走
        // arr[i] == -1 表示arr[i]这个数字已经被拿走
        return process1(arr, total);
    }

    // 当前轮到先手拿，
    // 先手只能选择在arr中还存在的数字，
    // 还剩rest这么值，
    // 返回先手会不会赢
    public static boolean process1(int[] arr, int rest) {
        if (rest <= 0) { // 先手先面对rest <= 0
            return false;
        }
        // 先手尝试所有的情况
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                int cur = arr[i];
                arr[i] = -1;
                // 先手当前选了cur这个数
                // 后续过程中，先手变成了后手
                // 后续过程的先手没有赢，那就是当前的先手赢了
                boolean next = process1(arr, rest - cur);
                arr[i] = cur;
                if (!next) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    ====================================================================================================================
     */

    public static boolean canIWin2(int choose, int total) {
        if (total == 0) {
            return true;
        }
        // 1~choose 等差数列
        // 1~choose的总和 < total
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        return process2(choose, 0, total);
    }

    // 当前轮到先手拿，
    // 先手可以拿1~choose中的任何一个数字
    // status   i位如果为0，代表没拿，当前可以拿
    //          i位为1，代表已经拿过了，当前不能拿
    // 还剩rest这么值，
    // 返回先手会不会赢
    public static boolean process2(int choose, int status, int rest) {
        if (rest <= 0) {
            return false;
        }
        for (int i = 1; i <= choose; i++) {
            if (((1 << i) & status) == 0) { // i 这个数字，是此时先手的决定
                if (!process2(choose, ((1 << i) | status), rest - i)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    ====================================================================================================================
     */

    public static boolean canIWin3(int choose, int total) {
        if (total == 0) {
            return true;
        }
        // 1~choose 等差数列
        // 1~choose的总和 < total
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        int[] dp = new int[1 << (choose + 1)];
        // dp[status] == 1  true
        // dp[status] == -1  false
        // dp[status] == 0  process(status) 没算过！去算！
        return process3(choose, 0, total, dp);
    }

    // status和rest是两个可变参数，其实只用status来代表状态(也就是dp)
    // 因为选了一批数字之后，得到的和一定是一样的，所以rest是由status决定的，所以rest不需要参与记忆化搜索
    public static boolean process3(int choose, int status, int rest, int[] dp) {
        if (dp[status] != 0) {
            return dp[status] == 1 ? true : false;
        }
        boolean ans = false;
        if (rest > 0) {
            for (int i = 1; i <= choose; i++) {
                if (((1 << i) & status) == 0) { // i是有效数字
                    if (!process3(choose, (1 << i) | status, rest - i, dp)) {
                        ans = true;
                        break;
                    }
                }
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
