package com.system.D_weekly.code_2023_03_1_week;

/**
 * @author ycb
 * @date 2023/3/2-13:36
 * @desc 给定一个1~N的排列，每次将相邻两数相加，可以得到新的序列，长度是N-1
 * 再对新的序列，每次将相邻两数相加，可以得到新的序列，长度是N-2
 * 这样下去可以最终只剩一个数字
 * 比如 :
 * 3 1 2 4
 * 4 3 6
 * 7 9
 * 16
 * 现在如果知道N，和最后的数字sum，反推最原始的序列是什么
 * 如果有多个答案，返回字典序最小的那个
 * 字典序看做所有数字拼起来的字符串字典序
 * 比如
 * 1, 10, 2... 拼起来是 1102...
 * 1, 2, 3, 4... 拼起来是 1234...
 * 认为 1, 10, 2...的字典序更小
 * 如果给定的n和sum，有答案，返回一个N长度的答案数组
 * 如果给定的n和sum，无答案，返回一个1长度的数组{ -1 }
 * 输入 : N = 4, sum = 16
 * 输出 : 3 1 2 4
 * 输入 : N = 10, sum = 4116
 * 输出 : 1 3 5 7 10 9 8 6 4 2
 * 0 < n <= 10, sum随意
 */
public class Code01_LexicographicalSmallestPermutation {

    public static int[][] modules = {
            {},
            {1},
            {1, 1},
            {1, 2, 1},
            {1, 3, 3, 1},
            {1, 4, 6, 4, 1},
            {1, 5, 10, 10, 5, 1},
            {1, 6, 15, 20, 15, 6, 1},
            {1, 7, 21, 35, 35, 21, 7, 1},
            {1, 8, 28, 56, 70, 56, 28, 8, 1},
            {1, 9, 36, 84, 126, 126, 84, 36, 9, 1}
    };

    // sum[i] =  只有1~i这些数字，整出来的最大和不会超过sums[i]
    public static int[] sums = {0, 1, 3, 9, 24, 61, 148, 350, 808, 1837, 4116};


    public static int[] lsp(int n, int sum) {
        if (n < 1 || n > 10 || sums[n] < sum) {
            return new int[]{-1};
        }

        int[][] dp = new int[1 << (n + 1)][sums[n] + 1];
        if (!process(((1 << (n + 1)) - 1) ^ 1, sum, 0, n, modules[n], dp)) {
            return new int[]{-1};
        }

        int[] ans = new int[n];
        int idx = 0, status = ((1 << (n + 1)) - 1) ^ 1, rest = sum;
        while (status != 0) {
            ans[idx] = dp[status][rest];
            status ^= 1 << ans[idx];
            rest -= ans[idx] * modules[n][idx];
            idx++;
        }
        return ans;
    }


    public static boolean process(int status, int rest, int index, int n, int[] module, int[][] dp) {
        if (rest < 0) {
            return false;
        }
        if (status == 0) {
            return rest == 0;
        }
        // dp[status][rest] == 0  之前没算过！
        // dp[status][rest] == -1 之前算过！返回了false！
        // dp[status][rest] != -1 之前算过！返回了true！
        if (dp[status][rest] != 0) {
            return dp[status][rest] != -1;
        }

        int ans = -1;
        if (n == 10 && (status & (1 << 10)) != 0) {
            if (process(status ^ (1 << 10), rest - module[index] * 10,
                    index + 1, n, module, dp)) {
                ans = 10;
            }
        }

        if (ans == -1) {
            for (int i = 1; i <= n; i++) {
                if ((status & (1 << i)) != 0) {
                    if (process(status ^ (1 << i), rest - module[index] * i,
                            index + 1, n, module, dp)) {
                        ans = i;
                        break;
                    }
                }
            }
        }
        dp[status][rest] = ans;
        return ans != -1;
    }

    // for test
    public static void main(String[] args) {
        int N1 = 4;
        int sum1 = 16;
        int[] ans1 = lsp(N1, sum1);
        for (int num : ans1) {
            System.out.print(num + " ");
        }
        System.out.println();

        int N2 = 10;
        int sum2 = 4116;
        int[] ans2 = lsp(N2, sum2);
        for (int num : ans2) {
            System.out.print(num + " ");
        }
        System.out.println();

        int N3 = 10;
        int sum3 = 3688;
        int[] ans3 = lsp(N3, sum3);
        for (int num : ans3) {
            System.out.print(num + " ");
        }
        System.out.println();

        int N4 = 10;
        int sum4 = 4013;
        int[] ans4 = lsp(N4, sum4);
        for (int num : ans4) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
