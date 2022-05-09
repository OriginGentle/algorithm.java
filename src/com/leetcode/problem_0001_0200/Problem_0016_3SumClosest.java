package com.leetcode.problem_0001_0200;

import java.util.Arrays;

/**
 * @author ycb
 * @since 2021/11/12-12:24
 */
public class Problem_0016_3SumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        long ans = Integer.MAX_VALUE;
        int N = nums.length;
        // 枚举数组中每一个位置的数作为3个数中的一部分
        for (int i = 0; i < N; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = N - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                if (sum > target) {
                    int nr = r - 1;
                    while (l < nr && nums[r] == nums[nr]) {
                        --nr;
                    }
                    r = nr;
                } else {
                    int nl = l + 1;
                    while (nl < r && nums[l] == nums[nl]) {
                        ++nl;
                    }
                    l = nl;
                }
            }
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        int[] arr = {-3, -2, -5, 3, -4};
        int target = -1;
        System.out.println(threeSumClosest(arr, target));
    }

}
