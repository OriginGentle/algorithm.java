package com.weekly.code_2022_07_4_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/7/30-14:55
 * @desc https://leetcode.cn/problems/the-number-of-good-subsets/
 */
public class Code03_TheNumberOfGoodSubsets {

    public int[] primes = {
            0, // 0 00000000
            0, // 1 00000000
            1, // 2 00000001
            2, // 3 00000010
            0, // 4 00000000
            4, // 5 00000100
            3, // 6 00000011
            8, // 7 00001000
            0, // 8 00000000
            0, // 9 00000000
            5, // 10 00000101
            16, 0, 32, 9, 6, 0, 64, 0, 128, 0, 10, 17, 256, 0, 0, 33, 0, 0,
            512,// 29  10000000
            7
    };

    // 每个在1～30之间
    public int[] counts = new int[31];

    // 1～30之间一共10个质数
    public int[] status = new int[1 << 10];

    public int MOD = 1000000007;

    public int numberOfGoodSubsets(int[] nums) {
        Arrays.fill(counts, 0);
        Arrays.fill(status, 0);
        // 统计每个数出现的次数
        for (int num : nums) {
            counts[num]++;
        }
        // 空集
        status[0] = 1;
        for (int i = 0; i < counts[1]; i++) {
            status[0] = (status[0] << 1) % MOD;
        }
        for (int i = 2; i <= 30; i++) {
            int curPrimeStatus = primes[i];
            if (curPrimeStatus != 0 && counts[i] != 0) {
                for (int from = 0; from < (1 << 10); from++) {
                    if ((from & curPrimeStatus) == 0) {
                        int to = from | curPrimeStatus;
                        status[to] = (int) (
                                ((long) status[to] + ((long) status[from] * counts[i])) % MOD
                        );
                    }
                }
            }
        }
        int ans = 0;
        for (int s = 1; s < (1 << 10); s++) {
            ans = (ans + status[s]) % MOD;
        }
        return ans;
    }
}
