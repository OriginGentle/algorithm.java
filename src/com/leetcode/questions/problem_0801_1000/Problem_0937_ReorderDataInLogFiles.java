package com.leetcode.questions.problem_0801_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/5/3-17:50
 */
public class Problem_0937_ReorderDataInLogFiles {

    public static class Log {
        public int type;
        public int index;
        public String str;
        public String sign;
        public String content;

        public Log(String s, int idx) {
            index = idx;
            int n = s.length();
            int i = 0;
            while (i < n && s.charAt(i) != ' ') i++;
            sign = s.substring(0, i);
            content = s.substring(i + 1);
            str = s;
            type = Character.isDigit(content.charAt(0)) ? 1 : 0;
        }
    }

    public String[] reorderLogFiles(String[] logs) {
        int n = logs.length;
        List<Log> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Log(logs[i], i));
        }
        list.sort((a, b) -> {
            if (a.type != b.type) return a.type - b.type;
            if (a.type == 1) return a.index - b.index;
            return !a.content.equals(b.content) ? a.content.compareTo(b.content) : a.sign.compareTo(b.sign);
        });
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(i).str;
        }
        return ans;
    }
}
