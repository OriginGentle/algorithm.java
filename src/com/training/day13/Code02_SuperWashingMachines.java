package com.training.day13;

/**
 * @author ycb
 * @date 2021/9/5-15:30
 * @description https://leetcode.com/problems/super-washing-machines/
 */
public class Code02_SuperWashingMachines {

    public static int findMinMoves(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        // 要求保证所有的洗衣机内的衣服数量都一样
        if (sum % size != 0) {
            return -1;
        }
        // 最终每台洗衣机都要有衣服数量
        int avg = sum / size;
        int ans = 0;
        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int leftRest = leftSum - i * avg;
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
            // 当前洗衣机的衣服过多，需要给左右的洗衣机，但是一次只能给一件
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }

}
