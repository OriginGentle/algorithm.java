package com.leetcode.questions.problem_0601_0800;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/11/17-21:11
 */
public class Problem_0792_NumberOfMatchingSubsequences {

    public int numMatchingSubseq(String s, String[] words) {
        int n = s.length(), ans = 0;
        Map<Character, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(s.charAt(i), new ArrayList<>());
            list.add(i);
            map.put(s.charAt(i), list);
        }

        for (String w : words) {
            boolean ok = true;
            int m = w.length(), idx = -1;
            for (int i = 0; i < m && ok; i++) {
                List<Integer> cmt = map.getOrDefault(w.charAt(i), new ArrayList<>());
                int l = 0, r = cmt.size() - 1;
                while (l < r) {
                    int mid = (l + r) >> 1;
                    if (cmt.get(mid) > idx)
                        r = mid;
                    else
                        l = mid + 1;
                }

                if (r < 0 || cmt.get(r) <= idx)
                    ok = false;
                else
                    idx = cmt.get(r);
            }
            if (ok)
                ans++;
        }
        return ans;
    }
}
