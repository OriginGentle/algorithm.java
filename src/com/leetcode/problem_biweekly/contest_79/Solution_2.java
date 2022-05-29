package com.leetcode.problem_biweekly.contest_79;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2022/5/28-22:16
 * @desc
 */
public class Solution_2 {

    public String largestWordCount(String[] messages, String[] senders) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < messages.length; i++) {
            String cur = messages[i];
            String[] str = cur.split(" ");
            String sender = senders[i];
            if (!map.containsKey(sender)) {
                map.put(sender, new ArrayList<>());
            }
            for (String s : str) {
                map.get(sender).add(s);
            }
        }
        PriorityQueue<MessageInfo> heap = new PriorityQueue<>(
                (a, b) -> b.count != a.count ? b.count - a.count : b.sender.compareTo(a.sender));
        for (String sender : map.keySet()) {
            ArrayList<String> set = map.get(sender);
            int count = set.size();
            MessageInfo messageInfo = new MessageInfo(sender, count);
            heap.add(messageInfo);
        }
        return heap.peek().sender;
    }

    public static class MessageInfo {
        public String sender;
        public Integer count;

        public MessageInfo(String s, Integer c) {
            sender = s;
            count = c;
        }
    }

}
