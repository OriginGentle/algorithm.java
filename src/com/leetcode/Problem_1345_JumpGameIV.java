package com.leetcode;

import java.util.*;

/**
 * @author ycb
 * @since 2022/1/21-10:53
 */
public class Problem_1345_JumpGameIV {

    public static int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited.add(0);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int index = cur[0], step = cur[1];
            if (index == arr.length - 1) {
                return step;
            }
            int v = arr[index];
            step++;
            if (map.containsKey(v)) {
                for (int i : map.get(v)) {
                    if (visited.add(i)) {
                        queue.offer(new int[]{i, step});
                    }
                }
                map.remove(v);
            }
            if (index + 1 < arr.length && visited.add(index + 1)) {
                queue.offer(new int[]{index + 1, step});
            }
            if (index - 1 >= 0 && visited.add(index - 1)) {
                queue.offer(new int[]{index - 1, step});
            }
        }
        return -1;
    }
}
