package com.leetcode.questions.problem_1801_2000;

import java.util.HashSet;

/**
 * @author ycb
 * @date 2022/12/6-00:06
 */
public class Problem_1805_NumberOfDifferentIntegersInAString {

    public static int numDifferentIntegers(String word) {
        HashSet<String> set = new HashSet<>();
        int n = word.length(), p1 = 0, p2;
        while (true) {
            while (p1 < n && !Character.isDigit(word.charAt(p1))) {
                p1++;
            }
            if (p1 == n) {
                break;
            }
            p2 = p1;
            while (p2 < n && Character.isDigit(word.charAt(p2))) {
                p2++;
            }
            while (p2 - p1 > 1 && word.charAt(p1) == '0') { // 去除前导 0
                p1++;
            }
            set.add(word.substring(p1, p2));
            p1 = p2;
        }
        return set.size();
    }

    public static void main(String[] args) {
        String word = "a123bc34d8ef34";
        int ans = numDifferentIntegers(word);
        System.out.println(ans);
    }
}
