package com.leetcode.problem_0201_0400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/8/29-11:19
 */
public class Problem_0216_CombinationSumIII {

    public static List<List<Integer>> ans;

    public static List<Integer> tmp;

    public static int target;

    public static List<List<Integer>> combinationSum3(int k, int n) {
        init(n);
        process(1, 9, k, n);
        return ans;
    }

    private static void process(int idx, int end, int times, int target) {
        if (tmp.size() + end - idx + 1 < times)
            return;

        if (tmp.size() == times) {
            check(tmp, target);
            return;
        }

        tmp.add(idx);
        process(idx + 1, end, times, target);
        tmp.remove(tmp.size() - 1);
        // 不要idx位置的数
        process(idx + 1, end, times, target);
    }

    private static void check(List<Integer> cnt, int sum) {
        int ansT = 0;
        for (int num : cnt) {
            ansT += num;
        }
        if (ansT == sum)
            ans.add(new ArrayList<>(cnt));
    }

    private static void init(int sum) {
        ans = new ArrayList<>();
        tmp = new ArrayList<>();
        target = sum;
    }

    public static void main(String[] args) {
        int k = 3, n = 7;
        List<List<Integer>> ans = combinationSum3(k, n);
        System.out.println(ans);
    }
}
