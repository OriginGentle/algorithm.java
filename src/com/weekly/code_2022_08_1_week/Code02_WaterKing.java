package com.weekly.code_2022_08_1_week;

/**
 * @author ycb
 * @date 2022/8/5-11:05
 * @desc 超级水王数
 */
public class Code02_WaterKing {

    public static int waterKing(int[] arr) {
        int cand = 0, hp = 0;
        for (int num : arr) {
            if (hp == 0) {
                cand = num;
                hp = 1;
            } else if (cand != num) {
                hp--;
            } else {
                hp++;
            }
        }
        if (hp == 0)
            return -1;
        hp = 0;
        for (int num : arr) {
            if (cand == num)
                hp++;
        }
        return hp > arr.length / 2 ? cand : -1;
    }
}
