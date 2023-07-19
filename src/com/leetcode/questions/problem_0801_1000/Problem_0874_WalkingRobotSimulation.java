package com.leetcode.questions.problem_0801_1000;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @date 2023/7/19-13:58
 */
public class Problem_0874_WalkingRobotSimulation {

    // 坐标轴方向
    public static int[][] nexts = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static int robotSim(int[] commands, int[][] obstacles) {
        int px = 0, py = 0, d = 1;
        Set<Integer> set = new HashSet<>();
        for (int[] ob : obstacles) {
            set.add(ob[0] * 60001 + ob[1]);
        }

        int res = 0;
        for (int cmd : commands) {
            if (cmd < 0) {
                d += cmd == -1 ? 1 : -1;
                d %= 4;
                if (d < 0) {
                    d += 4;
                }

            } else {

                for (int i = 0; i < cmd; i++) {
                    if (set.contains(
                            (px + nexts[d][0]) * 60001 + (py + nexts[d][1])
                    )) {
                        break;
                    }

                    px += nexts[d][0];
                    py += nexts[d][1];
                    res = Math.max(res, px * px + py * py);
                }
            }
        }
        return res;
    }
}
