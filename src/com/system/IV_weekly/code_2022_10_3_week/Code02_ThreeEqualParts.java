package com.system.IV_weekly.code_2022_10_3_week;

/**
 * @author ycb
 * @date 2022/10/23-13:10
 * @desc https://leetcode.cn/problems/three-equal-parts/
 * 给定一个由 0 和 1 组成的数组 arr ，将数组分成  3 个非空的部分
 * 使得所有这些部分表示相同的二进制值。
 * 如果可以做到，请返回任何 [i, j]，其中 i+1 < j，这样一来
 * arr[0], arr[1], ..., arr[i] 为第一部分
 * arr[i + 1], arr[i + 2], ..., arr[j - 1] 为第二部分
 * arr[j], arr[j + 1], ..., arr[arr.length - 1] 为第三部分
 * 这三个部分所表示的二进制值相等
 * 如果无法做到，就返回 [-1, -1]
 * 注意，在考虑每个部分所表示的二进制时，应当将其看作一个整体
 * 例如，[1,1,0] 表示十进制中的 6，而不会是 3。此外，前导零也是被允许的
 * 所以 [0,1,1] 和 [1,1] 表示相同的值。
 */
public class Code02_ThreeEqualParts {

    public static int[] threeEqualParts(int[] arr) {
        int ones = 0;
        for (int num : arr) {
            ones += num == 1 ? 1 : 0;
        }

        if (ones % 3 != 0)
            return new int[]{-1, -1};

        int n = arr.length;
        if (ones == 0)
            return new int[]{0, n - 1};

        int part = ones / 3;
        int start1 = -1, start2 = -1, start3 = -1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                cnt++;

                if (start1 == -1 && cnt == 1)
                    start1 = i;

                if (start2 == -1 && cnt == part + 1)
                    start2 = i;

                if (start3 == -1 && cnt == part * 2 + 1)
                    start3 = i;
            }
        }

        while (start3 < n) {
            if (arr[start1] != arr[start2] || arr[start1] != arr[start3]) {
                // 一旦不一样，肯定没方案了
                return new int[]{-1, -1};
            }
            start1++;
            start2++;
            start3++;
        }

        return new int[]{start1 - 1, start2};
    }
}
