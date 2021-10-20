package com.leetcode;

/**
 * @author ycb
 * @since 2021/10/19-15:44
 */
public class Problem_0221_WordDictionary {

    private Node root;

    public Problem_0221_WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        if (word == null) {
            return;
        }
        Node cur = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new Node();
            }
            cur = cur.nexts[index];
        }
        cur.end = true;
    }

    public boolean search(String word) {
        return dfs(word.toCharArray(), 0, root);
    }

    private boolean dfs(char[] str, int index, Node node) {
        if (index == str.length) {
            return node.end;
        }
        char ch = str[index];
        if (Character.isLetter(ch)) {
            if (node.nexts[ch - 'a'] != null && dfs(str, index + 1, node.nexts[ch - 'a'])) {
                return true;
            }
        } else {
            for (int i = 0; i < 26; i++) {
                if (node.nexts[i] != null && dfs(str, index + 1, node.nexts[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    public static class Node {
        public boolean end;
        public Node[] nexts;

        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }

}
