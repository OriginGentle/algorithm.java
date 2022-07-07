package com.leetcode.problem_0601_0800;

import java.util.List;

/**
 * @author ycb
 * @date 2022/7/7-11:27
 */
public class Problem_0648_ReplaceWords {

    public static String replaceWords(List<String> dictionary, String sentence) {
        TrieTree trieTree = new TrieTree();
        for (String s : dictionary) {
            trieTree.insert(s);
        }
        StringBuilder sb = new StringBuilder();
        for (String s : sentence.split(" ")) {
            sb.append(trieTree.query(s)).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static class TrieTree {

        public Node root;

        public TrieTree() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            Node node = root;
            for (char c : str) {
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.isEnd = true;
        }

        public String query(String s) {
            Node node = root;
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                if (node.nexts[index] != null && !node.isEnd) {
                    node = node.nexts[index];
                } else if (node.isEnd) {
                    return s.substring(0, i);
                } else {
                    break;
                }
            }
            return s;
        }
    }

    public static class Node {
        public boolean isEnd;
        public Node[] nexts;

        public Node() {
            isEnd = false;
            nexts = new Node[26];
        }
    }
}
