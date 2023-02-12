package com.system.C_training.day51;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @since 2021/11/14-14:06
 */
public class Problem_0630_CourseScheduleIII {

    public static int scheduleCourse(int[][] courses) {
        // courses[i]  = {花费，截止}
        // 根据截止时间从小到大排序
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        // 花费时间的大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;
        for (int[] c : courses) {
            if (time + c[0] <= c[1]) { // 当前时间 + 花费时间 <= 截止时间
                heap.add(c[0]);
                time += c[0];
            } else { // 当前时间 + 花费 > 截止时间的, 只有淘汰掉某课，当前的课才能进来！
                if (!heap.isEmpty() && heap.peek() > c[0]) {
                    heap.add(c[0]);
                    time += c[0] - heap.poll();
                }
            }
        }
        return heap.size();
    }
}
