package com.leetcode.sword_finger_offer;

/**
 * @Author ycb
 * @Date 2021/7/16-8:39
 */
public class Code53_I_Search {

    // 遍历一遍数组
    public int search1(int[] nums, int target) {
        int ans = 0;
        for (int num : nums) {
            if ((num ^ target) == 0) {
                ans++;
            }
        }
        return ans;
    }

    // 二分法
    public static int search2(int[] nums, int target) {
        int N = nums.length;
        int left = 0;

        return 0;
    }

    public static void findLeftIndex(int[] nums, int target) {
        int L = 0;
        int R = nums.length;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (nums[mid] > target) {
                R = mid - 1;
            } else if (nums[mid] < target) {
                L = mid + 1;
            }
        }
    }

    public static void main(String[] args) {
        int L = 4;
        int R = 8;
        int ans = L + ((R - L) >> 1);
        System.out.println(ans);
        System.out.println((R + L) / 2);
    }

}
