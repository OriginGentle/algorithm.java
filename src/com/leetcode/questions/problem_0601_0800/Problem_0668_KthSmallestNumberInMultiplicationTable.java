package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/5/18-19:53
 */
public class Problem_0668_KthSmallestNumberInMultiplicationTable {

    public static int findKthNumber1(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (count(m, n, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 统计乘法表中 <= k 数量
    public static int count(int m, int n, int target) {
        int res = 0;
        // 每一行中 >= target 有多少个
        for (int i = 1; i <= m; i++) {
            res += Math.min(target / i, n);
        }
        return res;
    }

    /*
    ====================================================================================================================
     */


    public static int _m, _n, _k;

    public static int findKthNumber(int m, int n, int k) {
        _n = Math.min(n, m);
        _m = Math.max(n, m);
        _k = k;
        int l = 1;
        int r = m * n;
        while (l < r) {
            int mid = (l + r) / 2;
            int count = cnt(mid);
            if (count >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static int cnt(int mid) {
        int ans = 0;
        for (int i = 1; i <= _n; i++) {
            ans += i * _m <= mid ? _m : mid / i;
        }
        return ans;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int k = 5;
        int ans = findKthNumber1(m, n, k);
        System.out.println(ans);
    }
}
