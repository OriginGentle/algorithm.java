package com.system.C_training.day17;

import java.util.HashMap;

/**
 * @author ycb
 * @date 2021/9/13-8:25
 * @description https://leetcode.com/problems/distinct-subsequences-ii/
 */
public class Code05_DistinctSubseqValue {

    public static int distinctSubseqII1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int m = 1000000007;
        char[] str = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int all = 1;
        for (char x : str) {
            // 此时新加的
            int newAdd = all;
            int curAll = all;
            curAll = (newAdd + curAll) % m;
            curAll = (curAll - (map.containsKey(x) ? map.get(x) : 0) + m) % m;
            all = curAll;
            map.put(x, newAdd);
        }
        return all - 1;
    }

    /*
    ====================================================================================================================
     */

    public static int distinctSubseqII2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int m = 1000000007;
        char[] str = s.toCharArray();
        int[] count = new int[26];
        int all = 1;
        for (char x : str) {
            int add = (all - count[x - 'a'] + m) % m;
            all = (add + all) % m;
            count[x - 'a'] = (count[x - 'a'] + add) % m;
        }
        return all - 1;
    }
}
