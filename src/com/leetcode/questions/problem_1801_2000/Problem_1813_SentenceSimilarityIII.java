package com.leetcode.questions.problem_1801_2000;

/**
 * @author ycb
 * @date 2023/1/16-10:42
 */
public class Problem_1813_SentenceSimilarityIII {

    public static boolean areSentencesSimilar(String s1, String s2) {
        String[] str1 = s1.split(" ");
        String[] str2 = s2.split(" ");
        int i = 0, j = 0;
        while (i < str1.length && i < str2.length
                && str1[i].equals(str2[i])) {
            i++;
        }
        while (j < str1.length - i && j < str2.length - i
                && str1[str1.length - j - 1].equals(str2[str2.length - j - 1])) {
            j++;
        }
        return i + j == Math.min(str1.length, str2.length);
    }
}
