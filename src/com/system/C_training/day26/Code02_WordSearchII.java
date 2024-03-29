package com.system.C_training.day26;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ycb
 * @date 2021/10/10-18:01
 * @description https://leetcode.com/problems/word-search-ii/
 */
public class Code02_WordSearchII {

    // 前缀树节点
    public static class TrieNode {
        public TrieNode[] nexts;
        public int pass;
        public boolean end;

        public TrieNode() {
            nexts = new TrieNode[26];
            pass = 0;
            end = false;
        }
    }

    public static void fillWords(TrieNode head, String word) {
        head.pass++;
        char[] str = word.toCharArray();
        int index = 0;
        TrieNode node = head;
        for (int i = 0; i < str.length; i++) {
            index = str[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end = true;
    }

    public static String generatePath(List<Character> path) {
        char[] str = new char[path.size()];
        int index = 0;
        for (Character ch : path) {
            str[index++] = ch;
        }
        return String.valueOf(str);
    }

    public static List<String> findWords(char[][] bord, String[] words) {
        TrieNode head = new TrieNode();
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            if (!set.contains(word)) {
                fillWords(head, word);
                set.add(word);
            }
        }
        // 收集的答案
        List<String> ans = new ArrayList<>();
        // 沿途走过的字符，收集起来，存在path里
        LinkedList<Character> path = new LinkedList<>();
        for (int row = 0; row < bord.length; row++) {
            for (int col = 0; col < bord[0].length; col++) {
                // 枚举在board中的所有位置
                // 每一个位置出发的情况下，答案都收集
                process(bord, row, col, path, head, ans);
            }
        }
        return ans;
    }

    // 从board[row][col]位置的字符出发，
    // 之前的路径上，走过的字符，记录在path里
    // cur还没有登上，有待检查能不能登上去的前缀树的节点
    // 如果找到words中的某个str，就记录在 res里
    // 返回值，从row,col 出发，一共找到了多少个str
    public static int process(char[][] board, int row, int col, LinkedList<Character> path, TrieNode cur, List<String> res) {
        char cha = board[row][col];
        if (cha == 0) { // 这个row col位置是之前走过的位置
            return 0;
        }
        // (row,col) 不是回头路 cha 有效
        int index = cha - 'a';
        // 优化点
        // 如果没路，或者这条路上最终的字符串之前加入过结果里
        if (cur.nexts[index] == null || cur.nexts[index].pass == 0) {
            return 0;
        }
        // 没有走回头路且能登上去
        cur = cur.nexts[index];
        // 当前位置的字符加到路径里去
        path.addLast(cha);
        // 从row和col位置出发，后续一共搞定了多少答案
        int fix = 0;
        // 来到row col位置，如果决定不往后走了。是不是已经搞定了某个字符串了
        if (cur.end) {
            res.add(generatePath(path));
            cur.end = false;
            fix++;
        }
        // 往上、下、左、右，四个方向尝试
        board[row][col] = 0;
        if (row > 0) {
            fix += process(board, row - 1, col, path, cur, res);
        }
        if (row < board.length - 1) {
            fix += process(board, row + 1, col, path, cur, res);
        }
        if (col > 0) {
            fix += process(board, row, col - 1, path, cur, res);
        }
        if (col < board[0].length - 1) {
            fix += process(board, row, col + 1, path, cur, res);
        }
        board[row][col] = cha;
        path.pollLast();
        cur.pass -= fix;
        return fix;
    }
}
