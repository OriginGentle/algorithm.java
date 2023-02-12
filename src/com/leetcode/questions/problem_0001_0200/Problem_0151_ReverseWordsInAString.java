package com.leetcode.questions.problem_0001_0200;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ycb
 * @date 2022/5/9-10:11
 */
public class Problem_0151_ReverseWordsInAString {

    public static String reverseWords1(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        List<String> cnt = new ArrayList<>();
        int n = s.length();
        int l = 0;
        while (s.charAt(l) == ' ') {
            l++;
        }
        n--;
        while (s.charAt(n) == ' ') {
            n--;
        }
        int r = l;
        while (r <= n) {
            if (s.charAt(r) == ' ') {
                cnt.add(s.substring(l, r));
                l = r;
            } else if (r == n) {
                cnt.add(s.substring(l, r + 1));
                break;
            } else {
                r++;
            }
            while (s.charAt(l) == ' ') {
                l++;
                r = l;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = cnt.size() - 1; i > 0; i--) {
            sb.append(cnt.get(i)).append(" ");
        }
        sb.append(cnt.get(0));
        return sb.toString();
    }

    /*
    ====================================================================================================================
     */

    public static String reverseWords2(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int l = 0;
        int r = s.length() - 1;
        while (l <= r && s.charAt(l) == ' ') {
            l++;
        }
        while (l <= r && s.charAt(r) == ' ') {
            r--;
        }
        Deque<String> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        while (l <= r) {
            char c = s.charAt(l);
            if (sb.length() != 0 && c == ' ') {
                deque.offerFirst(sb.toString());
                sb.setLength(0);
            } else if (c != ' ') {
                sb.append(c);
            }
            l++;
        }
        deque.offerFirst(sb.toString());

        return String.join(" ", deque);
    }

    public static void main(String[] args) {
        String s = "  Bob    Loves  Alice   ";
        String ans = reverseWords2(s);
        System.out.println(ans);
    }
}
