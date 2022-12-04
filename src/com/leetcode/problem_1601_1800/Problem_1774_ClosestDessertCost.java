package com.leetcode.problem_1601_1800;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/12/4-16:30
 */
public class Problem_1774_ClosestDessertCost {

    public static int[] collect = new int[14348907];
    public static int size = 0;

    public static int closestCost(int[] base, int[] topping, int target) {
        size = 0;
        process2(topping, 0, 0);
        Arrays.sort(collect, 0, size);
        int ans = Integer.MAX_VALUE;
        for (int num : base) {
            int cur = num;
            if (cur < target) {
                int rest = target - cur;
                int floor = floor(rest);
                int ceiling = ceiling(rest);
                if (floor == -1 || ceiling == -1) {
                    cur += floor == -1 ? ceiling : floor;
                } else {
                    cur += rest - floor <= ceiling - rest ? floor : ceiling;
                }
            }

            if (Math.abs(cur - target) < Math.abs(ans - target)
                    || (Math.abs(cur - target) == Math.abs(ans - target) && cur < ans)) {
                ans = cur;
            }
        }
        return ans;
    }

    public static void process2(int[] topping, int index, int sum) {
        if (index == topping.length) {
            collect[size++] = sum;
        } else {
            process2(topping, index + 1, sum);
            process2(topping, index + 1, sum + topping[index]);
            process2(topping, index + 1, sum + (topping[index] << 1));
        }
    }

    public static int floor(int num) {
        int l = 0, r = size - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (collect[m] <= num) {
                ans = collect[m];
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static int ceiling(int num) {
        int l = 0, r = size - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (collect[m] >= num) {
                ans = collect[m];
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
