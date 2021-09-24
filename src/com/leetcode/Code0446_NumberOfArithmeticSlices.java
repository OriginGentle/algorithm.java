package com.leetcode;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author ycb
 * @since 2021/8/11-19:43
 */
public class Code0446_NumberOfArithmeticSlices {

    public static int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int N = nums.length;
        // dp[i][j] 表示必须arr[i]结尾，公差为j的等差数列有多少个
        Map<Long, Integer>[] dp = new Map[N];
        return 0;
    }

    public static void process(int[] arr, int index, List<Integer> ans) {
        if (index == arr.length) {
            return;
        }
    }


    public static void main(String[] args) {
        String str = "";
        System.out.println(str.length());
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println(bytes.length);

        String s = String.valueOf(bytes);
        System.out.println(s.length());
    }

}
