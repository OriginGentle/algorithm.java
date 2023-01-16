package com.system.II_basic.day02;

import java.util.HashMap;

/**
 * @Author ycb
 * @Date 2021/3/5-13:15
 * @Description 一个数组中有一种数出现K次，其他数都出现了M次，其中:M > 1,  K < M,找到出现了K次的数。要求，额外空间复杂度O(1)，时间复杂度O(N)
 */
public class Code03_KM {
    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static int onlyKTimes(int[] arr, int k, int m) {
        if (map.size() == 0) {
            mapCreator(map);
        }
        int[] t = new int[32];
        // t[0] 0位置的1出现了几个
        // t[i] i位置的1出现了几个
        for (int num : arr) {
            while (num != 0) {
                int rightOne = num & (-num);
                t[map.get(rightOne)]++;
                num ^= rightOne;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                if (t[i] % m == k) {
                    ans |= (1 << k);
                } else {
                    return -1;
                }
            }
        }
        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }

    public static void mapCreator(HashMap<Integer, Integer> map) {
        int value = 1;
        for (int i = 0; i < 32; i++) {
            map.put(value, i);
            value <<= 1;
        }
    }
}
