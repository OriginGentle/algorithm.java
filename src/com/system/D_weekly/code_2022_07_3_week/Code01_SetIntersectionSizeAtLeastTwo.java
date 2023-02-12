package com.system.D_weekly.code_2022_07_3_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/7/23-13:17
 * @desc https://leetcode.cn/problems/set-intersection-size-at-least-two/
 */
public class Code01_SetIntersectionSizeAtLeastTwo {

    public int intersectionSizeTwo(int[][] arr) {
        Arrays.sort(arr,
                (a, b) -> a[1] != b[1] ? (a[1] - b[1]) : (b[0] - a[0]));
        int n = arr.length;
        int pos = arr[0][1], pre = pos - 1;
        int ans = 2;
        for (int i = 1; i < n; i++) {
            if (arr[i][0] > pre) {
                if (arr[i][0] > pos) {
                    pre = arr[i][1] - 1;
                    ans += 2;
                } else {
                    pre = pos;
                    ans += 1;
                }
                pos = arr[i][1];
            }
        }
        return ans;
    }
}
