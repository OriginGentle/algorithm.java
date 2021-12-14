package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/12/14-8:51
 */
public class Problem_0057_InsertInterval {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<>();
        for (int[] arr : intervals) {
            // 在插入区间的右侧且无交集
            if (arr[0] > right) {
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(arr);
            } else if (arr[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(arr);
            } else {
                // 与插入区间有交集，计算并集
                left = Math.min(left, arr[0]);
                right = Math.max(right, arr[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
