package com.system.D_weekly.code_2023_02_4_week;

import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2023/2/23-08:24
 * @desc 一所学校里有一些班级，每个班级里有一些学生，现在每个班都会进行一场期末考试
 * 给你一个二维数组 classes ，其中 classes[i] = [passi, totali]
 * 表示你提前知道了第 i 个班级总共有 totali 个学生，其中只有 passi 个学生可以通过考试
 * 给你一个整数 extraStudents ，表示额外有 extraStudents 个聪明的学生
 * 他们 一定 能通过任何班级的期末考
 * 你需要给这 extraStudents 个学生每人都安排一个班级
 * 使得 所有 班级的 平均 通过率 最大 。
 * 一个班级的 通过率 等于这个班级通过考试的学生人数除以这个班级的总人数
 * 平均通过率 是所有班级的通过率之和除以班级数目。
 * 请你返回在安排这 extraStudents 个学生去对应班级后的 最大 平均通过率
 * 与标准答案误差范围在 10-5 以内的结果都会视为正确结果。
 * 测试链接 : https://leetcode.cn/problems/maximum-average-pass-ratio/
 */
public class Code01_MaximumAveragePassRatio {

    public static double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<Pari> heap = new PriorityQueue<>(
                (a, b) -> a.benefit() - b.benefit() < 0 ? 1 : -1);
        for (int[] c : classes) {
            heap.add(new Pari(c[0], c[1]));
        }
        Pari cur = null;
        for (int i = 0; i < extraStudents; i++) {
            cur = heap.poll();
            cur.pass++;
            cur.total++;
            heap.add(cur);
        }
        double all = 0;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            all += cur.pass / cur.total;
        }
        return all / classes.length;
    }

    public static class Pari {
        public double pass;
        public double total;

        public Pari(int p, int t) {
            pass = p;
            total = t;
        }

        public double benefit() {
            return (pass + 1) / (total + 1) - pass / total;
        }
    }
}
