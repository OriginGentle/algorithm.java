package com.system.C_training.day03;

/**
 * @Author ycb
 * @Date 2021/7/27-7:56
 * @Description https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class Code01_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        // ASCII码:0~255
        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        // 记录每个字符出现的位置
        map[str[0]] = 0;
        int pre = 1;
        int ans = 1;
        int N = str.length;
        for (int i = 1; i < N; i++) {
            // 现在来到i位置,i-1位置时无重复字符子串的最长长度是pre
            // 情况一:str[i]位置的字符出现在pre之前
            // 情况二:str[i]出现在 i ~ pre 之间
            pre = Math.min(i - map[str[i]], pre + 1);
            ans = Math.max(ans, pre);
            map[str[i]] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abc";
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
            map[str[i]] = 1;
        }
        for (int i = 0; i < map.length; i++) {
            System.out.print(map[i] + " ");
        }
    }

}
