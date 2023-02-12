package com.system.C_training.day28;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/14-8:34
 */
public class Problem_0022_GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        char[] path = new char[n << 1];
        List<String> ans = new ArrayList<>();
        process(path, 0, 0, n, ans);
        return ans;
    }

    // path 做的决定  path[0....index-1]做完决定的！
    // path[index.....] 还没做决定，当前轮到index位置做决定！
    public static void process(char[] path, int index, int leftMinusRight, int left, List<String> ans) {
        if (index == path.length) {
            ans.add(String.valueOf(path));
        } else {
            if (leftMinusRight > 0) {
                path[index] = ')';
                process(path, index + 1, leftMinusRight - 1, left, ans);
            }
            if (left > 0) {
                path[index] = '(';
                process(path, index + 1, leftMinusRight + 1, left - 1, ans);
            }
        }
    }
}
