package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycb
 * @since 2022/3/14-8:47
 */
public class Problem_0599_MinimumIndexSumOfTwoLists {

    public String[] findRestaurant(String[] a, String[] b) {
        Map<String, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }
        int iSum = Integer.MAX_VALUE;
        for (int bi = 0; bi < b.length; bi++) {
            if (map.containsKey(b[bi])) {
                Integer ai = map.get(b[bi]);
                if (ai + bi < iSum) {
                    ans.clear();
                    ans.add(b[bi]);
                    iSum = ai + bi;
                } else if (ai + bi == iSum) {
                    ans.add(b[bi]);
                }
            }
        }
        return ans.toArray(new String[ans.size()]);
    }
}
