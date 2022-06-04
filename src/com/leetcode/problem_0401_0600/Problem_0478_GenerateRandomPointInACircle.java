package com.leetcode.problem_0401_0600;

import java.util.Random;

/**
 * @author ycb
 * @date 2022/6/5-00:44
 */
public class Problem_0478_GenerateRandomPointInACircle {

    class Solution {
        public Random random;
        public double x;
        public double y;
        public double r;

        public Solution(double radius, double x_center, double y_center) {
            random = new Random();
            x = x_center;
            y = y_center;
            r = radius;
        }

        public double[] randPoint() {
            while (true) {
                double nx = random.nextDouble() * (2 * r) - r;
                double ny = random.nextDouble() * (2 * r) - r;
                if (nx * nx + ny * ny <= r * r) {
                    return new double[]{x + nx, y + ny};
                }
            }
        }
    }
}
