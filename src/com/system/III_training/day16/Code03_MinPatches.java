package com.system.III_training.day16;

/**
 * @author ycb
 * @date 2021/9/12-11:14
 * @description https://leetcode.com/problems/patching-array/
 */
public class Code03_MinPatches {

    // arr有序，且正数  1~aim
    public static int minPatches(int[] arr, int aim) {
        int patches = 0;
        long range = 0;
//        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            // 1 ~ arr[i]-1 范围被搞定！
            while (arr[i] - 1 > range) {
                range += range + 1;
                patches++;
                if (range >= aim) {
                    return patches;
                }
            }
            range += arr[i];
            if (range >= aim) {
                return patches;
            }
        }
        while (aim > range) {
            range += range + 1;
            patches++;
        }
        return patches;
    }
}
