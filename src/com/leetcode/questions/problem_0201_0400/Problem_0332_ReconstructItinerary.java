package com.leetcode.questions.problem_0201_0400;

import java.util.*;

/**
 * @author ycb
 * @date 2022/11/3-16:19
 */
public class Problem_0332_ReconstructItinerary {

    public Map<String, PriorityQueue<String>> cmt = new HashMap<>();
    public List<String> ans = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> t : tickets) {
            String from = t.get(0), to = t.get(1);
            if (!cmt.containsKey(from))
                cmt.put(from, new PriorityQueue<>());
            cmt.get(from).add(to);
        }

        dfs("JFK");
        Collections.reverse(ans);
        return ans;
    }

    public void dfs(String cur) {
        while (cmt.containsKey(cur) && cmt.get(cur).size() > 0) {
            String next = cmt.get(cur).poll();
            dfs(next);
        }
        ans.add(cur);
    }
}
