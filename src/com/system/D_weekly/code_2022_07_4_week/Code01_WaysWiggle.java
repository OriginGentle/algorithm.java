package com.system.D_weekly.code_2022_07_4_week;

/**
 * @author ycb
 * @date 2022/7/30-14:54
 * @desc 一个数组如果满足 :
 * 升降升降升降... 或者 降升降升...都是满足的
 * 给定一个数组，
 * 1，看有几种方法能够剔除一个元素，达成上述的要求
 * 2，数组天然符合要求返回0
 * 3，剔除1个元素达成不了要求，返回-1，
 * 比如：
 * 给定[3, 4, 5, 3, 7]，返回3
 * 移除0元素，4 5 3 7 符合
 * 移除1元素，3 5 3 7 符合
 * 移除2元素，3 4 3 7 符合
 * 再比如：给定[1, 2, 3, 4] 返回-1
 * 因为达成不了要求
 */
public class Code01_WaysWiggle {

    public static boolean isWiggle(int[] arr, int removeIndex) {
        boolean ans = true;
        // 升
        boolean request = true;
        for (int i = 1; i < arr.length; i++) {
            if (i == removeIndex) {
                continue;
            }
            if (i - 1 == removeIndex && removeIndex == 0) {
                continue;
            }
            int last = i - 1 == removeIndex ? (i - 2) : (i - 1);
            if (request) {
                if (arr[last] >= arr[i]) {
                    ans = false;
                    break;
                }
            } else {
                if (arr[last] <= arr[i]) {
                    ans = false;
                    break;
                }
            }
            request = !request;
        }
        if (ans) {
            return true;
        }
        ans = true;
        // 降
        request = false;
        for (int i = 1; i < arr.length; i++) {
            if (i == removeIndex) {
                continue;
            }
            if (i - 1 == removeIndex && removeIndex == 0) {
                continue;
            }
            int last = i - 1 == removeIndex ? (i - 2) : (i - 1);
            if (request) {
                if (arr[last] >= arr[i]) {
                    ans = false;
                    break;
                }
            } else {
                if (arr[last] <= arr[i]) {
                    ans = false;
                    break;
                }
            }
            request = !request;
        }
        return ans;
    }

    public static int ways1(int[] arr) {
        if (isWiggle(arr, -1)) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            if (isWiggle(arr, i)) {
                ans++;
            }
        }
        return ans == 0 ? -1 : ans;
    }

    /*
    ====================================================================================================================
     */

    public static int ways2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        boolean[] rightUp = new boolean[n];
        boolean[] rightDown = new boolean[n];
        rightUp[n - 1] = true;
        rightDown[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            rightUp[i] = arr[i] < arr[i + 1] && rightDown[i + 1];
            rightDown[i] = arr[i] > arr[i + 1] && rightUp[i + 1];
        }
        // 数组是否天然符合要求
        if (rightUp[0] || rightDown[0]) {
            return 0;
        }
        int ans = (rightDown[1] || rightUp[1]) ? 1 : 0;
        boolean leftUp = true, leftDown = true;
        // 删除i位置的数
        // 是否满足要求
        for (int i = 1, l = 0, r = 2; i < n - 1; i++, l++, r++) {
            ans += (arr[l] > arr[r] && rightUp[r] && leftDown) ||
                    (arr[l] < arr[r] && rightDown[r] && leftUp) ? 1 : 0;
            boolean tmp = leftUp;
            leftUp = arr[l] > arr[i] && leftDown;
            leftDown = arr[l] < arr[i] && tmp;
        }
        // 单独计算不要n - 1位置的数时
        // 是否符合要求
        ans += leftDown || leftUp ? 1 : 0;
        return ans == 0 ? -1 : ans;
    }

    // for test
    public static int[] randomArray(int len, int maxValue) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 100;
        int testTime = 30000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen) + 1;
            int[] arr = randomArray(len, maxValue);
            int ans1 = ways1(arr);
            int ans2 = ways2(arr);
            if (ans1 != ans2) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
