package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @since 2022/1/26-8:26
 */
public class Problem_2013_DetectSquares {

    private Map<Integer, Map<Integer, Integer>> row2Col = new HashMap<>();

    public void add(int[] point) {
        int x = point[0], y = point[1];
        Map<Integer, Integer> col2Cnt = row2Col.getOrDefault(x, new HashMap<>());
        col2Cnt.put(y, col2Cnt.getOrDefault(y, 0) + 1);
        row2Col.put(x, col2Cnt);
    }

    public int count(int[] point) {
        int x = point[0], y = point[1];
        int ans = 0;
        Map<Integer, Integer> col2Cnt = row2Col.getOrDefault(x, new HashMap<>());
        for (int ny : col2Cnt.keySet()) {
            if (ny == y) {
                continue;
            }
            int c1 = col2Cnt.get(ny);
            int len = y - ny;
            int[] nums = new int[]{x + len, x - len};
            for (int nx : nums) {
                Map<Integer, Integer> tmp = row2Col.getOrDefault(nx, new HashMap<>());
                int c2 = tmp.getOrDefault(y, 0), c3 = tmp.getOrDefault(ny, 0);
                ans += c1 * c2 * c3;
            }
        }
        return ans;
    }
}
