package com.system.III_training.day45;

import java.util.HashSet;

/**
 * @author ycb
 * @since 2021/11/7-14:34
 */
public class Problem_0291_WordPatternII {

    public static boolean wordPatternMatch(String pattern, String str) {
        return match(str, pattern, 0, 0, new String[26], new HashSet<>());
    }

    // 题目有限制，str和pattern其中的字符，一定是a~z小写
    // p[a] -> "abc"
    // p[b] -> "fbf"
    // 需要指代的表最多26长度
    // String[] map -> new String[26]
    // p[a] -> "abc"   map[0] -> "abc"
    // p[b] -> "fbf"   map[1] -> "fbf";
    // p[z] -> "kfk"   map[25] -> "kfk"
    // HashSet<String> set -> map中指代了哪些字符串
    // str[si.......]  是不是符合  p[pi......]？符合返回true，不符合返回false
    // 之前的决定！由map和set，告诉我！不能冲突！
    public static boolean match(String s, String p, int si, int pi, String[] map, HashSet<String> set) {
        if (si == s.length() && pi == p.length()) {
            return true;
        }
        // str和pattern，并没有都结束！
        if (si == s.length() || pi == p.length()) {
            return false;
        }
        // str和pattern，都没有结束！
        char ch = p.charAt(pi);
        String cur = map[ch - 'a'];
        if (cur != null) { // 当前p[pi]已经指定过了！
            return si + cur.length() <= s.length() // 不能越界
                    && cur.equals(s.substring(si, si + cur.length())) // 之前指代过的字符和当前字符能配上
                    && match(s, p, si + cur.length(), pi + 1, map, set); // 后续也都能配上
        }
        // 当前p[pi]还没有指定过
        int end = s.length();
        // 剪枝
        for (int i = p.length() - 1; i > pi; i--) {
            end -= map[p.charAt(i) - 'a'] == null ? 1 : map[p.charAt(i) - 'a'].length();
        }
        for (int i = si; i < end; i++) {
            // 从si出发的所有前缀串，全试
            cur = s.substring(si, i + 1);
            // 但是，只有这个前缀串，之前没占过别的坑！才能去尝试
            if (!set.contains(cur)) {
                set.add(cur);
                map[ch - 'a'] = cur;
                if (match(s, p, i + 1, pi + 1, map, set)) {
                    return true;
                }
                // 恢复现场
                map[ch - 'a'] = null;
                set.remove(cur);
            }
        }
        return false;
    }
}
