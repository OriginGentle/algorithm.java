package com.leetcode.problem_0401_0600;

import java.util.*;

/**
 * @author ycb
 * @date 2022/5/7-09:01
 */
public class Problem_0443_MinimumGeneticMutation {

    public char[] keys = {'A', 'C', 'G', 'T'};

    public int minMutation1(String start, String end, String[] bank) {
        if (start.length() != end.length()) {
            return -1;
        }
        if (start.equals(end)) {
            return 0;
        }
        Set<String> cnt = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for (String str : bank) {
            cnt.add(str);
        }
        if (!cnt.contains(end)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);
        int dis = 1;
        // BFS的层序遍历
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != cur.charAt(j)) {
                            StringBuilder sb = new StringBuilder(cur);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            if (cnt.contains(next) && !visited.contains(next)) {
                                if (next.equals(end)) {
                                    return dis;
                                }
                                queue.add(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            dis++;
        }
        return -1;
    }

    /*
    ====================================================================================================================
     */

    String S, T;
    Set<String> set = new HashSet<>();
    static char[] items = new char[]{'A', 'C', 'G', 'T'};

    // 双向BFS
    public int minMutation2(String _s, String _e, String[] bank) {
        S = _s;
        T = _e;
        for (String s : bank) set.add(s);
        return bfs();
    }

    int bfs() {
        Deque<String> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        d1.addLast(S);
        m1.put(S, 0);
        d2.addLast(T);
        m2.put(T, 0);
        while (!d1.isEmpty() && !d2.isEmpty()) {
            int t = -1;
            if (d1.size() <= d2.size()) t = update(d1, m1, m2);
            else t = update(d2, m2, m1);
            if (t != -1) return t;
        }
        return -1;
    }

    int update(Deque<String> d, Map<String, Integer> cur, Map<String, Integer> other) {
        int size = d.size();
        while (size-- > 0) {
            String s = d.pollFirst();
            char[] cs = s.toCharArray();
            int step = cur.get(s);
            for (int i = 0; i < 8; i++) {
                for (char c : items) {
                    if (cs[i] == c) continue;
                    char[] clone = cs.clone();
                    clone[i] = c;
                    String sub = String.valueOf(clone);
                    if (!set.contains(sub)) continue;
                    if (other.containsKey(sub)) return step + 1 + other.get(sub);
                    if (!cur.containsKey(sub) || cur.get(sub) > step + 1) {
                        cur.put(sub, step + 1);
                        d.addLast(sub);
                    }
                }
            }
        }
        return -1;
    }

    /*
    ====================================================================================================================
     */

    class Node {
        String s;
        int val;

        Node(String _s) {
            s = _s;
            for (int i = 0; i < 8; i++) {
                if (s.charAt(i) != T.charAt(i)) val++;
            }
        }
    }

    // A*算法
    public int minMutation3(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>();
        for (String s : bank) set.add(s);
        S = start;
        T = end;
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.val - b.val);
        Map<String, Integer> map = new HashMap<>();
        q.add(new Node(S));
        map.put(S, 0);
        while (!q.isEmpty()) {
            Node node = q.poll();
            char[] cs = node.s.toCharArray();
            int step = map.get(node.s);
            for (int i = 0; i < 8; i++) {
                for (char c : items) {
                    if (cs[i] == c) continue;
                    char[] clone = cs.clone();
                    clone[i] = c;
                    String sub = String.valueOf(clone);
                    if (!set.contains(sub)) continue;
                    if (sub.equals(T)) return step + 1;
                    if (!map.containsKey(sub) || map.get(sub) > step + 1) {
                        map.put(sub, step + 1);
                        q.add(new Node(sub));
                    }
                }
            }
        }
        return -1;
    }
}
