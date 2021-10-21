package com.training.day33;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ycb
 * @since 2021/10/20-14:09
 */
public class Problem_0207_CourseSchedule {

    // name是课程的编号
    // in是课程的入度
    // nexts是下一层节点
    public static class Course {
        public int name;
        public int in;
        public ArrayList<Course> nexts;

        public Course(int n) {
            name = n;
            in = 0;
            nexts = new ArrayList<>();
        }
    }

    public static boolean canFinish1(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        HashMap<Integer, Course> nodes = new HashMap<>();
        for (int[] arr : prerequisites) {
            int to = arr[0];
            // from --> to
            // [1,0] : 学习课程 1 之前，你需要完成课程 0
            int from = arr[1];
            if (!nodes.containsKey(to)) {
                nodes.put(to, new Course(to));
            }
            if (!nodes.containsKey(from)) {
                nodes.put(from, new Course(from));
            }
            Course t = nodes.get(to);
            Course f = nodes.get(from);
            f.nexts.add(t);
            t.in++;
        }
        int needPrerequisitesNum = nodes.size();
        Queue<Course> zeroInQueue = new LinkedList<>();
        for (Course node : nodes.values()) {
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        int count = 0;
        while (!zeroInQueue.isEmpty()) {
            Course cur = zeroInQueue.poll();
            count++;
            for (Course next : cur.nexts) {
                if (--next.in == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return count == needPrerequisitesNum;
    }

    /*
    ====================================================================================================================
     */

    public static boolean canFinish2(int courses, int[][] relation) {
        if (relation == null || relation.length == 0) {
            return true;
        }
        // 3 : 0,2,4
        // nexts[3] --> {0,2,4}
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i < courses; i++) {
            nexts.add(new ArrayList<>());
        }
        // 3 入度 1  --> in[3] == 1
        int[] in = new int[courses];
        for (int[] arr : relation) {
            // [1,0] : 学习课程 1 之前，你需要完成课程 0
            // arr[1]:from   arr[0]:to
            nexts.get(arr[1]).add(arr[0]);
            in[arr[0]]++;
        }
        // 队列结构
        int[] zero = new int[courses];
        // 该队列有效范围是[l,r)
        // 新来的数，放哪？r位置，r++
        // 出队列的数，从哪拿？l位置，l++
        // l == r 队列无元素  l < r 队列有元素
        int l = 0, r = 0;
        for (int i = 0; i < courses; i++) {
            if (in[i] == 0) {
                zero[r++] = i;
            }
        }
        int count = 0;
        while (l != r) {
            count++;
            for (int next : nexts.get(zero[l++])) {
                if (--in[next] == 0) {
                    zero[r++] = next;
                }
            }
        }
        return count == nexts.size();
    }
}
