package com.system.D_weekly.code_2023_05_2_week;

import java.util.Arrays;
import java.util.List;

/**
 * @author ycb
 * @date 2023/5/13-16:22
 * @desc 作为项目经理，你规划了一份需求的技能清单 req_skills，
 * 并打算从备选人员名单 people 中选出些人组成一个「必要团队」
 * （ 编号为 i 的备选人员 people[i] 含有一份该备选人员掌握的技能列表）。
 * 所谓「必要团队」，就是在这个团队中，
 * 对于所需求的技能列表 req_skills 中列出的每项技能，
 * 团队中至少有一名成员已经掌握。可以用每个人的编号来表示团队中的成员：
 * 例如，团队 team = [0, 1, 3] 表示掌握技能分别为
 * people[0]，people[1]，和 people[3] 的备选人员。
 * 请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。
 * 你可以按 任意顺序 返回答案，题目数据保证答案存在。
 * 测试链接 : https://leetcode.cn/problems/smallest-sufficient-team/
 */
public class Code03_SmallestSufficientTeam {

    // 状态压缩
    public static int[] smallestSufficientTeam(String[] skills, List<List<String>> people) {
        Arrays.sort(skills);
        int n = skills.length;
        int m = people.size();
        int[] statuses = new int[m];
        for (int i = 0; i < m; i++) {
            int skillStatus = 0;
            List<String> skillList = people.get(i);
            skillList.sort(String::compareTo);

            int p1 = 0, p2 = 0;
            while (p1 < n && p2 < skillList.size()) {
                int compare = skills[p1].compareTo(skillList.get(p2));
                if (compare < 0) {
                    p1++;
                } else if (compare > 0) {
                    p2++;
                } else {
                    skillStatus |= 1 << p1;
                    p1++;
                    p2++;
                }
            }
            statuses[i] = skillStatus;
        }

        int[][] dp = new int[m][1 << n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        int size = process(statuses, n, 0, 0, dp);

        int[] ans = new int[size];
        int ansi = 0;
        int i = 0, status = 0;

        while (status != (1 << n) - 1) {
            if (i + 1 == m || dp[i][status] != dp[i + 1][status]) {
                ans[ansi++] = i;
                status |= statuses[i];
            }
            i++;
        }
        return ans;
    }

    private static int process(int[] people, int n, int i, int status, int[][] dp) {
        if (status == (1 << n) - 1) {
            return 0;
        }

        if (i == people.length) {
            return Integer.MAX_VALUE;
        }

        if (dp[i][status] != -1) {
            return dp[i][status];
        }

        // 不要第 i 个人
        int p1 = process(people, n, i + 1, status, dp);

        int p2 = Integer.MAX_VALUE;
        int next = process(people, n, i + 1, status | people[i], dp);
        if (next != Integer.MAX_VALUE) {
            p2 = next + 1;
        }

        int ans = Math.min(p1, p2);
        dp[i][status] = ans;
        return ans;
    }
}
