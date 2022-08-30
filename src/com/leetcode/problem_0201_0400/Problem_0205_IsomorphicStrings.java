package com.leetcode.problem_0201_0400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/8/30-14:34
 */
public class Problem_0205_IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char si = s.charAt(i), ti = t.charAt(i);

            if ((s2t.containsKey(si) && s2t.get(si) != ti) || (t2s.containsKey(ti) && t2s.get(ti) != si))
                return false;

            s2t.put(si, ti);
            t2s.put(ti, si);
        }
        return true;
    }
}
