package com.system.C_training.day33;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ycb
 * @since 2021/10/20-14:09
 */
public class Problem_0210_CourseScheduleII {

    public static class Node {
        public int name;
        public int in;
        public ArrayList<Node> nexts;

        public Node(int n) {
            name = n;
            in = 0;
            nexts = new ArrayList<>();
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[i] = i;
        }
        if (prerequisites == null || prerequisites.length == 0) {
            return ans;
        }
        HashMap<Integer, Node> map = new HashMap<>();
        for (int[] arr : prerequisites) {
            int from = arr[1];
            int to = arr[0];
            if (!map.containsKey(to)) {
                map.put(to, new Node(to));
            }
            if (!map.containsKey(from)) {
                map.put(from, new Node(from));
            }
            Node t = map.get(to);
            Node f = map.get(from);
            f.nexts.add(t);
            t.in++;
        }
        int index = 0;
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                ans[index++] = i;
            } else {
                if (map.get(i).in == 0) {
                    zeroInQueue.add(map.get(i));
                }
            }
        }
        int needPrerequisitesNum = map.size();
        int count = 0;
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            ans[index++] = cur.name;
            count++;
            for (Node next : cur.nexts) {
                if (--next.in == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return count == needPrerequisitesNum ? ans : new int[0];
    }
}
