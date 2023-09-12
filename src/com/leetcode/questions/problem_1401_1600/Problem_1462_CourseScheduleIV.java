package com.leetcode.questions.problem_1401_1600;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author ycb
 * @date 2023/9/12-21:02
 */
public class Problem_1462_CourseScheduleIV {

    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        // p:[
        //      {0,1} 想修 1 课程，得先修 0 课程
        //   ]
        for (int[] p : prerequisites) {
            inDegree[p[1]]++;
            graph.get(p[0]).add(p[1]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Integer next : graph.get(cur)) {
                isPre[cur][next] = true;

                for (int i = 0; i < numCourses; i++) {
                    isPre[i][next] |= isPre[i][cur];
                }

                --inDegree[next];
                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            res.add(isPre[q[0]][q[1]]);
        }
        return res;
    }
}
