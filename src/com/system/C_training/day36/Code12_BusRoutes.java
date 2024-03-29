package com.system.C_training.day36;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ycb
 * @date 2021/10/24-17:25
 * @description https://leetcode.com/problems/bus-routes/
 */
public class Code12_BusRoutes {

    // 根据线路宽度优先遍历
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (!map.containsKey(routes[i][j])) {
                    map.put(routes[i][j], new ArrayList<>());
                }
                map.get(routes[i][j]).add(i);
            }
        }
        ArrayList<Integer> queue = new ArrayList<>();
        boolean[] set = new boolean[n];
        // 把开始站所在的路线加进去
        for (int route : map.get(source)) {
            queue.add(route);
            set[route] = true;
        }
        int len = 1;
        while (!queue.isEmpty()) {
            ArrayList<Integer> nextLevel = new ArrayList<>();
            for (int route : queue) {
                int[] bus = routes[route];
                for (int station : bus) {
                    if (station == target) {
                        return len;
                    }
                    for (int nextRoute : map.get(station)) {
                        if (!set[nextRoute]) {
                            nextLevel.add(nextRoute);
                            set[nextRoute] = true;
                        }
                    }
                }
            }
            queue = nextLevel;
            len++;
        }
        return -1;
    }
}
