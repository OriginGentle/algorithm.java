package com.leetcode.questions.problem_0201_0400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/9/6-14:51
 */
public class Problem_0229_MajorityElementII {

    public static List<Integer> majorityElement(int[] nums) {
        int cand1 = 0, hp1 = 0;
        int cand2 = 0, hp2 = 0;

        for (int num : nums) {
            if (hp1 > 0 && num == cand1)
                hp1++;
            else if (hp2 > 0 && num == cand2)
                hp2++;
            else if (hp1 == 0) {
                cand1 = num;
                hp1++;
            } else if (hp2 == 0) {
                cand2 = num;
                hp2++;
            } else {
                hp1--;
                hp2--;
            }
        }

        int cnt1 = 0, cnt2 = 0;
        for (int num : nums) {
            if (hp1 > 0 && num == cand1)
                cnt1++;

            if (hp2 > 0 && num == cand2)
                cnt2++;
        }

        List<Integer> res = new ArrayList<>();
        if (cnt1 > nums.length / 3)
            res.add(cand1);

        if (cnt2 > nums.length / 3)
            res.add(cand2);

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        List<Integer> ans = majorityElement(nums);
        for (Integer num : ans) {
            System.out.print(num + " ");
        }
    }
}
