package com.system.C_training.day38;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/25-10:59
 */
public class Problem_0763_PartitionLabels {

    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] far = new int[26];
        for (int i = 0; i < N; i++) {
            far[str[i] - 'a'] = i;
        }
        // 窗口
        int left = 0;
        int right = far[str[0] - 'a'];
        for (int i = 1; i < N; i++) {
            if (i > right) {
                ans.add(right - left + 1);
                left = i;
            }
            right = Math.max(right, far[str[i] - 'a']);
        }
        ans.add(right - left + 1);
        return ans;
    }
}
