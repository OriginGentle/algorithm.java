package com.leetcode.questions.problem_0601_0800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/7/14-11:36
 */
public class Problem_0745_PrefixAndSuffixSearch {

    class WordFilter {

        private final TrieNode pt = new TrieNode();
        private final TrieNode st = new TrieNode();

        public WordFilter(String[] words) {
            build(pt, words, true);
            build(st, words, false);
        }

        private void build(TrieNode root, String[] words, boolean isPre) {
            for (int i = 0; i < words.length; i++) {
                TrieNode cur = root;
                int len = words[i].length();

                for (int j = isPre ? 0 : len - 1; j >= 0 && j < len; j += isPre ? 1 : -1) {
                    int index = words[i].charAt(j) - 'a';
                    if (cur.nexts[index] == null)
                        cur.nexts[index] = new TrieNode();
                    cur = cur.nexts[index];
                    cur.indexs.add(i);
                }
            }
        }

        public int f(String pre, String suf) {
            List<Integer> preRes = query(pt, pre, true);
            List<Integer> sufRes = query(st, suf, false);

            int i = preRes.size() - 1;
            int j = sufRes.size() - 1;

            while (i >= 0 && j >= 0) {
                int p = preRes.get(i);
                int s = sufRes.get(j);

                if (p == s)
                    return p;
                else if (p > s)
                    i--;
                else
                    j--;
            }
            return -1;
        }

        private List<Integer> query(TrieNode root, String str, boolean isPre) {
            TrieNode cur = root;
            int len = str.length();
            for (int i = isPre ? 0 : len - 1; i >= 0 && i < len; i += isPre ? 1 : -1) {
                int index = str.charAt(i) - 'a';
                if (cur.nexts[index] == null)
                    return new ArrayList<>();
                cur = cur.nexts[index];
            }
            return cur.indexs;
        }
    }

    class TrieNode {
        public TrieNode[] nexts;
        List<Integer> indexs;
        public boolean isEnd;

        public TrieNode() {
            nexts = new TrieNode[26];
            indexs = new ArrayList<>();
            isEnd = false;
        }
    }
}
