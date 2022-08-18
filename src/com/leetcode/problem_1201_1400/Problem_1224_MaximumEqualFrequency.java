package com.leetcode.problem_1201_1400;

/**
 * @author ycb
 * @date 2022/8/18-22:07
 */
public class Problem_1224_MaximumEqualFrequency {

    public int max = (int) 1e5 + 1;

    // cnt[x] 表示x这个数出现了多少次
    public int[] cnt;
    // sum[x] 表示出现次数为x的数字一共有多少个
    public int[] sum;

    public int maxEqualFreq(int[] nums) {
        cnt = new int[max];
        sum = new int[max];
        int n = nums.length;
        int max = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int t = nums[i], cur = ++cnt[t], len = i + 1;
            sum[cur]++;
            sum[cur - 1]--;
            max = Math.max(cur, max);
            // 所有数字出现的次数都是1，任意删除一个
            if (max == 1)
                ans = len;
            // 有一个数字出现了一次，其余都是max次
            if (max * sum[max] + 1 == len)
                ans = len;
            // 有一个数字出现了max次，其余都是max - 1次
            if ((max - 1) * (sum[max - 1] + 1) + 1 == len)
                ans = len;
        }
        return ans;
    }
}
