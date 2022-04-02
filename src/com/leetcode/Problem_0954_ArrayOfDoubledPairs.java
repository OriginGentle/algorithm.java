package com.leetcode;

import java.util.*;

/**
 * @author ycb
 * @date 2022/4/1
 */
public class Problem_0954_ArrayOfDoubledPairs {

    public static boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        if ((map.getOrDefault(0, 0) % 2) != 0) {
            return false;
        }
        List<Integer> val = new ArrayList<>();
        for (int v : map.keySet()) {
            val.add(v);
        }
        Collections.sort(val, (a, b) -> Math.abs(a) - Math.abs(b));
        for (int va : val) {
            if (map.getOrDefault(va << 1, 0) < map.get(va)) {
                return false;
            }
            map.put(va << 1, map.getOrDefault(va << 1, 0) - map.get(va));
        }
        return true;
    }
}
