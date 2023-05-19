package com.leetcode.questions.problem_1001_1200;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ycb
 * @date 2023/5/19-21:51
 */
public class Problem_1079_LetterTilePossibilities {

    public static int numTilePossibilities(String tiles) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (char c : tiles.toCharArray()) {
            set.add(c);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return dfs(tiles.length(), map, set) - 1;
    }

    private static int dfs(int i, Map<Character, Integer> map, Set<Character> set) {
        if (i == 0) {
            return 1;
        }

        int res = 1;
        for (char c : set) {
            if (map.get(c) > 0) {
                map.put(c, map.get(c) - 1);
                res += dfs(i - 1, map, set);
                map.put(c, map.get(c) + 1);
            }
        }
        return res;
    }
}
