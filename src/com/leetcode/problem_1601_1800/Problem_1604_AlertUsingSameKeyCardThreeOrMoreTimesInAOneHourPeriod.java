package com.leetcode.problem_1601_1800;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author ycb
 * @date 2023/2/7-09:01
 */
public class Problem_1604_AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod {

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        int n = keyName.length;
        HashMap<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = keyName[i], time = keyTime[i];
            map.putIfAbsent(name, new ArrayList<>());
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            map.get(name).add(hour * 60 + minute);
        }
        List<String> res = new ArrayList<>();
        for (String name : map.keySet()) {
            List<Integer> times = map.get(name);
            Collections.sort(times);
            int size = times.size();
            for (int i = 2; i < size; i++) {
                int t1 = times.get(i - 2), t2 = times.get(i);
                int diff = t2 - t1;
                if (diff <= 60) {
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}
