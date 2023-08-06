package com.system.D_weekly.code_2023_08_2_week;

import java.util.LinkedList;

/**
 * @author ycb
 * @date 2023/8/6-14:02
 * @desc 设计一个算法：接收一个字符流，并检查这些字符的后缀是否是字符串数组 words 中的一个字符串。
 * 例如，words = ["abc", "xyz"] 且字符流中逐个依次加入 4 个字符 'a'、'x'、'y' 和 'z' ，
 * 你所设计的算法应当可以检测到"axyz" 的后缀 "xyz" 与words 中的字符串 "xyz" 匹配。
 * <p>
 * 按下述要求实现 StreamChecker 类：
 * StreamChecker(String[] words) ：构造函数，用字符串数组words 初始化数据结构。
 * boolean query(char letter)：从字符流中接收一个新字符，
 * 如果字符流中的任一非空后缀能匹配 words 中的某一字符串，返回 true ；否则，返回 false。
 * 测试链接：https://leetcode.cn/problems/stream-of-characters
 */
public class Code03_StreamOfCharacters {

    static class StreamChecker {

        static class Node {
            public boolean end;
            public Node fail;
            public Node[] nexts;

            public Node() {
                end = false;
                fail = null;
                nexts = new Node[26];
            }
        }

        public Node root;

        public Node now;

        private void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = true;
        }

        private void build() {
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cfail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.nexts[i] != null) {
                        cur.nexts[i].fail = root;
                        cfail = cur.fail;
                        while (cfail != null) {
                            if (cfail.nexts[i] != null) {
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public StreamChecker(String[] words) {
            root = new Node();
            for (String w : words) {
                insert(w);
            }
            build();
            now = root;
        }

        public boolean query(char letter) {
            int index = 0;
            index = letter - 'a';
            while (now.nexts[index] == null && now != root) {
                now = now.fail;
            }
            now = now.nexts[index] != null ? now.nexts[index] : root;
            Node follow = now;
            while (follow != root) {
                if (follow.end) {
                    return true;
                }
                follow = follow.fail;
            }
            return false;
        }

    }
}
