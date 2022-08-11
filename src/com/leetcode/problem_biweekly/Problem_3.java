package com.leetcode.problem_biweekly;

import javax.xml.transform.Source;
import java.util.HashMap;
import java.util.Map;

public class Problem_3 {

    // 贪心
    public static long taskSchedulerII(int[] tasks, int space) {
        // key : 任务类型
        // value : 完成的天数
        Map<Integer, Long> cmt = new HashMap<>();
        long day = 0;
        for (int i = 0; i < tasks.length; i++) {
            int cur = tasks[i];
            if (!cmt.containsKey(cur)) {
                day++;
            } else {
                Long pre = cmt.get(cur);
                if (day - pre <= space) {
                    long has = day - pre;
                    long need = space - has + 1;
                    day += need;
                } else {
                    day++;
                }
            }
            cmt.put(cur, day);
        }
        return day;
    }

    public static void main(String[] args) {
        int[] arr = {5,8,8,5};
        int p = 2;
        long ans = taskSchedulerII(arr, p);
        System.out.println(ans);
    }
}
