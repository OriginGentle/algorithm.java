package com.system.D_weekly.code_2023_07_1_week;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ycb
 * @date 2023/7/10-10:24
 * @desc 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * 例如，如果 words = ["ab","cd","ef"]
 * 那么 "abcdef"， "abefcd"，"cdabef"
 * "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串
 * "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联字串在 s 中的开始索引
 * 你可以以 任意顺序 返回答案
 * 1 <= s.length <= 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 和 s 由小写英文字母组成
 * 测试链接 : https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
 */
public class Code02_SubstringWithConcatenationOfAllWords {

    public static int BASE = 499;

    public static int MAXN = 10001;

    public static long[] pow = new long[MAXN];

    public static long[] hash = new long[MAXN];

    static {
        pow[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            pow[i] = pow[i - 1] * BASE;
        }
    }

    // 计算一个字符串的哈希值
    public static long hashValue(String str) {
        if (str.equals("")) {
            return 0;
        }
        int n = str.length();
        long ans = str.charAt(0) - 'a' + 1;
        for (int j = 1; j < n; j++) {
            ans = ans * BASE + str.charAt(j) - 'a' + 1;
        }
        return ans;
    }

    public static void buildHash(String str) {
        hash[0] = str.charAt(0) - 'a' + 1;
        for (int j = 1; j < str.length(); j++) {
            hash[j] = hash[j - 1] * BASE + str.charAt(j) - 'a' + 1;
        }
    }

    // 范围是[l,r)，左闭右开
    public static long hashValue(int l, int r) {
        long ans = hash[r - 1];
        ans -= l == 0 ? 0 : (hash[l - 1] * pow[r - l]);
        return ans;
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (null == s || s.length() == 0 || null == words || words.length == 0) {
            return ans;
        }

        HashMap<Long, Integer> map = new HashMap<>();
        for (String str : words) {
            long v = hashValue(str);
            map.put(v, map.getOrDefault(v, 0) + 1);
        }

        buildHash(s);

        int n = s.length();
        int wordLen = words[0].length();
        int wordNum = words.length;
        int allLen = wordLen * wordNum;

        HashMap<Long, Integer> window = new HashMap<>();
        for (int init = 0; init < wordLen && init + allLen <= n; init++) {
            int debt = wordNum;

            for (int l = init, r = init + wordLen, part = 0;
                 part < wordNum;
                 l += wordLen, r += wordLen, part++) {

                long cur = hashValue(l, r);
                window.put(cur, window.getOrDefault(cur, 0) + 1);
                if (window.get(cur) <= map.getOrDefault(cur, 0)) {
                    debt--;
                }
            }

            if (debt == 0) {
                ans.add(init);
            }
            // [5...10) [10...15) [15...20)
            // [10...15) [15...20)[20...25]
            for (int l1 = init, r1 = init + wordLen, l2 = init + allLen, r2 = init + allLen + wordLen;
                 r2 <= n;
                 l1 += wordLen, r1 += wordLen, l2 += wordLen, r2 += wordLen) {
                // l1...r1 .......  l2...r2
                long out = hashValue(l1, r1);
                long in = hashValue(l2, r2);
                window.put(out, window.get(out) - 1);
                if (window.get(out) < map.getOrDefault(out, 0)) {
                    debt++;
                }

                window.put(in, window.getOrDefault(in, 0) + 1);
                if (window.get(in) <= map.getOrDefault(in, 0)) {
                    debt--;
                }

                if (debt == 0) {
                    ans.add(r1);
                }
            }

            window.clear();
        }

        return ans;
    }
}
