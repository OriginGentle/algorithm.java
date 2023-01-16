package com.system.III_training.day03;

import java.util.Arrays;

/**
 * @Author ycb
 * @Date 2021/7/29-11:14
 * @Description 给定一个正数数组arr，代表若干人的体重
 * 再给定一个正数limit，表示所有船共同拥有的载重量
 * 每艘船最多坐两人，且不能超过载重
 * 想让所有的人同时过河，并且用最好的分配方法让船尽量少
 * 返回最少的船数
 */
public class Code05_MinBoat {

    public static int minBoat(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        Arrays.sort(arr);
        // 边界
        if (limit < arr[N - 1]) {
            return -1;
        }
        // 找到小于 limit/2 的边界
        int lessR = -1;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= (limit / 2)) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) {
            return N;
        }
        // 贪心
        int L = lessR;
        int R = lessR + 1;
        int noUsed = 0;
        while (L >= 0) {
            int solved = 0;
            // 来到L位置，看看能和右边多少个人进行配对
            while (R < N && arr[L] + arr[R] <= limit) {
                R++;
                solved++;
            }
            if (solved == 0) {
                noUsed++;
                L--;
            } else {
                L = Math.max(-1, L - solved);
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int moreUnsolved = (N - all) - used;
        // 最小总船数是 X/2 + Y/2 + Z
        // 左右两边的配对数 / 2 + 左边剩余的人数 / 2(向上取整) + 右边剩余的人数
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }

}
