package com.leetcode.problem_0601_0800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/3/31
 */
public class Problem_0728_SelfDividingNumbers {

    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isDividingNum(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private static boolean isDividingNum(int num) {
        int tmp = num;
        while (tmp != 0) {
            int res = tmp % 10;
            if (res == 0 || num % res != 0) {
                return false;
            }
            tmp /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        int left = 1;
        int right = 22;
        List<Integer> ans = selfDividingNumbers(left, right);
        for (int a : ans) {
            System.out.print(a + " ");
        }
    }

}
