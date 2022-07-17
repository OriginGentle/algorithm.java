package com.weekly.code_2022_07_2_week;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author ycb
 * @date 2022/7/17-13:50
 * @desc https://leetcode.cn/problems/employee-free-time/
 */
public class Code04_EmployeeFreeTime {

    public static class Interval {
        public int start;
        public int end;

        public Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        ArrayList<int[]> arr = new ArrayList<>();
        for (List<Interval> people : schedule) {
            for (Interval interval : people) {
                arr.add(new int[]{interval.start, interval.end, 0});
                arr.add(new int[]{interval.end, interval.end, 1});
            }
        }
        arr.sort((a, b) -> a[0] - b[0]);
        HashSet<Integer> set = new HashSet<>();
        set.add(arr.get(0)[1]);
        List<Interval> ans = new ArrayList<>();
        for (int i = 1; i < arr.size(); i++) {
            int[] cur = arr.get(i);
            if (cur[2] == 0) {
                if (set.isEmpty() && arr.get(i - 1)[0] != cur[0]) {
                    ans.add(new Interval(arr.get(i - 1)[0], cur[0]));
                }
                set.add(cur[1]);
            } else {
                set.remove(cur[0]);
            }
        }
        return ans;
    }
}
