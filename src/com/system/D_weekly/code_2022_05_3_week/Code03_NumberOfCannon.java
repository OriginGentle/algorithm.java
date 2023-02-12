package com.system.D_weekly.code_2022_05_3_week;

import java.util.TreeMap;

/**
 * @author ycb
 * @date 2022/5/21-16:07
 * @desc 给定一个数组arr，表示从早到晚，依次会出现的导弹的高度
 * 大炮打导弹的时候，如果一旦大炮定了某个高度去打，那么这个大炮每次打的高度都必须下降一点
 * 1) 如果只有一个大炮，返回最多能拦截多少导弹
 * 2) 如果所有的导弹都必须拦截，返回最少的大炮数量
 */
public class Code03_NumberOfCannon {

    public static int numOfCannon(int[] arr) {
        TreeMap<Integer, Integer> ends = new TreeMap<>();
        for (int cur : arr) {
            if (ends.ceilingKey(cur + 1) == null) {
                ends.put(Integer.MAX_VALUE, 1);
            }
            int cKey = ends.ceilingKey(cur + 1);
            if (ends.get(cKey) > 1) {
                ends.put(cKey, ends.get(cKey) - 1);
            } else {
                ends.remove(cKey);
            }
            ends.put(cur, ends.getOrDefault(cur, 0) + 1);
        }
        int ans = 0;
        for (int val : ends.values()) {
            ans += val;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {15, 7, 14, 6, 5, 13, 5, 10, 9};
        System.out.println(numOfCannon(arr));
    }
}
