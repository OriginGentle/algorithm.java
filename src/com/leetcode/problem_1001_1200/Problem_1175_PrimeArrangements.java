package com.leetcode.problem_1001_1200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/6/30-11:30
 */
public class Problem_1175_PrimeArrangements {

    static int MOD = (int) 1e9 + 7;
    static int[] cnt = new int[110];

    static {
        List<Integer> record = new ArrayList<>();
        for (int i = 2; i <= 100; i++) {
            boolean flag = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                record.add(i);
            cnt[i] = record.size();
        }
    }

    public int numPrimeArrangements(int n) {
        int a = cnt[n];
        int b = n - a;
        long ans = 1L;
        for (int i = b; i > 1; i--)
            ans = ans * i % MOD;
        for (int i = a; i > 1; i--)
            ans = ans * i % MOD;
        return (int) ans;
    }
}
