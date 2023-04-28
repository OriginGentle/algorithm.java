package com.system.B_basic.day08;

import java.util.HashMap;

/**
 * @Author ycb
 * @Date 2021/5/2-21:31
 * @Description 前缀树
 */
public class Code01_TrieTree {

    public static class Node1 {
        private int pass;
        private int end;
        private final Node1[] nexts; // 代表路

        public Node1() {
            pass = 0;
            end = 0;
            /*
             0 -> a
             1 -> b
             ..   ..
             25 -> z
             next[i] == null 表示i方向的路不存在
             next[i] != null 表示i方向的路存在
             */
            nexts = new Node1[26]; // 代表 a~z 26个小写字母
        }
    }

    public static class Trie1 {

        public Node1 root;

        public Trie1() {
            root = new Node1();
        }

        // 加入字符串
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray(); //
            Node1 node = root;
            for (char c : str) {
                int path = c - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node1();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        // 查询字符串出现了几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] str = word.toCharArray();
            Node1 node = root;
            for (char c : str) {
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        // 查询某个前缀出现了几次
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] str = pre.toCharArray();
            Node1 node = root;
            for (char c : str) {
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }

        // 删除字符串
        public void delete(String word) {
            if (search(word) != 0) {
                char[] str = word.toCharArray();
                Node1 node = root;
                root.pass--;
                for (char c : str) {
                    int path = c - 'a';
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }

    }

    /*
    ====================================================================================================================
     */

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private final Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            node.pass++;
            for (char ch : chs) {
                if (!node.nexts.containsKey((int) ch)) {
                    node.nexts.put((int) ch, new Node2());
                }
                node = node.nexts.get((int) ch);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node2 node = root;
                node.pass--;
                for (char ch : chs) {
                    if (--node.nexts.get((int) ch).pass == 0) {
                        node.nexts.remove((int) ch);
                        return;
                    }
                    node = node.nexts.get((int) ch);
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            for (char ch : chs) {
                if (!node.nexts.containsKey((int) ch)) {
                    return 0;
                }
                node = node.nexts.get((int) ch);
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node2 node = root;
            for (char ch : chs) {
                if (!node.nexts.containsKey((int) ch)) {
                    return 0;
                }
                node = node.nexts.get((int) ch);
            }
            return node.pass;
        }
    }

    public static class Right {

        private final HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            return box.getOrDefault(word, 0);
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (String s : arr) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(s);
                    trie2.insert(s);
                    right.insert(s);
                } else if (decide < 0.5) {
                    trie1.delete(s);
                    trie2.delete(s);
                    right.delete(s);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(s);
                    int ans2 = trie2.search(s);
                    int ans3 = right.search(s);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(s);
                    int ans2 = trie2.prefixNumber(s);
                    int ans3 = right.prefixNumber(s);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
