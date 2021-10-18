package com.training.day31;

import java.util.*;

/**
 * @author ycb
 * @since 2021/10/17-21:23
 */
public class Problem_0127_WordLadder {

    // start，出发的单词
    // to, 目标单位
    // list, 列表
    // to 一定属于list，而start未必
    // 返回变换的最短路径长度
    // 从左往右进行扩展
    public static int ladderLength1(String start, String to, List<String> list) {
        list.add(start);
        // key: 列表中每一个单词
        // value: key这个单词，有哪些邻居!
        HashMap<String, List<String>> nexts = getNexts(list);
        // 距离表
        HashMap<String, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 1);
        // 记录进入队列的单词
        HashSet<String> set = new HashSet<>();
        set.add(start);
        // 借用队列完成宽度优先遍历
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            Integer distance = distanceMap.get(cur);
            for (String next : nexts.get(cur)) {
                if (next.equals(to)) {
                    return distance + 1;
                }
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                    distanceMap.put(next, distance + 1);
                }
            }
        }
        return 0;
    }

    public static HashMap<String, List<String>> getNexts(List<String> words) {
        HashSet<String> dict = new HashSet<>(words);
        HashMap<String, List<String>> nexts = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            nexts.put(words.get(i), getNext(words.get(i), dict));
        }
        return nexts;
    }

    // 应该根据具体数据状况决定用什么来找邻居
    // 1)如果字符串长度比较短，字符串数量比较多，以下方法适合
    // 2)如果字符串长度比较长，字符串数量比较少，以下方法不适合
    public static List<String> getNext(String word, HashSet<String> dict) {
        List<String> res = new ArrayList<>();
        char[] str = word.toCharArray();
        for (int i = 0; i < str.length; i++) {
            for (char cur = 'a'; cur <= 'z'; cur++) {
                if (cur != str[i]) {
                    char tmp = str[i];
                    str[i] = cur;
                    if (dict.contains(String.valueOf(str))) {
                        res.add(String.valueOf(str));
                    }
                    str[i] = tmp;
                }
            }
        }
        return res;
    }

    /*
    ====================================================================================================================
     */

    // 两边从中间扩展
    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        HashSet<String> startSet = new HashSet<>();
        HashSet<String> endSet = new HashSet<>();
        HashSet<String> visit = new HashSet<>();
        startSet.add(beginWord);
        endSet.add(endWord);
        for (int len = 2; !startSet.isEmpty(); len++) {
            HashSet<String> nextSet = new HashSet<>();
            for (String w : startSet) {
                for (int j = 0; j < w.length(); j++) {
                    char[] ch = w.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != ch[j]) {
                            ch[j] = c;
                            String next = String.valueOf(ch);
                            if (endSet.contains(next)) {
                                return len;
                            }
                            if (dict.contains(next) && !visit.contains(next)) {
                                visit.add(next);
                                nextSet.add(next);
                            }
                        }
                    }
                }
            }
            // startSet(小) -> nextSet(某个大小)   和 endSet大小来比
            startSet = (nextSet.size() < endSet.size()) ? nextSet : endSet;
            endSet = (startSet == nextSet) ? endSet : nextSet;
        }
        return 0;
    }
}
