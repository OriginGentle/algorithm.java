package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/3/17-8:07
 */
public class Problem_0720_LongestWordInDictionary {

    public static String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        TrieTree trieTree = new TrieTree();
        for (String str : words) {
            trieTree.insert(str);
        }
        String ans = "";
        for (String word : words) {
            if (trieTree.search(word)) {
                if (word.length() > ans.length() || (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                    ans = word;
                }
            }
        }
        return ans;
    }

    public static class TrieNode {
        TrieNode[] nexts;
        boolean isEnd;

        public TrieNode() {
            // 只有小写字母
            nexts = new TrieNode[26];
            isEnd = false;
        }
    }

    public static class TrieTree {
        TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < str.length; i++) {
                if (node.nexts[str[i] - 'a'] == null) {
                    node.nexts[str[i] - 'a'] = new TrieNode();
                }
                node = node.nexts[str[i] - 'a'];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            char[] str = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < str.length; i++) {
                int index = str[i] - 'a';
                if (node.nexts[index] == null || !node.nexts[index].isEnd) {
                    return false;
                }
                node = node.nexts[index];
            }
            return node.isEnd;
        }
    }

    public static void main(String[] args) {
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        String ans = longestWord(words);
        System.out.println(ans);
    }
}
