package com.system.D_weekly.code_2023_03_4_week;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author ycb
 * @date 2023/3/23-10:06
 * @desc 给你一个由数字组成的字符串 s，返回 s 中独特子字符串数量
 * 其中的每一个数字出现的频率都相同。
 * 测试链接 : https://leetcode.cn/problems/unique-substrings-with-equal-digit-frequency/
 */
public class Code05_UniqueSubstringsWithEqualDigitFrequency {

    // 帖子上的一个很骚的方法:构造简陋的hash函数
    // 其实是不对的，只是可以通过所有当前的测试用例而已
    // 可以构造出让这种方法不通过的例子，原因是这种简陋的hash函数太容易碰撞了
    // 其实这个题的最优解，依然需要使用DC3算法生成后缀数组来做
    public static int equalDigitFrequency(String s) {
        long base = 1000000007;
        HashSet<Long> set = new HashSet<>();
        int[] cnts = new int[10];
        for (int l = 0; l < s.length(); l++) {
            Arrays.fill(cnts, 0);
            long hashCode = 0;
            int curVal, maxCnt = 0, maxKinds = 0, allKinds = 0;
            for (int r = l; r < s.length(); r++) {
                curVal = s.charAt(r) - '0';
                // l....r 字符串的哈希值
                // l...r-1 算出的哈希值 * base + 当前位 + 1
                hashCode = hashCode * base + curVal + 1;
                cnts[curVal]++;
                if (cnts[curVal] == 1) {
                    allKinds++;
                }
                if (cnts[curVal] > maxCnt) {
                    maxCnt = cnts[curVal];
                    maxKinds = 1;
                } else if (cnts[curVal] == maxCnt) {
                    maxKinds++;
                }
                if (maxKinds == allKinds) {
                    set.add(hashCode);
                }
            }
        }
        return set.size();
    }
}
