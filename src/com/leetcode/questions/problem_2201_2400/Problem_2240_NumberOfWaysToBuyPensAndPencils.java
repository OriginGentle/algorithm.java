package com.leetcode.questions.problem_2201_2400;

/**
 * @author ycb
 * @date 2023/9/1-20:58
 */
public class Problem_2240_NumberOfWaysToBuyPensAndPencils {

    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        if (cost1 < cost2) {
            return waysToBuyPensPencils(total, cost2, cost1);
        }
        long ans = 0, cnt = 0;
        while (cnt * cost1 <= total) {
            ans += (total - cnt * cost1) / cost2 + 1;
            cnt++;
        }
        return ans;
    }
}
