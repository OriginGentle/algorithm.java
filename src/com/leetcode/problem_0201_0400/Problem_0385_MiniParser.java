package com.leetcode.problem_0201_0400;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ycb
 * @since 2022/4/15-8:17
 */
public class Problem_0385_MiniParser {

    // 题目自定义结构
    public static class NestedInteger {

        public NestedInteger() {
        }

        public NestedInteger(Integer val) {
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger() {
            return null;
        }

        // Set this NestedInteger to hold a single integer.
        void setInteger(int value) {
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        void add(NestedInteger ni) {
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList() {
            return null;
        }
    }

    static int INF = 1000010;

    public static NestedInteger deserialize(String s) {
        Deque<NestedInteger> d = new ArrayDeque<>();
        char[] cs = s.toCharArray();
        int n = cs.length, i = 0;
        while (i < n) {
            if (cs[i] == ',' && ++i >= 0) continue;
            if (cs[i] == '-' || (cs[i] >= '0' && cs[i] <= '9')) {
                int j = i + 1;
                while (j < n && cs[j] >= '0' && cs[j] <= '9') {
                    j++;
                }
                d.addLast(new NestedInteger(Integer.parseInt(s.substring(i, j))));
                i = j;
            } else if (cs[i] == '[') {
                d.addLast(new NestedInteger());
                d.addLast(new NestedInteger(INF));
                i++;
            } else {
                List<NestedInteger> list = new ArrayList<>();
                while (!d.isEmpty()) {
                    NestedInteger cur = d.pollLast();
                    if (cur.isInteger() && cur.getInteger() == INF) break;
                    list.add(cur);
                }
                for (int j = list.size() - 1; j >= 0; j--) {
                    d.peekLast().add(list.get(j));
                }
                i++;
            }
        }
        return d.peekLast();
    }
}
