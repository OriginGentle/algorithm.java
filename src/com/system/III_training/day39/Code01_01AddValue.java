package com.system.III_training.day39;

/**
 * @author ycb
 * @date 2021/10/28-16:34
 * @description 来自腾讯
 * 给定一个只由0和1组成的字符串S，假设下标从1开始，规定i位置的字符价值V[i]计算方式如下 :
 * 1) i == 1时，V[i] = 1
 * 2) i > 1时，如果S[i] != S[i-1]，V[i] = 1
 * 3) i > 1时，如果S[i] == S[i-1]，V[i] = V[i-1] + 1
 * 你可以随意删除S中的字符，返回整个S的最大价值
 * 字符串长度<=5000
 */
public class Code01_01AddValue {

    public static int max1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] arr = new int[str.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = str[i] == '0' ? 0 : 1;
        }
        return process1(arr, 0, 0, 0);
    }

    // 递归含义 :
    // 目前在arr[index...]上做选择, str[index...]的左边，最近的数字是lastNum
    // 并且lastNum所带的价值，已经拉高到baseValue
    // 返回在str[index...]上做选择，最终获得的最大价值
    public static int process1(int[] arr, int index, int lastNum, int baseValue) {
        if (index == arr.length) {
            return 0;
        }
        int curValue = arr[index] == lastNum ? baseValue + 1 : 1;
        // 不要当前位置
        int p1 = process1(arr, index + 1, lastNum, baseValue);
        // 要当前位置
        int p2 = process1(arr, index + 1, arr[index], curValue);
        return Math.max(p1, p2 + curValue);
    }
}
