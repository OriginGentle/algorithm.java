package com.system.D_weekly.code_2021_12_5_week;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2021/12/30-9:52
 * @description 来自hulu
 * 有n个人，m个任务，任务之间有依赖记录在int[][] depends里
 * 比如: depends[i] = [a, b]，表示a任务依赖b任务的完成
 * 其中 0 <= a < m，0 <= b < m
 * 1个人1天可以完成1个任务，每个人都会选当前能做任务里，标号最小的任务
 * 一个任务所依赖的任务都完成了，该任务才能开始做
 * 返回n个人做完m个任务，需要几天
 */
public class Code02_DoAllJobs {

    // 拓扑排序
    // 利用小根堆的维持依赖数据的传导性
    public static int days(int n, int m, int[][] depends) {
        if (n <= 0) {
            return -1;
        }
        if (m <= 0) {
            return 0;
        }
        int[][] nexts = nexts(depends, m);
        int[] inDegree = inDegree(nexts, m);
        // 工人队列
        PriorityQueue<Integer> workers = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            workers.add(0);
        }
        // zeroIn:放着工作，放着可以开始做的工作，不能做的任务，不在其中
        // 小根堆：标号小的任务，一定要先做
        PriorityQueue<Integer> zeroIn = new PriorityQueue<Integer>();
        for (int i = 0; i < m; i++) {
            if (inDegree[i] == 0) {
                zeroIn.add(i);
            }
        }
        // start[i] : i之前必须完成的任务，占了几天，导致i任务只能从哪天开始
        int[] start = new int[m];
        int finishAll = 0, done = 0;
        while (!zeroIn.isEmpty()) {
            // 当前可以做的任务，标号最小的任务
            int job = zeroIn.poll();
            // 当前可用工人里，最早醒的
            int wake = workers.poll();
            // 工作什么时候完成？
            // (工人醒来，开工时间)最晚的! + 1
            int finish = Math.max(start[job], wake) + 1;
            finishAll = Math.max(finishAll, finish);
            done++;
            // 消除影响
            for (int next : nexts[job]) {
                start[next] = Math.max(start[next], finish);
                if (--inDegree[next] == 0) {
                    zeroIn.add(next);
                }
            }
            workers.add(finish);
        }
        return done == m ? finishAll : -1;
    }

    // depends[i] = {a, b}，表示a任务依赖b任务的完成
    public static int[][] nexts(int[][] depends, int m) {
        Arrays.sort(depends, (a, b) -> a[1] - b[1]);
        int n = depends.length;
        int[][] nexts = new int[m][0];
        if (n == 0) { // 任务之间没有依赖性
            return nexts;
        }
        int size = 1;
        for (int i = 1; i < n; i++) {
            // depends[i] : {a,b} a依赖b
            // depends[i] : {a,c} a依赖c
            if (depends[i - 1][1] != depends[i][1]) {
                int from = depends[i - 1][1];
                nexts[from] = new int[size];
                for (int k = 0, j = i - size; k < size; k++, j++) {
                    nexts[from][k] = depends[j][0];
                }
                size = 1;
            } else {
                size++;
            }
        }
        int from = depends[n - 1][1];
        nexts[from] = new int[size];
        for (int k = 0, j = n - size; k < size; k++, j++) {
            nexts[from][k] = depends[j][0];
        }
        return nexts;
    }

    public static int[] inDegree(int[][] nexts, int m) {
        int[] inDegree = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < nexts[i].length; j++) {
                inDegree[nexts[i][j]]++;
            }
        }
        return inDegree;
    }

    public static void main(String[] args) {
        // 2 -> 5 -> 6
        //           |
        //           v
        // 1 -> 4 -> 7
        //      ^
        //      |
        // 0 -> 3
        // 两个人
        // {1，2} 工人队列
        // 0 : 干0号工作 ，1
        // 0 : 干1号工作 ，1
        // 1 : 干2号工作，2
        int[][] d = {
                {3, 0},
                {4, 1},
                {5, 2},
                {4, 3},
                {6, 5},
                {7, 4},
                {7, 6}
        };
        System.out.println(days(3, 8, d));
        System.out.println(days(2, 8, d));
    }
}
