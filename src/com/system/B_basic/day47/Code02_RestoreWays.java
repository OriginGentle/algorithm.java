package com.system.B_basic.day47;

/**
 * @author ycb
 * @date 2021/11/23-8:27
 * @description 整型数组arr长度为n(3 < = n < = 10 ^ 4)，最初每个数字是<=200的正数且满足如下条件：
 * 1. arr[0] <= arr[1]
 * 2. arr[n-1] <= arr[n-2]
 * 3. arr[i] <= max(arr[i-1], arr[i+1])
 * 但是在arr有些数字丢失了，比如k位置的数字之前是正数，
 * 丢失之后k位置的数字为0。
 * 请你根据上述条件， 计算可能有多少种不同的arr可以满足以上条件。
 * 比如 [6,0,9] 只有还原成 [6,9,9]满足全部三个条件，所以返回1种。
 */
public class Code02_RestoreWays {

    // 暴力解
    public static int ways1(int[] arr) {
        return func(arr, 0);
    }

    public static int func(int[] arr, int index) {
        if (index == arr.length) {
            return isValid(arr) ? 1 : 0;
        } else {
            if (arr[index] != 0) {
                return func(arr, index + 1);
            } else {
                int ways = 0;
                for (int v = 1; v < 201; v++) {
                    arr[index] = v;
                    ways += func(arr, index + 1);
                }
                arr[index] = 0;
                return ways;
            }
        }
    }

    public static boolean isValid(int[] arr) {
        if (arr[0] > arr[1]) {
            return false;
        }
        if (arr[arr.length - 1] > arr[arr.length - 2]) {
            return false;
        }
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > Math.max(arr[i - 1], arr[i + 1])) {
                return false;
            }
        }
        return true;
    }

    /*
    ====================================================================================================================
     */

    public static int ways2(int[] arr) {
        int N = arr.length;
        if (arr[N - 1] != 0) {
            return process1(arr, N - 1, arr[N - 1], 2);
        } else {
            int ways = 0;
            for (int v = 1; v < 201; v++) {
                ways += process1(arr, N - 1, v, 2);
            }
            return ways;
        }
    }

    // arr[0...i]范围上
    // 如果i位置的数字变成了v,
    // 并且arr[i]和arr[i+1]的关系为s，
    // s==0，代表arr[i] < arr[i+1] 右大
    // s==1，代表arr[i] == arr[i+1] 右=当前
    // s==2，代表arr[i] > arr[i+1] 右小
    // 返回0...i范围上有多少种有效的转化方式？
    public static int process1(int[] arr, int i, int v, int s) {
        if (i == 0) { // 0...0范围，只剩下一个数了
            // (s == 0 || s == 1):右边的数已经 >= [i]位置的数了
            // (arr[i] == 0 || v == arr[i]):当前数是缺失还是没有缺失
            return ((s == 0 || s == 1) && (arr[i] == 0 || v == arr[i])) ? 1 : 0;
        }
        // i > 0 , 不只有一个数
        if (arr[i] != 0 && v != arr[i]) {
            return 0;
        }
        // i>0 ，并且[i]位置的数真的可以变成V
        int ways = 0;
        if (s == 0 || s == 1) { // [i] -> V <= [i+1]
            for (int pre = 1; pre < 201; pre++) {
                ways += process1(arr, i - 1, pre, pre < v ? 0 : (pre == v ? 1 : 2));
            }
        } else { // 当前 > 右  需要满足:当前 <= max{左，右}
            for (int pre = v; pre < 201; pre++) {
                ways += process1(arr, i - 1, pre, pre == v ? 1 : 2);
            }
        }
        return ways;
    }

    // process1方法的简洁版本
    // 复杂度一样，就是代码上做一下简化
    public static int f(int[] arr, int i, int v, int s) {
        if (i == 0) { // 0...i 只剩一个数了，0...0
            return ((s == 0 || s == 1) && (arr[0] == 0 || v == arr[0])) ? 1 : 0;
        }
        // i > 0
        if (arr[i] != 0 && v != arr[i]) {
            return 0;
        }
        // i>0 ，并且， i位置的数真的可以变成V，
        int ways = 0;
        if (s == 0 || s == 1) { // [i] -> V <= [i+1]
            for (int pre = 1; pre < v; pre++) {
                ways += f(arr, i - 1, pre, 0);
            }
        }
        ways += f(arr, i - 1, v, 1);
        for (int pre = v + 1; pre < 201; pre++) {
            ways += f(arr, i - 1, pre, 2);
        }
        return ways;
    }

    /*
    ====================================================================================================================
     */

    public static int ways3(int[] arr) {
        int N = arr.length;
        int[][][] dp = new int[N][201][3];
        if (arr[0] != 0) {
            dp[0][arr[0]][0] = 1;
            dp[0][arr[0]][1] = 1;
        } else {
            for (int v = 1; v < 201; v++) {
                dp[0][v][0] = 1;
                dp[0][v][1] = 1;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int v = 1; v < 201; v++) {
                for (int s = 0; s < 3; s++) {
                    if (arr[i] == 0 || v == arr[i]) {
                        if (s == 0 || s == 1) {
                            for (int pre = 1; pre < v; pre++) {
                                dp[i][v][s] += dp[i - 1][pre][0];
                            }
                        }
                        dp[i][v][s] += dp[i - 1][v][1];
                        for (int pre = v + 1; pre < 201; pre++) {
                            dp[i][v][s] += dp[i - 1][pre][2];
                        }
                    }
                }
            }
        }
        if (arr[N - 1] != 0) {
            return dp[N - 1][arr[N - 1]][2];
        } else {
            int ways = 0;
            for (int v = 1; v < 201; v++) {
                ways += dp[N - 1][v][2];
            }
            return ways;
        }
    }

    /*
    ====================================================================================================================
     */

    public static int ways4(int[] arr) {
        int N = arr.length;
        int[][][] dp = new int[N][201][3];
        if (arr[0] != 0) {
            dp[0][arr[0]][0] = 1;
            dp[0][arr[0]][1] = 1;
        } else {
            for (int v = 1; v < 201; v++) {
                dp[0][v][0] = 1;
                dp[0][v][1] = 1;
            }
        }
        int[][] preSum = new int[201][3];
        for (int v = 1; v < 201; v++) {
            for (int s = 0; s < 3; s++) {
                preSum[v][s] = preSum[v - 1][s] + dp[0][v][s];
            }
        }
        for (int i = 1; i < N; i++) {
            for (int v = 1; v < 201; v++) {
                for (int s = 0; s < 3; s++) {
                    if (arr[i] == 0 || v == arr[i]) {
                        if (s == 0 || s == 1) {
                            dp[i][v][s] += sum(1, v - 1, 0, preSum);
                        }
                        dp[i][v][s] += dp[i - 1][v][1];
                        dp[i][v][s] += sum(v + 1, 200, 2, preSum);
                    }
                }
            }
            for (int v = 1; v < 201; v++) {
                for (int s = 0; s < 3; s++) {
                    preSum[v][s] = preSum[v - 1][s] + dp[i][v][s];
                }
            }
        }
        if (arr[N - 1] != 0) {
            return dp[N - 1][arr[N - 1]][2];
        } else {
            return sum(1, 200, 2, preSum);
        }
    }

    public static int sum(int begin, int end, int relation, int[][] presum) {
        return presum[end][relation] - presum[begin - 1][relation];
    }

    // for test
    public static int[] generateRandomArray(int len) {
        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            if (Math.random() < 0.5) {
                ans[i] = 0;
            } else {
                ans[i] = (int) (Math.random() * 200) + 1;
            }
        }
        return ans;
    }

    public static void printArray(int[] arr) {
        System.out.println("arr size : " + arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 4;
        int testTime = 15;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * len) + 2;
            int[] arr = generateRandomArray(N);
            int ans0 = ways1(arr);
            int ans1 = ways2(arr);
            int ans2 = ways3(arr);
            int ans3 = ways4(arr);
            if (ans0 != ans1 || ans2 != ans3 || ans0 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("功能测试结束");
        System.out.println("===========");
        int N = 100000;
        int[] arr = generateRandomArray(N);
        long begin = System.currentTimeMillis();
        ways4(arr);
        long end = System.currentTimeMillis();
        System.out.println("run time : " + (end - begin) + " ms");
    }
}
