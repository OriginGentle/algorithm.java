package com.leetcode.problem_0001_0200;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ycb
 * @since 2022/1/27-13:23
 */
public class Problem_0077_Combinations {

    public static List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k <= 0 || n < k) {
            return ans;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, ans, path);
        return ans;
    }

    private static void dfs(int n, int k, int i, List<List<Integer>> ans, Deque<Integer> path) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 尝试每一个位置作为开头
        for (int j = i; j <= n - (k - path.size()) + 1; j++) {
            path.addLast(j);
            System.out.println("递归之前:" + path);
            dfs(n, k, j + 1, ans, path);
            System.out.println("递归之后:" + path);
            path.removeLast();
        }
    }

    /*
    ====================================================================================================================
     */

    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> combine2(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    public void dfs(int cur, int n, int k) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录合法的答案
        if (temp.size() == k) {
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        // 考虑选择当前位置
        temp.add(cur);
        dfs(cur + 1, n, k);
        temp.remove(temp.size() - 1);
        // 考虑不选择当前位置
        dfs(cur + 1, n, k);
    }

    public static void main(String[] args) {
        int n = 5;
        int k = 2;
        combine1(n, k);
    }
}
