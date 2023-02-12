package com.leetcode.questions.problem_0801_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/11/7-08:21
 */
public class Problem_0816_AmbiguousCoordinates {

    public static List<String> ambiguousCoordinates(String s) {
        int n = s.length() - 2;
        List<String> res = new ArrayList<>();
        s = s.substring(1, s.length() - 1);

        for (int L = 1; L < n; L++) {
            List<String> lt = process(s.substring(0, L));
            if (lt.isEmpty())
                continue;

            List<String> rt = process(s.substring(L));
            if (rt.isEmpty())
                continue;

            for (String l : lt) {
                for (String r : rt)
                    res.add("(" + l + ", " + r + ")");
            }
        }
        return res;
    }

    public static List<String> process(String s) {
        List<String> ans = new ArrayList<>();
        if (s.charAt(0) != '0' || "0".equals(s)) {
            ans.add(s);
        }

        for (int pos = 1; pos < s.length(); pos++) {

            if ((pos != 1 && s.charAt(0) == '0')
                    || s.charAt(s.length() - 1) == '0') {
                continue;
            }

            ans.add(s.substring(0, pos) + "." + s.substring(pos));
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "(0123)";
        // [1,5)
        System.out.println(ambiguousCoordinates(s));
    }
}
