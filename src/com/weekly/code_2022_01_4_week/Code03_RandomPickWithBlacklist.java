package com.weekly.code_2022_01_4_week;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/2/10-17:40
 * @description https://leetcode.com/problems/random-pick-with-blacklist/
 * 给定一个包含 [0，n) 中不重复整数的黑名单 blacklist
 * 写一个函数从 [0, n) 中返回一个不在 blacklist 中的随机整数
 * 对它进行优化使其尽量少调用系统方法 Math.random()
 * 1 <= n <= 1000000000
 * 0 <= blacklist.length < min(100000, N)
 */
public class Code03_RandomPickWithBlacklist {

    class Solution {
        private int size;
        private Map<Integer, Integer> convert = new HashMap<>();

        public Solution(int n, int[] blacklist) {
            Arrays.sort(blacklist);
            int m = blacklist.length;
            for (int i = 0; i < m && blacklist[i] < n; i++) {
                for (n--; n > blacklist[i]; n--) {
                    if (n == blacklist[m - 1]) {
                        m--;
                    } else {
                        convert.put(blacklist[i], n);
                        break;
                    }
                }
            }
            size = n;
        }

        public int pick() {
            int cur = (int) (Math.random() * size);
            return convert.containsKey(cur) ? convert.get(cur) : cur;
        }
    }

}
