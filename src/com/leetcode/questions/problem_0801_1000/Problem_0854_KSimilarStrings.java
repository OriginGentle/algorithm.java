package com.leetcode.questions.problem_0801_1000;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2022/9/21-13:32
 */
public class Problem_0854_KSimilarStrings {

    int n;
    String t;

    public int kSimilarity(String s1, String s2) {
        if (s1.equals(s2))
            return 0;

        n = s1.length();
        t = s2;

        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<String> heap = new PriorityQueue<>((a, b) -> {
            int v1 = process(a), v2 = process(b);
            int d1 = map.get(a), d2 = map.get(b);
            return (v1 + d1) - (v2 + d2);
        });

        map.put(s1, 0);
        heap.add(s1);

        while (!heap.isEmpty()) {
            String cur = heap.poll();
            int step = map.get(cur);
            char[] str = cur.toCharArray();
            int idx = 0;
            while (idx < n && str[idx] == t.charAt(idx))
                idx++;

            for (int i = idx + 1; i < n; i++) {
                if (str[i] != t.charAt(idx) || str[i] == t.charAt(i))
                    continue;

                swap(str, idx, i);
                String nStr = String.valueOf(str);
                swap(str, idx, i);

                if (map.containsKey(nStr) && map.get(nStr) <= step + 1)
                    continue;

                if (nStr.equals(t))
                    return step + 1;

                map.put(nStr, step + 1);
                heap.add(nStr);
            }
        }
        return -1;
    }

    private void swap(char[] str, int i, int j) {
        char c = str[i];
        str[i] = str[j];
        str[j] = c;
    }

    private int process(String s) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += s.charAt(i) != t.charAt(i) ? 1 : 0;
        }
        return ans + 1 >> 1;
    }
}
