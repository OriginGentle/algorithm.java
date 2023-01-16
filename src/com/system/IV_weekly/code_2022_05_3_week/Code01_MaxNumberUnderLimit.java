package com.system.IV_weekly.code_2022_05_3_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/5/21-16:07
 * @desc 来自字节
 * 输入:
 * 去重数组arr，里面的数只包含0~9
 * limit，一个数字
 * 返回:
 * 要求比limit小的情况下，能够用arr拼出来的最大数字
 */
public class Code01_MaxNumberUnderLimit {

    public static int tmp = 0;

    public static int maxNumber1(int[] arr, int limit) {
        tmp = 0;
        limit--;
        Arrays.sort(arr);
        int offset = 1;
        while (offset <= limit / 10) {
            offset *= 10;
        }
        process1(arr, 0, offset, limit);
        if (tmp == 0) {
            int rest = 0;
            offset /= 10;
            while (offset > 0) {
                rest += arr[arr.length - 1] * offset;
                offset /= 10;
            }
            return rest;
        }
        return tmp;
    }

    public static void process1(int[] arr, int num, int offset, int limit) {
        if (offset == 0) {
            if (num <= limit) {
                tmp = Math.max(tmp, num);
            }
        } else {
            for (int cur : arr) {
                process1(arr, num * 10 + cur, offset / 10, limit);
            }
        }
    }

    /*
    ====================================================================================================================
     */

    public static int maxNumber2(int[] arr, int limit) {
        Arrays.sort(arr);
        limit--;
        int offset = 1;
        while (offset <= limit / 10) {
            offset *= 10;
        }
        int ans = process2(arr, limit, offset);
        return ans != -1 ? ans : rest(arr, offset / 10);
    }

    public static int process2(int[] arr, int limit, int offset) {
        if (offset == 0) {
            return limit;
        }
        // 68886
        // 最高位的数是啥 -> cur
        int cur = (limit / offset) % 10;
        int near = near(arr, cur);
        if (near == -1) {
            return -1;
        } else if (arr[near] == cur) { // 找出来的数字，真的和当前的数字cur一样
            int ans = process2(arr, limit, offset / 10);
            if (ans != -1) {
                return ans;
            } else if (near > 0) {
                near--;
                return (limit / (offset * 10)) * offset * 10 + arr[near] * offset + rest(arr, offset / 10);
            } else {
                return -1;
            }
        } else { // 找出来的数字，比当前的数字cur小
            return (limit / (offset * 10)) * offset * 10 + arr[near] * offset + rest(arr, offset / 10);
        }
    }

    public static int rest(int[] arr, int offset) {
        int rest = 0;
        while (offset > 0) {
            rest += offset * arr[arr.length - 1];
            offset /= 10;
        }
        return rest;
    }

    // 在有序数组中，找到 <= num，且最大的数，返回在arr中的位置
    // 如果所有数都大于num，返回-1
    public static int near(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int near = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= num) {
                near = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return near;
    }

    // for test
    public static int[] randomArray() {
        int[] arr = new int[(int) (Math.random() * 10) + 1];
        boolean[] cnt = new boolean[10];
        for (int i = 0; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * 10);
            } while (cnt[arr[i]]);
            cnt[arr[i]] = true;
        }
        return arr;
    }

    public static void main(String[] args) {
        int max = 3000;
        int testTime = 100;
        System.out.println("测试开始");
        for (int i = 0; i < max; i++) {
            int[] arr = randomArray();
            for (int j = 0; j < testTime; j++) {
                int ans1 = maxNumber1(arr, i);
                int ans2 = maxNumber2(arr, i);
                if (ans1 != ans2) {
                    System.out.println("出错了!");
                    System.out.println("数组为 ：");
                    for (int num : arr) {
                        System.out.print(num + " ");
                    }
                    System.out.println();
                    System.out.println("数字为 ：" + i);
                    System.out.println(ans1);
                    System.out.println(ans2);
                    break;
                }
            }
        }
        System.out.println("测试结束");
    }
}
