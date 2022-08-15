package com.leetcode.problem_biweekly;

public class Problem_4 {

    public long minimumReplacement(int[] nums) {
        long ans = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                int cnt = nums[i] / nums[i + 1] + (nums[i] % nums[i + 1] == 0 ? 0 : 1);
                ans += cnt - 1;
                nums[i] /= cnt;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    // n >= 1
    public static int countSpecialNumbers(int n) {
        // n 一共由几位数字组成
        // n : 697645617
        // len : 9位
        int lenOfNum = getLenOfNum(n);
        if (lenOfNum == 1) {
            return n;
        }
        int ans = 0;
        // 从 len - 1的长度字符串开始算起
        // 可以得到多少个
        for (int i = lenOfNum - 1; i > 0; i--) {
            int count = 1;
            int rest = 9;
            int tmp = i;
            while (--tmp > 0) {
                count *= rest--;
            }
            // 最高位不能是0，所以最后要考虑最高位的情况
            ans += count * 9;
        }
        // 剩下的len位范围的数字需要统计
        // 100000000 - 697645617
        int count = process(n, 0, lenOfNum, "", new boolean[10]);
        return ans + count;
    }

    // from - to 范围内的数字
    public static int process(int to, int idx, int len, String pre, boolean[] visited) {
        if (idx == len) {
            int ans = Integer.parseInt(pre);
            return (getLenOfNum(ans) == len && ans <= to) ? 1 : 0;
        }
        int ans = 0;
        for (int i = 0; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            ans += process(to, idx + 1, len, pre + i, visited);
            visited[i] = false;
        }
        return ans;
    }

    public static int getLenOfNum(long n) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= 10;
        }
        return len;
    }

    public static void main(String[] args) {
        int n = 135;
        int ans = countSpecialNumbers(n);
        System.out.println(ans);
    }
}
