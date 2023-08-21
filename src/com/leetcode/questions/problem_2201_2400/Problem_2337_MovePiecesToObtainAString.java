package com.leetcode.questions.problem_2201_2400;

/**
 * @author ycb
 * @date 2023/8/21-21:43
 */
public class Problem_2337_MovePiecesToObtainAString {

    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;
        while (i < n && j < n) {

            while (i < n && start.charAt(i) == '_') {
                i++;
            }

            while (j < n && target.charAt(j) == '_') {
                j++;
            }

            if (i < n && j < n) {

                if (start.charAt(i) != target.charAt(j)) {
                    return false;
                }

                char cur = start.charAt(i);
                if ((cur == 'L' && i < j) || (cur == 'R' && i > j)) {
                    return false;
                }

                i++;
                j++;
            }
        }

        while (i < n) {
            if (start.charAt(i) != '_') {
                return false;
            }
            i++;
        }


        while (j < n) {
            if (target.charAt(j) != '_') {
                return false;
            }

            j++;
        }

        return true;
    }
}
