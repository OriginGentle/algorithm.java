package com.leetcode.problem_1401_1600;

/**
 * @author ycb
 * @date 2022/9/9-23:23
 */
public class Problem_1598_CrawlerLogFolder {

    public static int minOperations(String[] logs) {
        int depth = 0;
        for (String s : logs) {
            if (s.equals("../"))
                depth = Math.max(0, depth - 1);
            else if (!s.equals("./"))
                depth++;
        }
        return depth;
    }
}
