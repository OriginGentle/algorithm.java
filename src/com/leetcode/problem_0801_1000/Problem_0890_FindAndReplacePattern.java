package com.leetcode.problem_0801_1000;

import javax.xml.transform.OutputKeys;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ycb
 * @date 2022/6/29-10:09
 */
public class Problem_0890_FindAndReplacePattern {

    public static List<String> findAndReplacePattern(String[] words, String p) {
        List<String> ans = new ArrayList<>();
        int[] map = new int[26];
        int[] vis = new int[26];
        for (String s : words) {
            Arrays.fill(map, -1);
            Arrays.fill(vis, 0);
            boolean flag = true;
            for (int i = 0; i < p.length() && flag; i++) {
                int c1 = s.charAt(i) - 'a';
                int c2 = p.charAt(i) - 'a';
                if (map[c1] == -1 && vis[c2] == 0) {
                    map[c1] = c2;
                    vis[c2] = 1;
                } else if (map[c1] != c2) {
                    flag = false;
                }
            }
            if (flag) {
                ans.add(s);
            }
        }
        return ans;
    }
}
