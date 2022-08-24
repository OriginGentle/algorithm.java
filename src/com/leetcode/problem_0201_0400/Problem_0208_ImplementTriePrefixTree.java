package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/8/24-08:41
 */
public class Problem_0208_ImplementTriePrefixTree {

    class Trie {

        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null)
                return;
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int next = word.charAt(i) - 'a';
                if (node.nexts[next] == null)
                    node.nexts[next] = new TrieNode();
                node = node.nexts[next];
            }
            node.end = true;
        }

        public boolean search(String word) {
            if (word == null)
                return false;
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int next = word.charAt(i) - 'a';
                if (node.nexts[next] == null)
                    return false;

                node = node.nexts[next];
            }
            return node.end;
        }

        public boolean startsWith(String prefix) {
            if (prefix == null)
                return false;
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {
                int next = prefix.charAt(i) - 'a';
                if (node.nexts[next] == null)
                    return false;

                node = node.nexts[next];
            }
            return true;
        }

        class TrieNode {
            public TrieNode[] nexts;
            public boolean end;

            public TrieNode() {
                nexts = new TrieNode[26];
                end = false;
            }
        }
    }
}
