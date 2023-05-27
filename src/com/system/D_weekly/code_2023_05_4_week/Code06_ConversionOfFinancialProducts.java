package com.system.D_weekly.code_2023_05_4_week;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/5/28-00:01
 * @desc 来自招商银行
 * 假如某公司目前推出了N个在售的金融产品(1<=N<=100)
 * 对于张三，用ai表示他购买了ai(0<=ai<=10^4)份额的第i个产品(1<=i<=N)
 * 现给出K(1<=K<=N)个方案，通过这些方案，能够支持将多个不同的产品进行整合
 * (也可以对单个产品进行优化)形成新的产品。
 * 新的产品形成后，若用户持有了组成新产品所需的全部的原产品份额，
 * 则能够将用户持有的原产品份额转换为新产品的份额，各原产品份额与新产品份额比例均为1:1
 * 我们保证对于每个产品最多存在一个方案使用旧产品整合成该产品
 * 并且根据方案产出的新产品的产品编号均大于各旧产品的产品编号
 * 现计划根据这些方案，帮助部分愿意升级到最新产品的用户对产品进行升级
 * 请协助工作人员计算当前用户能够转换出的最新产品份额的最大值
 * 输入描述
 * 第一行包含整数N，第二行包含N个整数ai，第三行包含整数K
 * 接下来的K行，每一行代表一个方案，每一行包含整数1和M(M>=1)
 * L为该方案产生的新产品的编号，M代表方案所需原产品个数
 * 接下来的M个整数代表了该方案所需的每个原产品的个数
 * 输出描述
 * 根据日前的份额和给出的方案，经过若干次转换，输出当前用户能够得到产品N的份额最大值
 * 举例
 * 输入:
 * 5
 * 2 0 0 1 0
 * 3
 * 5 2 3 4
 * 2 1 1
 * 3 1 2
 * 输出:
 * 1
 * 解释:
 * 第一步将1份1产品转化为1份2产品
 * 第二步将1份2产品转化为1份3产品
 * 第三步将1份3产品和1份4产品，转成为1份5产品
 * 然后不能得到更多的5产品了，所以返回1
 * <p>
 * 如下是改写后的题目描述
 * 给定一个数组arr，长度为n，产品编号从0~n-1
 * arr[i]代表初始时i号产品有多少份
 * 存在一系列的产品转化方案的数组convert，长度为k，代表k个方案
 * 比如具体某一个方案，convert[j] = {a, b, c, d, ...}
 * 表示当前的j号方案转化出来的产品是a，转化1份a需要：1份b、1份c、1份d...
 * 其中a、b、c、d...一定都在0~n-1范围内
 * 并且题目保证a > Math.max(b, c, d, ....)
 * 而且题目保证所有方案转化出来的产品编号一定是不重复的
 * 请返回最终能得到的第n-1号商品的最大值
 * 1 <= n <= 100
 * 0 <= arr[i] <= 10^4
 * k < n
 */
public class Code06_ConversionOfFinancialProducts {

    public static int MAXN = 101;

    public static int[] inDegree = new int[MAXN];

    public static int[] zeroQueue = new int[MAXN];

    public static int[] help = new int[MAXN];

    public static int[] need = new int[MAXN];

    public static int n;

    public static int maxValue(int[] arr, int[][] convert) {
        n = arr.length;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        Arrays.fill(inDegree, 0, n, 0);
        for (int[] relation : convert) {
            for (int i = 1; i < relation.length; i++) {
                graph.get(relation[0]).add(relation[i]);
                inDegree[relation[i]]++;
            }
        }

        int l = arr[n - 1] + 1;
        int r = 0;
        for (int num : arr) {
            r += num;
        }

        int ans = arr[n - 1];
        while (l <= r) {
            int m = (l + r) / 2;
            if (ok(arr, graph, m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return ans;
    }

    // arr[i]:代表每份产品的初始份额
    // graph就是图，3 -> 0 1 2
    // aim目标，一定要转化出这么多份 n-1 号商品
    private static boolean ok(int[] arr, ArrayList<ArrayList<Integer>> graph, int aim) {
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            help[i] = inDegree[i];
            if (help[i] == 0) {
                zeroQueue[r++] = i;
            }
        }

        Arrays.fill(need, 0, n, 0);
        need[n - 1] = aim;

        while (l < r) {

            int cur = zeroQueue[l++];
            // 需要下级供应的量
            int supplement = Math.max(need[cur] - arr[cur], 0);

            if (graph.get(cur).isEmpty() && supplement > 0) {
                return false;
            }

            for (Integer next : graph.get(cur)) {
                need[next] += supplement;
                if (--help[next] == 0) {
                    zeroQueue[r++] = next;
                }
            }

        }
        return true;
    }

    // for test
    public static void main(String[] args) {
        int[] arr1 = {2, 0, 0, 1, 0};
        int[][] convert1 = {{4, 2, 3}, {1, 0}, {2, 1}};
        System.out.println(maxValue(arr1, convert1));

        int[] arr2 = {100, 5, 5, 0};
        int[][] convert2 = {{1, 0}, {2, 0, 1}, {3, 0, 1, 2}};
        System.out.println(maxValue(arr2, convert2));
    }

}
