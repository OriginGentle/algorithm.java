package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycb
 * @since 2022/1/30-18:08
 */
public class Problem_0884_UncommonWordsFromTwoSentences {

    public static String[] uncommonFromSentences1(String s1, String s2) {
        Map<String, Integer> map = new HashMap<>();
        String str = s1 + " " + s2;
        String[] s = str.split(" ");
        for (String cur : s) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        List<String> ans = new ArrayList<>();
        for (String cur : map.keySet()) {
            if (map.get(cur) == 1) {
                ans.add(cur);
            }
        }
        return ans.toArray(new String[ans.size()]);
    }

    /*
    ====================================================================================================================
     */

    public String[] uncommonFromSentences2(String s1, String s2) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        insert(s1, map);
        insert(s2, map);

        List<String> ans = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                ans.add(entry.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }

    public void insert(String s, Map<String, Integer> map) {
        String[] arr = s.split(" ");
        for (String word : arr) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
    }
}
