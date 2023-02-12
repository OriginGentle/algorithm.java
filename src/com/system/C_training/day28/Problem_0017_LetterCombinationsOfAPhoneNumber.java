package com.system.C_training.day28;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/14-8:33
 */
public class Problem_0017_LetterCombinationsOfAPhoneNumber {

    public static char[][] phone = {
            {'a', 'b', 'c'}, // 2
            {'d', 'e', 'f'}, // 3
            {'g', 'h', 'i'}, // 4
            {'j', 'k', 'l'}, // 5
            {'m', 'n', 'o'}, // 6
            {'p', 'q', 'r', 's'}, // 7
            {'t', 'u', 'v'},// 8
            {'w', 'x', 'y', 'z'} // 9
    };

    public static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.equals("")) {
            return ans;
        }
        char[] str = digits.toCharArray();
        char[] path = new char[str.length];
        process(str, 0, path, ans);
        return ans;
    }

    public static void process(char[] str, int index, char[] path, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(path));
        } else {
            char[] cands = phone[str[index] - '2'];
            for (char cur : cands) {
                path[index] = cur;
                process(str, index + 1, path, ans);
            }
        }
    }
}
