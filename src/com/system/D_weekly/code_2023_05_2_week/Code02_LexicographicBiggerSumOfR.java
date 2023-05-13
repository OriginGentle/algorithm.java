package com.system.D_weekly.code_2023_05_2_week;

/**
 * @author ycb
 * @date 2023/5/13-16:21
 * @desc 塔子哥最近在处理一些字符串相关的任务
 * 他喜欢 R 字符，因为在某些任务中，这个字符通常表示“正确”的结果
 * 另一方面，他不喜欢 B 字符，因为在某些任务中，这个字符通常表示“错误”的结果
 * 为了解决他的任务，塔子哥定义了字符串的权值为字符串中 R 字符的出现次数
 * 例如，对于字符串 BBRBRB，它的权值为 2，因为其中有 2 个 R 字符
 * 现在，塔子哥面临一个问题，他有一个长度为 n 的字符串 s，它仅由 R 和 B 组成
 * 他想知道，长度为 n 的仅由 R 和 B组成的字符串中，
 * 字典序不小于 s 的字符串的权值之和是多少？
 * 因此，他需要编写一个程序来解决这个问题
 * 输入第一行为一个整数 n ，表示字符串的长度
 * 输入第二行为一个长度为 n 的字符串 s ，字符串中元素组成仅为 R 和 B
 * 输出一个整数，代表长度为 n 的、字典序不小于 s 的字符串权值之和
 * 输入样例：
 * 3
 * RBR
 * 输出：
 * 7
 * 解释：共有 3 个字符串字典序大于等于"RBR"，RBR权值为2，RRB为2，RRR为3
 * 1 <= n <= 100000
 * 结果可能很大，对1000000007取模
 */
public class Code02_LexicographicBiggerSumOfR {

    public static int sum1(String str) {
        return process1("", str);
    }

    private static int process1(String path, String s) {
        if (path.length() == s.length()) {
            if (path.compareTo(s) >= 0) {

                int ans = 0;
                for (int i = 0; i < path.length(); i++) {
                    if (path.charAt(i) == 'R') {
                        ans++;
                    }
                }
                return ans;
            }

            return 0;
        } else {
            return process1(path + "R", s) + process1(path + "B", s);
        }
    }

    /*
    ====================================================================================================================
     */

    public static final int MOD = 1000000007;
    public static final int MAXN = 100001;

    // pow2[i]:长度为 i ,可以组成多少个 RB串,结果 % MOD
    public static int[] pow2 = new int[MAXN];

    // f[i]:长度为 i 的RB串,一共有多少权值和,结果 %MOD
    public static int[] f = new int[MAXN];

    static {
        pow2[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        f[1] = 1;
        for (int i = 2; i < MAXN; i++) {
            f[i] = (pow2[i - 1] + f[i - 1]) % MOD;
            f[i] = (f[i] + f[i - 1]) % MOD;
        }
    }

    public static int sum2(String str) {
        int n = str.length();
        char[] s = str.toCharArray();
        int[] rNum = new int[n];
        rNum[0] = s[0] == 'R' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            rNum[i] = rNum[i - 1] + (s[i] == 'R' ? 1 : 0);
        }

        return process2(s, rNum, n, 0);
    }

    // 依次填写字符串
    // 0...i-1范围上，你填写的东西，和s完全一样
    // 当前来到i位置，一共的长度是n
    // rNum[i] : s[0...i]范围上有几个R
    // 返回 : 在[0...i-1]和s完全一样的情况下，后续所有字典序不小于s的字符串，整体的权值和是多少?
    private static int process2(char[] s, int[] rNum, int n, int i) {
        int ans;
        if (i == n) {
            ans = rNum[n - 1];
        } else {

            if (s[i] == 'B') {

                // 当前位置填的字符是 B
                int p1 = process2(s, rNum, n, i + 1);

                // 当前位置填的字符是 R
                // (rNum[i] + 1) : str[i] 当前位置是 B,填的却是 R
                // 所以此时共有 rNum[i] + 1 个 R
                int p2 = (int) (((long) (rNum[i] + 1) * pow2[n - i - 1]) % MOD);
                p2 = (p2 + f[n - i - 1]) % MOD;

                ans = (p1 + p2) % MOD;
            } else { // 当前位置是 R
                ans = process2(s, rNum, n, i + 1);
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int sum3(String str) {
        int n = str.length();
        char[] s = str.toCharArray();
        int[] rNum = new int[n];
        rNum[0] = s[0] == 'R' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            rNum[i] = rNum[i - 1] + (s[i] == 'R' ? 1 : 0);
        }

        int[] dp = new int[n + 1];
        dp[n] = rNum[n - 1];

        for (int i = n - 1; i >= 0; i--) {
            if (s[i] == 'B') { // 当前位置填的字符是 B
                int p1 = dp[i + 1];
                int p2 = (int) (((long) (rNum[i] + 1) * pow2[n - i - 1]) % MOD);
                p2 = (p2 + f[n - i - 1]) % MOD;
                dp[i] = (p1 + p2) % MOD;
            } else { // 当前位置是 R
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    // for test
    public static String randomString(int n) {
        char[] s = new char[n];
        for (int i = 0; i < n; i++) {
            s[i] = Math.random() < 0.5 ? 'B' : 'R';
        }
        return String.valueOf(s);
    }

    public static void main(String[] args) {
        int N = 15;
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            String s = randomString(n);
            int ans1 = sum1(s);
            int ans2 = sum2(s);
            int ans3 = sum3(s);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
