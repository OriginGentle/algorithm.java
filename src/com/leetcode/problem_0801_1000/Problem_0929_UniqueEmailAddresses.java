package com.leetcode.problem_0801_1000;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @date 2022/6/4-20:16
 */
public class Problem_0929_UniqueEmailAddresses {

    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String str : emails) {
            StringBuilder sb = new StringBuilder();
            int n = str.length();
            int i = 0;
            boolean flag = true;
            while (i < n) {
                char cur = str.charAt(i);
                if (cur == '@') break;
                if (cur == '.') {
                    ++i;
                    continue;
                }
                if (cur == '+') flag = false;
                if (flag) {
                    sb.append(cur);
                }
                i++;
            }
            set.add(sb.append(str.substring(i)).toString());
        }
        return set.size();
    }
}
