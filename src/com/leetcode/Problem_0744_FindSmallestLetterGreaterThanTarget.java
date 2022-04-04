package com.leetcode;

/**
 * @author ycb
 * @date 2022/4/3
 */
public class Problem_0744_FindSmallestLetterGreaterThanTarget {

    public static char nextGreatestLetter(char[] letters, char target) {
        int l = 0, r = letters.length - 1;
        if (target >= letters[r]) {
            return letters[0];
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (letters[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return letters[l];
    }

    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        char target = 'c';
        char ans = nextGreatestLetter(letters, target);
        System.out.println(ans);
    }
}
