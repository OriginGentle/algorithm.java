package com.leetcode.problem_0001_0200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ycb
 * @date 2022/8/19-14:46
 */
public class Problem_0187_RepeatedDnaSequences {

    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        int n = s.length();
        for (int l = 0, r = 10; r <= n; r++, l++) {
            String cur = s.substring(l, r);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            if (map.get(cur) == 2) {
                ans.add(cur);
            }
        }
        return ans;
    }
}
