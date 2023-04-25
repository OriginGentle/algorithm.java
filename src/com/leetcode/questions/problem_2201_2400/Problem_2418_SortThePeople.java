package com.leetcode.questions.problem_2201_2400;

import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2023/4/25-08:45
 */
public class Problem_2418_SortThePeople {

    public static String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        PriorityQueue<People> heap = new PriorityQueue<>((a, b) -> b.height - a.height);
        for (int i = 0; i < n; i++) {
            heap.add(new People(names[i], heights[i]));
        }

        int i = 0;
        while (!heap.isEmpty()) {
            names[i++] = heap.poll().name;
        }
        return names;
    }

    public static class People {
        public String name;
        public int height;

        public People(String n, int h) {
            this.name = n;
            this.height = h;
        }
    }
}
