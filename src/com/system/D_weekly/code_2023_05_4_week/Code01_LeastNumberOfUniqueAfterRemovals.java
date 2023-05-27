package com.system.D_weekly.code_2023_05_4_week;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2023/5/26-13:16
 * @desc 一个智力题，来自美团面试题
 * 给定n个二维坐标，表示在二维平面的n个点，
 * 坐标为double类型，精度最多小数点后两位
 * 希望在二维平面上画一个圆，圈住其中的k个点，其他的n-k个点都要在圆外
 * 返回一个圆心和半径，表示哪个圆可以圈住其中的k个点
 * 坐标和半径都是double类型，最多保留小数点后两位
 * <p>
 * 给你一个整数数组 arr 和一个整数 k
 * 现需要从数组中恰好移除 k 个元素
 * 请找出移除后数组中不同整数的最少数目
 * 测试链接 : https://leetcode.cn/problems/least-number-of-unique-integers-after-k-removals/
 */
public class Code01_LeastNumberOfUniqueAfterRemovals {

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int size = map.size();
        int[] cnts = new int[size];
        int i = 0;
        for (Integer val : map.values()) {
            cnts[i++] = val;
        }

        Arrays.sort(cnts);
        for (i = 0; i < size; i++) {
            k -= cnts[i];
            if (k <= 0) {
                if (k == 0) {
                    i++;
                }
                break;
            }
        }
        return size - i;
    }
}
