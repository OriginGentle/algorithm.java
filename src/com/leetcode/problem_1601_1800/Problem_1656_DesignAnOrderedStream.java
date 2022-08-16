package com.leetcode.problem_1601_1800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/8/16-11:42
 */
public class Problem_1656_DesignAnOrderedStream {

    class OrderedStream {
        public String[] s;
        public int ptr;

        public OrderedStream(int n) {
            s = new String[n + 1];
            ptr = 1;
        }

        public List<String> insert(int idKey, String value) {
            List<String> ans = new ArrayList<>();
            s[idKey] = value;
            while (ptr < s.length && s[ptr] != null) {
                ans.add(s[ptr++]);
            }
            return ans;
        }
    }
}
