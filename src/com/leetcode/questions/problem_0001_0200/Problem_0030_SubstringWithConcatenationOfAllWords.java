package com.leetcode.questions.problem_0001_0200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ycb
 * @since 2021/11/30-8:17
 */
public class Problem_0030_SubstringWithConcatenationOfAllWords {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return ans;
        }
        // key:words中的某个单词
        // value:出现的次数
        HashMap<String, Integer> map = new HashMap<>();
        // 单词的长度相同
        int oneWordLen = words[0].length(), size = words.length;
        // 所有单词的总长度
        int allLen = oneWordLen * size;
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < oneWordLen; i++) {
            int left = i, right = i, count = 0;
            HashMap<String, Integer> tmp = new HashMap<>();
            while (right + oneWordLen <= s.length()) {
                String str = s.substring(right, right + oneWordLen);
                right += oneWordLen;
                if (!map.containsKey(str)) {
                    count = 0;
                    left = right;
                    tmp.clear();
                } else {
                    tmp.put(str, tmp.getOrDefault(str, 0) + 1);
                    count++;
                    while (tmp.getOrDefault(str, 0) > map.getOrDefault(str, 0)) {
                        String word = s.substring(left, left + oneWordLen);
                        count--;
                        tmp.put(word, tmp.getOrDefault(word, 0) - 1);
                        left += oneWordLen;
                    }
                    if (count == size) {
                        ans.add(left);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "word"};
        List<Integer> ans = findSubstring(s, words);
        for (Integer an : ans) {
            System.out.print(an + " ");
        }
    }
}
