package com.leetcode.problem_0801_1000;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @date 2022/4/10
 */
public class Problem_0804_UniqueMorseCodeWords {

    static String[] morse = {
            ".-", "-...", "-.-.", "-..", ".",
            "..-.", "--.", "....", "..", ".---",
            "-.-", ".-..", "--", "-.", "---",
            ".--.", "--.-", ".-.", "...", "-",
            "..-", "...-", ".--", "-..-", "-.--", "--.."
    };

    public static int uniqueMorseRepresentations(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String word : words) {
            char[] str = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char ch : str) {
                String cur = morse[ch - 'a'];
                sb.append(cur);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
