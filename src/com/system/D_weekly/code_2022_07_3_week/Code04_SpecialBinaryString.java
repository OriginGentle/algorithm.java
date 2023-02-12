package com.system.D_weekly.code_2022_07_3_week;

import java.util.ArrayList;

/**
 * @author ycb
 * @date 2022/7/23-13:19
 * @desc https://leetcode.cn/problems/special-binary-string/
 */
public class Code04_SpecialBinaryString {

    public static String makeLargestSpecial(String s) {
        ArrayList<String> res = new ArrayList<>();
        for (int si = 0; si < s.length(); ) {
            Info info = process(s, si + 1);
            res.add(info.ans);
            si = info.end + 1;
        }
        StringBuilder sb = new StringBuilder();
        res.sort((a, b) -> b.compareTo(a));
        for (String cur : res) {
            sb.append(cur);
        }
        return sb.toString();
    }

    public static class Info {
        public String ans;
        public int end;

        public Info(String s, int e) {
            ans = s;
            end = e;
        }
    }

    public static Info process(String s, int index) {
        ArrayList<String> res = new ArrayList<>();
        while (index < s.length() && s.charAt(index) != '0') {
            Info info = process(s, index + 1);
            res.add(info.ans);
            index = info.end + 1;
        }
        StringBuilder sb = new StringBuilder();
        res.sort((a, b) -> b.compareTo(a));
        for (String cur : res) {
            sb.append(cur);
        }
        return new Info("1" + sb + "0", index);
    }
}
