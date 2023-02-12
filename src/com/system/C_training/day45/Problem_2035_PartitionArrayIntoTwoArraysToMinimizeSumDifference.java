package com.system.C_training.day45;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author ycb
 * @since 2021/11/7-14:35
 */
public class Problem_2035_PartitionArrayIntoTwoArraysToMinimizeSumDifference {

    public static int minimumDifference(int[] nums) {
        int size = nums.length;
        int half = size >> 1;
        HashMap<Integer, TreeSet<Integer>> lMap = new HashMap<>();
        process(nums, 0, half, 0, 0, lMap);
        HashMap<Integer, TreeSet<Integer>> rMap = new HashMap<>();
        process(nums, half, size, 0, 0, rMap);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int ans = Integer.MAX_VALUE;
        for (int leftNum : lMap.keySet()) {
            for (int leftSum : lMap.get(leftNum)) {
                Integer rightSum = rMap.get(half - leftNum).floor((sum >> 1) - leftSum);
                if (rightSum != null) {
                    int pickSum = leftSum + rightSum;
                    int restSum = sum - pickSum;
                    ans = Math.min(ans, restSum - pickSum);
                }
            }
        }
        return ans;
    }

    // arr -> 8   0 1 2 3      4 5 6 7
    // process(arr, 0, 4)  [0,4)
    // process(arr, 4, 8)  [4,8)
    // arr[index....end-1]这个范围上，去做选择
    // pick挑了几个数！
    // sum挑的这些数，累加和是多少！
    // map记录结果
    // HashMap<Integer, TreeSet<Integer>> map
    // key -> 挑了几个数，比如挑了3个数，但是形成累加和可能多个！
    // value -> 有序表，都记下来！
    // 整个过程，纯暴力！2^15 -> 3万多，纯暴力跑完，依然很快！
    public static void process(int[] arr, int index, int end, int pick, int sum, HashMap<Integer, TreeSet<Integer>> map) {
        if (index == end) {
            if (!map.containsKey(pick)) {
                map.put(pick, new TreeSet<>());
            }
            map.get(pick).add(sum);
        } else {
            process(arr, index + 1, end, pick, sum, map);
            process(arr, index + 1, end, pick + 1, sum + arr[index], map);
        }
    }
}
