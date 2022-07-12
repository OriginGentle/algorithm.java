package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/7/11-16:16
 */
public class Problem_0676_ImplementMagicDictionary {

    class MagicDictionary {

        public TrieTree trieTree;

        public MagicDictionary() {
            trieTree = new TrieTree();
        }

        public void buildDict(String[] dictionary) {
            for (String s : dictionary) {
                trieTree.addWord(s);
            }
        }

        public boolean search(String s) {
            return trieTree.patten(s.toCharArray(), trieTree.root, 0, false);
        }
    }

    public class Node {
        public boolean isEnd;
        public Node[] nexts;

        public Node() {
            isEnd = false;
            nexts = new Node[26];
        }
    }

    public class TrieTree {
        public Node root;

        public TrieTree() {
            root = new Node();
        }

        public void addWord(String s) {
            if (s == null || s.length() == 0) {
                return;
            }
            Node cur = root;
            for (char ch : s.toCharArray()) {
                int index = ch - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.isEnd = true;
        }

        public boolean patten(char[] str, Node cur, int pos, boolean hasModify) {
            if (pos == str.length) {
                return hasModify && cur.isEnd;
            }
            int index = str[pos] - 'a';
            if (cur.nexts[index] != null) {
                if (patten(str, cur.nexts[index], pos + 1, hasModify)) {
                    return true;
                }
            }
            if (!hasModify) {
                for (int i = 0; i < 26; i++) {
                    if (i != index && cur.nexts[i] != null) {
                        if (patten(str, cur.nexts[i], pos + 1, true)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
}
