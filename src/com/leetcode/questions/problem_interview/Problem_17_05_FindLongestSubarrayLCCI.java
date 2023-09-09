package com.leetcode.questions.problem_interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2023/8/24-22:46
 */
public class Problem_17_05_FindLongestSubarrayLCCI {

    public static String[] findLongestSubarray(String[] array) {
        // key: 前缀和  val: 位置
        Map<Integer, Integer> idx = new HashMap<>();
        idx.put(0, -1);
        int sum = 0, maxLen = 0, sIdx = -1;
        int n = array.length;
        for (int i = 0; i < n; i++) {

            if (Character.isLetter(array[i].charAt(0)))
                sum++;
            else
                sum--;

            if (idx.containsKey(sum)) {
                int firstIdx = idx.get(sum);
                if (i - firstIdx > maxLen) {
                    maxLen = i - firstIdx;
                    sIdx = firstIdx + 1;
                }
            } else {
                idx.put(sum, i);
            }
        }

        if (maxLen == 0) {
            return new String[0];
        }

        String[] ans = new String[maxLen];
        System.arraycopy(array, sIdx, ans, 0, maxLen);
        return ans;
    }
}
