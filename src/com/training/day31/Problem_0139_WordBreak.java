package com.training.day31;

import java.util.List;

/**
 * @author ycb
 * @since 2021/10/17-21:24
 */
public class Problem_0139_WordBreak {

    public static class Node {
        public boolean end;
        public Node[] next;

        public Node() {
            end = false;
            next = new Node[26];
        }
    }

    public static boolean wordBreak1(String s, List<String> wordDict) {
        Node root = new Node();
        // 利用前缀树代替枚举行为
        for (String str : wordDict) {
            char[] chs = str.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Node();
                }
                node = node.next[index];
            }
            node.end = true;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        // dp[i]  word[i.....] 能不能被分解
        boolean[] dp = new boolean[N + 1];
        // dp[N] word[N...]  -> ""  能不能够被分解
        // dp[i] ... dp[i+1....]
        dp[N] = true;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                cur = cur.next[str[end] - 'a'];
                // 没路
                if (cur == null) {
                    break;
                }
                // 有路
                if (cur.end) {
                    // i...end 真的是一个有效的前缀串  end+1....  能不能被分解
                    dp[i] |= dp[end + 1];
                }
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[0];
    }

    /*
    ====================================================================================================================
     */

    // follow up:返回有多少种方法数
    public static int wordBreak2(String s, List<String> wordDict) {
        Node root = new Node();
        // 利用前缀树代替枚举行为
        for (String str : wordDict) {
            char[] chs = str.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new Node();
                }
                node = node.next[index];
            }
            node.end = true;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        // dp[i]  word[i.....] 能不能被分解
        int[] dp = new int[N + 1];
        // dp[N] word[N...]  -> ""  能不能够被分解
        // dp[i] ... dp[i+1....]
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                cur = cur.next[str[end] - 'a'];
                // 没路
                if (cur == null) {
                    break;
                }
                // 有路
                if (cur.end) {
                    // i...end 真的是一个有效的前缀串  end+1....  能不能被分解
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }
}
