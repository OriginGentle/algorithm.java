package com.leetcode.questions.problem_0801_1000;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ycb
 * @since 2022/4/17-13:04
 */
public class Problem_0819_MostCommonWord {

    public static String mostCommonWord(String p, String[] banned) {
        Set<String> set = new HashSet();
        for (String s : banned) {
            set.add(s);
        }
        char[] str = p.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        int l = 0, r = 0, n = str.length;
        String ans = null;
        for (int i = 0; i < n; ) {
            if (!Character.isLetter(str[i]) && ++i >= 0) continue;
            int j = i;
            while (j < n && Character.isLetter(str[j])) j++;
            String cur = p.substring(i, j).toLowerCase();
            i = j + 1;
            if (set.contains(cur)) continue;
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            if (ans == null || map.get(cur) > map.get(ans)) ans = cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        String p = "Bob";
        String[] b = {};
        String ans = mostCommonWord(p, b);
        System.out.println(ans);
    }
}
