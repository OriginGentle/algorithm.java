package com.algorithm.day18;

/**
 * @Author ycb
 * @Date 2021/5/11-0:15
 * @Description 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class RobotWalk {

    // 尝试过程
    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > K || K < 1) {
            return -1;
        }
        return process1(start, K, aim, N);
    }

    // 机器人当前来到的位置是cur
    // 机器人还有rest步需要去走
    // 最终的目标是aim
    // 有哪些位置？1~N
    // 返回:机器人从cur出发，走过rest步，最终停在aim的方法数是多少？
    public static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) { // 不需要走了
            return cur == aim ? 1 : 0;
        }
        // rest > 0
        if (cur == 1) { // 1 --> 2
            return process1(2, rest - 1, aim, N);
        }

        if (cur == N) { // N --> N-1
            return process1(N - 1, rest - 1, aim, N);
        }
        // cur处在中间位置 -->  往左走 or 往右走
        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
    }

    /*
    ====================================================================================================================
     */

    // 优化:aim不变 N不变
    // 重复过程 --> 缓存
    // 从顶向下的动态规划  --> 记忆化搜索
    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > K || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        // dp就是缓存表
        // dp[cur][rest] == -1 --> process2(cur,rest)之前没算过
        // dp[cur][rest] != -1 --> process2(cur,rest)之前算过,返回值dp[cur][rest]
        return process2(start, K, aim, N, dp);
    }

    // cur  范围:1 ~ N
    // rest 范围:0 ~ K
    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        // 之前没算过
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process2(N - 1, rest - 1, aim, N, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /*
    ====================================================================================================================
     */

    // 分析依赖 --> 状态转移
    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > K || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) { // 列
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }


    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}
