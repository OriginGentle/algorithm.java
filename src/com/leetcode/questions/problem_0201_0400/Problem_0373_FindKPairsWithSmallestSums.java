package com.leetcode.questions.problem_0201_0400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @since 2022/1/14-8:27
 */
public class Problem_0373_FindKPairsWithSmallestSums {

    public static List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        // 堆：保存两个数组的下标位置
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]));
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            heap.add(new int[]{i, 0});
        }
        while (k-- > 0 && !heap.isEmpty()) {
            int[] cur = heap.poll();
            ans.add(Arrays.asList(nums1[cur[0]], nums2[cur[1]]));
            if (++cur[1] < nums2.length) {
                heap.add(cur);
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    // 二分技巧
    public static List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        // 二分查找第k小的数对和的大小
        int left = nums1[0] + nums2[0];
        int right = nums1[m - 1] + nums2[n - 1];
        int pairSum = right;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long cnt = 0;
            int start = 0;
            int end = n - 1;
            while (start < m && end >= 0) {
                if (nums1[start] + nums2[end] > mid) {
                    end--;
                } else {
                    cnt += end + 1;
                    start++;
                }
            }
            if (cnt < k) {
                left = mid + 1;
            } else {
                pairSum = mid;
                right = mid - 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        int pos = n - 1;
        // 找到小于目标值,pairSum 的数对
        for (int i = 0; i < m; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] >= pairSum) {
                pos--;
            }
            for (int j = 0; j <= pos && k > 0; j++, k--) {
                ans.add(Arrays.asList(nums1[i], nums2[j]));
            }
        }
        // 找到等于目标值 pairSum 的数对
        pos = n - 1;
        for (int i = 0; i < m && k > 0; i++) {
            while (pos >= 0 && nums1[i] + nums2[pos] > pairSum) {
                pos--;
            }
            for (int j = i; k > 0 && j >= 0 && nums1[j] + nums2[pos] == pairSum; j--, k--) {
                ans.add(Arrays.asList(nums1[j], nums2[pos]));
            }
        }
        return ans;
    }
}
