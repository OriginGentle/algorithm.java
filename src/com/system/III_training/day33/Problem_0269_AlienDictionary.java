package com.system.III_training.day33;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ycb
 * @since 2021/10/21-8:33
 */
public class Problem_0269_AlienDictionary {

    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        int N = words.length;
        // 入度表
        HashMap<Character, Integer> inDegreeMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (char c : words[i].toCharArray()) {
                inDegreeMap.put(c, 0);
            }
        }
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] nex = words[i + 1].toCharArray();
            int len = Math.min(cur.length, nex.length);
            int j = 0;
            for (; j < len; j++) {
                if (cur[j] != nex[j]) {
                    if (!graph.containsKey(cur[j])) {
                        graph.put(cur[j], new HashSet<>());
                    }
                    if (!graph.get(cur[j]).contains(nex[j])) {
                        graph.get(cur[j]).add(nex[j]);
                        inDegreeMap.put(nex[j], inDegreeMap.get(nex[j] + 1));
                    }
                    break;
                }
            }
            if (j < cur.length && j == nex.length) {
                return "";
            }
        }
        StringBuilder ans = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for (Character key : inDegreeMap.keySet()) {
            if (inDegreeMap.get(key) == 0) {
                q.offer(key);
            }
        }
        while (!q.isEmpty()) {
            char cur = q.poll();
            ans.append(cur);
            if (graph.containsKey(cur)) {
                for (char next : graph.get(cur)) {
                    inDegreeMap.put(next, inDegreeMap.get(next) - 1);
                    if (inDegreeMap.get(next) == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        return ans.length() == inDegreeMap.size() ? ans.toString() : "";
    }
}
