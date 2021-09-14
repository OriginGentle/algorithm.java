package com.training.day17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ycb
 * @date 2021/9/13-8:24
 * @description https://leetcode.com/problems/palindrome-pairs/
 */
public class Code03_PalindromePairs {

    public static List<List<Integer>> palindromePairs(String[] words) {
        HashMap<String, Integer> wordset = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordset.put(words[i], i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            ans.addAll(findAll(words[i], i, wordset));
        }
        return ans;
    }

    public static List<List<Integer>> findAll(String word, int index, HashMap<String, Integer> words) {
        List<List<Integer>> res = new ArrayList<>();
        String reverse = reverse(word);
        Integer rest = words.get("");
        // 字符串本身就是回文串
        if (rest != null && rest != index && word.equals(reverse)) {
            addRecord(res, rest, index);
            addRecord(res, index, rest);
        }
        // 回文半径数组
        // manacher算法
        int[] rs = manacherArr(word);
        int mid = rs.length >> 1;
        for (int i = 1; i < mid; i++) {
            if (i - rs[i] == -1) {
                rest = words.get(reverse.substring(0, mid - i));
                if (rest != null && rest != index) {
                    addRecord(res, rest, index);
                }
            }
        }
        for (int i = mid + 1; i < rs.length; i++) {
            if (i + rs[i] == rs.length) {
                rest = words.get(reverse.substring((mid << 1) - i));
                if (rest != null && rest != index) {
                    addRecord(res, index, rest);
                }
            }
        }
        return res;
    }

    public static int[] manacherArr(String word) {
        char[] mStr = manacherStr(word);
        int[] rs = new int[mStr.length];
        int center = -1;
        int pr = -1;
        for (int i = 0; i != mStr.length; i++) {
            rs[i] = pr > i ? Math.min(rs[(center << 1) - i], pr - i) : 1;
            while (i + rs[i] < mStr.length && i - rs[i] > -1) {
                if (mStr[i + rs[i]] != mStr[i - rs[i]]) {
                    break;
                }
                rs[i]++;
            }
            if (i + rs[i] > pr) {
                pr = i + rs[i];
                center = i;
            }
        }
        return rs;
    }

    public static char[] manacherStr(String word) {
        char[] chs = word.toCharArray();
        char[] mStr = new char[chs.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < mStr.length; i++) {
            mStr[i] = (i & 1) == 0 ? '#' : chs[index++];
        }
        return mStr;
    }

    public static void addRecord(List<List<Integer>> res, int left, int right) {
        List<Integer> ans = new ArrayList<>();
        ans.add(left);
        ans.add(right);
        res.add(ans);
    }

    public static String reverse(String str) {
        char[] chs = str.toCharArray();
        int l = 0;
        int r = chs.length - 1;
        while (l < r) {
            char temp = chs[l];
            chs[l++] = chs[r];
            chs[r--] = temp;
        }
        return String.valueOf(chs);
    }

}
