package com.system.III_training.day39;

/**
 * @author ycb
 * @date 2021/10/28-16:34
 * @description 来自百度
 * 给定一个字符串str，和一个正数k
 * str子序列的字符种数必须是k种，返回有多少子序列满足这个条件
 * 已知str中都是小写字母
 * 原题的结果最后要求取mod
 * 搞出桶来，组合公式
 */
public class Code03_SequenceKDifferentKinds {

    public static int nums(String s, int k) {
        char[] str = s.toCharArray();
        int[] count = new int[26];
        for (char ch : str) {
            count[ch - 97]++;
        }
        return process1(count, 0, k);
    }

    public static int process1(int[] arr, int index, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (index == arr.length) {
            return 0;
        }
        return process1(arr, index + 1, rest) + math(arr[index]) * process1(arr, index + 1, rest - 1);
    }

    // n个不同的球
    // 挑出1个的方法数 + 挑出2个的方法数 + ... + 挑出n个的方法数为:
    // C(n,1) + C(n,2) + ... + C(n,n) == (2 ^ n) -1
    public static int math(int n) {
        return (1 << n) - 1;
    }

    // for test
    public static void main(String[] args) {
        String str = "acbbcafjfj";
        int k = 4;
        System.out.println(nums(str, k));
    }
}
