package com.training.day38;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/25-10:58
 */
public class Problem_0438_FindAllAnagramsInAString {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList();
        if (s == null || p == null || s.length() < p.length()) {
            return ans;
        }
        char[] str = s.toCharArray();
        char[] pst = p.toCharArray();
        int N = str.length, M = pst.length;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : pst) {
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            } else {
                map.put(ch, map.get(ch) + 1);
            }
        }
        int all = M;
        for (int end = 0; end < M - 1; end++) {
            if (map.containsKey(str[end])) {
                int count = map.get(str[end]);
                if (count > 0) {
                    all--;
                }
                map.put(str[end], count - 1);
            }
        }
        for (int end = M - 1, start = 0; end < N; end++, start++) {
            if (map.containsKey(str[end])) {
                int count = map.get(str[end]);
                if (count > 0) {
                    all--;
                }
                map.put(str[end], count - 1);
            }
            if (all == 0) {
                ans.add(start);
            }
            if (map.containsKey(str[start])) {
                int count = map.get(str[start]);
                if (count >= 0) {
                    all++;
                }
                map.put(str[start], count + 1);
            }
        }
        return ans;
    }
}
