package com.system.C_training.day42;

/**
 * @author ycb
 * @since 2021/11/4-11:22
 */
public class Problem_0335_SelfCrossing {

    public static boolean isSelfCrossing(int[] x) {
        if (x == null || x.length < 4) {
            return false;
        }
        if ((x.length > 3 && x[2] <= x[0] && x[3] >= x[1])
                || (x.length > 4 && ((x[3] <= x[1] && x[4] >= x[2]) || (x[3] == x[1] && x[0] + x[4] >= x[2])))) {
            return true;
        }
        for (int i = 5; i < x.length; i++) {
            if (x[i - 1] <= x[i - 3] && ((x[i] >= x[i - 2]) ||
                    (x[i - 2] >= x[i - 4] && x[i - 5] + x[i - 1] >= x[i - 3] && x[i - 4] + x[i] >= x[i - 2]))) {
                return true;
            }
        }
        return false;
    }
}
