package com.weekly.code_2022_05_2_week;

/**
 * @author ycb
 * @date 2022/5/13-19:52
 * @desc 来自网易
 * 小红拿到了一个长度为N的数组arr，她准备只进行一次修改
 * 可以将数组中任意一个数arr[i]，修改为不大于P的正数（修改后的数必须和原数不同)
 * 并使得所有数之和为X的倍数
 * 小红想知道，一共有多少种不同的修改方案
 * 1 <= N, X <= 10^5
 * 1 <= arr[i], P <= 10^9
 */
public class Code02_ModifyOneNumberModXWays {

    public static int ways1(int[] arr, int p, int x) {
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int ans = 0;
        for (int cur : arr) {
            sum -= cur;
            for (int v = 1; v <= p; v++) {
                if (v != cur) {
                    if ((sum + v) % x == 0) {
                        ans++;
                    }
                }
            }
            sum += cur;
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int ways2(int[] arr, int p, int x) {
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int ans = 0;
        for (int cur : arr) {
            ans += cnt(p, x, cur, (x - (int) (sum - cur) % x) % x);
        }
        return ans;
    }

    // 当前数是num
    // 可以在1 ~ p 上自由变换，但是不能是num自己
    // 要求 % x == mod
    public static int cnt(int p, int x, int num, int mod) {
        // 1.p / x 至少几个
        // 2.p不能整除x，即最后一组在数量不够的情况，有没有可能
        int ans = (p / x) + ((p % x) >= mod ? 1 : 0) - (mod == 0 ? 1 : 0);
        // 不能是num本身
        return ans - (((num <= p) && (num % x == mod)) ? 1 : 0);
    }

    // for test
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int value = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * len) + 1;
            int[] arr = randomArray(n, value);
            int p = (int) (Math.random() * value) + 1;
            int x = (int) (Math.random() * value) + 1;
            int ans1 = ways1(arr, p, x);
            int ans2 = ways2(arr, p, x);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
