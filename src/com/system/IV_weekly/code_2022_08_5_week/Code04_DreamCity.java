package com.system.IV_weekly.code_2022_08_5_week;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ycb
 * @date 2022/9/2-16:51
 * @desc 给定n棵树，和两个长度为n的数组a和b
 * i号棵树的初始重量为a[i]，i号树每天的增长重量为b[i]
 * 你每天最多能砍1棵树，这天收益 = 砍的树初始重量 + 砍的树增长到这天的总增重
 * 给定m，表示你有m天，返回m天内你获得的最大收益
 * <p>
 * 测试链接 : https://zoj.pintia.cn/problem-sets/91827364500/problems/91827367873
 * 技巧：输入输出的处理方法
 * <p>
 * 增加题目：
 * 小红定义"漂亮串"为：至少有两个"red"子串
 * 例如"redxred"为漂亮串，但"reedred"则不是漂亮串
 * 小红想知道长度为n，仅包含小写字母的所有字符串中，共有多少个不同的漂亮串？
 * 输入描述：
 * 一个正整数n，代表字符串长度
 * n <= 10^6
 * 输出描述：
 * 长度为n，仅包含小写字母的所有字符串中，共有多少个不同的漂亮串，结果对10^9 + 7取模
 */
public class Code04_DreamCity {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        int testCases = (int) in.nval;
        for (int i = 0; i < testCases; i++) {
            in.nextToken();
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            for (int j = 0; j < n; j++) {
                in.nextToken();
                tree[j][0] = (int) in.nval;
            }
            for (int j = 0; j < n; j++) {
                in.nextToken();
                tree[j][1] = (int) in.nval;
            }
            out.println(maxWeight(n, m));
            out.flush();
        }
    }

    public static int[][] tree = new int[250][2];

    // dp[i][j] 在0...i自由选择，种j棵树，获取的最大收益是多少
    public static int[][] dp = new int[250][250];

    public static int maxWeight(int n, int m) {
        Arrays.sort(tree, 0, n, (Comparator.comparingInt(o -> o[1])));
        dp[0][0] = tree[0][0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], tree[i][0]);
        }

        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + tree[0][1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j],
                        dp[i - 1][j - 1] + tree[i][0] + tree[i][1] * j);
            }
        }
        return dp[n - 1][m - 1];
    }
}
