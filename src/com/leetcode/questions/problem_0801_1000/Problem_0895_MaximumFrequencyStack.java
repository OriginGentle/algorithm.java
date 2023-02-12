package com.leetcode.questions.problem_0801_1000;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/11/30-00:21
 */
public class Problem_0895_MaximumFrequencyStack {

    class FreqStack {

        public Map<Integer, Integer> cmtMap;
        public Map<Integer, Deque<Integer>> freqMap;
        public int maxFreq;

        public FreqStack() {
            cmtMap = new HashMap<>();
            freqMap = new HashMap<>();
            maxFreq = Integer.MIN_VALUE;
        }

        public void push(int val) {
            cmtMap.put(val, cmtMap.getOrDefault(val, 0) + 1);
            freqMap.putIfAbsent(cmtMap.get(val), new ArrayDeque<>());
            freqMap.get(cmtMap.get(val)).push(val);
            maxFreq = Math.max(maxFreq, cmtMap.get(val));
        }

        public int pop() {
            int val = freqMap.get(maxFreq).peek();
            cmtMap.put(val, cmtMap.get(val) - 1);
            freqMap.get(maxFreq).pop();
            if (freqMap.get(maxFreq).isEmpty())
                maxFreq--;
            return val;
        }
    }
}
