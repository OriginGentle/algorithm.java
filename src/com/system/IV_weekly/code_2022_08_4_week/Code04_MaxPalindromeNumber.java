package com.system.IV_weekly.code_2022_08_4_week;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2022/8/27-13:33
 * @desc 来自微软
 * 给定一个字符串s，只含有0~9这些字符
 * 你可以使用来自s中的数字，目的是拼出一个最大的回文数
 * 使用数字的个数，不能超过s里含有的个数
 * 比如 :
 * 39878，能拼出的最大回文数是 : 898
 * 00900，能拼出的最大回文数是 : 9
 * 54321，能拼出的最大回文数是 : 5
 * 最终的结果以字符串形式返回
 * str的长度为N，1 <= N <= 100000
 * <p>
 * https://leetcode.cn/problems/largest-palindromic-number/
 */
public class Code04_MaxPalindromeNumber {

    public static String largestPalindromic(String s) {
        if (s == null || s.length() == 0)
            return s;

        int n = s.length();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Record> heap = new PriorityQueue<>(new RecordComparator());
        for (int key : map.keySet()) {
            heap.add(new Record(key, map.get(key)));
        }

        Record top = heap.poll();
        if (top.times == 1)
            return String.valueOf(top.number);

        else if (top.number == 0)
            return String.valueOf(heap.isEmpty() ? 0 : heap.peek().number);

        else {
            StringBuilder left = new StringBuilder();
            left.append(top.number);
            top.times -= 2;
            if (top.times > 0)
                heap.add(top);

            while (!heap.isEmpty() && heap.peek().times > 1) {
                top = heap.poll();
                left.append(top.number);
                top.times -= 2;
                if (top.times > 0)
                    heap.add(top);
            }

            StringBuilder ans = new StringBuilder();
            ans.append(left);

            if (!heap.isEmpty()) {
                ans.append(heap.peek().number);
            }

            ans.append(left.reverse());

            return ans.toString();
        }
    }

    public static class Record {

        public int number;
        public int times;

        public Record(int n, int t) {
            number = n;
            times = t;
        }
    }

    public static class RecordComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            if (o1.times == 1 && o2.times > 1)
                return 1;

            if (o1.times > 1 && o2.times == 1)
                return -1;

            return o2.number - o1.number;
        }
    }
}
