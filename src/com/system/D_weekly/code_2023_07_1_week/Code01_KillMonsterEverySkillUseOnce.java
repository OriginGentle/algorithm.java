package com.system.D_weekly.code_2023_07_1_week;

import java.io.*;

/**
 * @author ycb
 * @date 2023/7/10-10:23
 * @desc 国外笔试题目
 * 已知一些供应点的位置，一共n个供应点
 * 其中有n-1个供应点一定都在x轴上，比如(15,0)位置，(2,0)位置等
 * 只有1个供应点不在x轴上，比如(23,17)位置
 * 给出每个供应点的位置，并且给定第k号供应点是出发点
 * 要求每个供应点最多走过2次，返回从k点出发，走完所有供应点的最少距离
 * <p>
 * 现在有一个打怪类型的游戏，这个游戏是这样的，你有n个技能
 * 每一个技能会有一个伤害，
 * 同时若怪物小于等于一定的血量，则该技能可能造成双倍伤害
 * 每一个技能最多只能释放一次，已知怪物有m点血量
 * 现在想问你最少用几个技能能消灭掉他(血量小于等于0)
 * 技能的数量是n，怪物的血量是m
 * i号技能的伤害是x[i]，i号技能触发双倍伤害的血量最小值是y[i]
 * 1 <= n <= 10
 * 1 <= m、x[i]、y[i] <= 10^6
 * 测试链接 : https://www.nowcoder.com/questionTerminal/d88ef50f8dab4850be8cd4b95514bbbd
 */
public class Code01_KillMonsterEverySkillUseOnce {

    public static int MAXN = 11;

    public static int[] skill = new int[MAXN];

    public static int[] blood = new int[MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int t = (int) in.nval;
            for (int i = 0; i < t; i++) {
                in.nextToken();
                int n = (int) in.nval;
                in.nextToken();
                int m = (int) in.nval;
                for (int j = 0; j < n; j++) {
                    in.nextToken();
                    skill[j] = (int) in.nval;
                    in.nextToken();
                    blood[j] = (int) in.nval;
                }
                int ans = process(n, 0, m);
                out.println(ans == Integer.MAX_VALUE ? -1 : ans);
                out.flush();
            }
        }
    }

    public static int process(int n, int i, int rest) {
        if (rest <= 0) {
            return i;
        }

        if (i == n) {
            return Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            swap(i, j);
            if (rest > blood[i]) {
                ans = Math.min(ans, process(n, i + 1, rest - skill[i]));
            } else {
                ans = Math.min(ans, process(n, i + 1, rest - skill[i] * 2));
            }
            swap(i, j);
        }
        return ans;
    }

    public static void swap(int i, int j) {
        int a = skill[i];
        int b = blood[i];
        skill[i] = skill[j];
        blood[i] = blood[j];
        skill[j] = a;
        blood[j] = b;
    }
}
