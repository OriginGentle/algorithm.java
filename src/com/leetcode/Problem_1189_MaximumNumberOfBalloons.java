package com.leetcode;

/**
 * @author ycb
 * @since 2022/2/13-14:09
 */
public class Problem_1189_MaximumNumberOfBalloons {

    public int maxNumberOfBalloons(String text) {
        int[] count = new int[5];
        for (int i = 0; i < text.length(); i++) {
            char cur = text.charAt(i);
            switch (cur) {
                case 'a':
                    count[0]++;
                    break;
                case 'b':
                    count[1]++;
                    break;
                case 'l':
                    count[2]++;
                    break;
                case 'o':
                    count[3]++;
                    break;
                case 'n':
                    count[4]++;
                    break;
            }
        }
        count[2] /= 2;
        count[3] /= 2;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < count.length; i++) {
            ans = Math.min(ans, count[i]);
        }
        return ans;
    }
}
