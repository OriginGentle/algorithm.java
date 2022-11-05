package com.weekly.code_2022_11_1_week;

/**
 * @author ycb
 * @date 2022/11/4-10:29
 * @desc https://leetcode.cn/problems/construct-smallest-number-from-di-string/
 * 给你下标从 0 开始、长度为 n的字符串pattern，
 * 它包含两种字符，'I'表示 上升，'D'表示 下降。
 * 你需要构造一个下标从 0开始长度为n + 1的字符串，且它要满足以下条件：
 * num包含数字'1'到'9'，其中每个数字至多使用一次。
 * 如果pattern[i] == 'I'，那么num[i] < num[i + 1]。
 * 如果pattern[i] == 'D'，那么num[i] > num[i + 1]。
 * 请你返回满足上述条件字典序 最小的字符串num。
 */
public class Code05_CreateMinNumberFromPattern {

    public static String smallestNumber(String pattern) {
        int ans = create(pattern.toCharArray(), 0, 0, 0);
        return String.valueOf(ans);
    }

    // pattern I I I D
    //         0 1 2 i
    //       1 3 4 5 2
    // -1

    //       1589
    //        9 8    5 4 3 2 1 0
    //        1 1    1 0 0 0 1 0
    //       number = 1589
    // 返回 i... 所有数字都决定了，并且不破坏pattern，并且1~9每个数字最多用一次
    // 能出来的最小值是啥，返回
    public static int create(char[] pattern, int index, int status, int num) {
        if (index == pattern.length + 1)
            return num;

        int cur = 0;
        while ((cur = next(status, cur)) != -1) {
            if (index == 0
                    || (pattern[index - 1] == 'I' && num % 10 < cur)
                    || (pattern[index - 1] == 'D' && num % 10 > cur)) {

                int ans = create(pattern, index + 1, status | (1 << cur), num * 10 + cur);

                if (ans != -1)
                    return ans;
            }
        }
        return -1;
    }

    public static int next(int status, int num) {
        for (int i = num + 1; i <= 9; i++) {
            if ((status & (1 << i)) == 0)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String pattern = "IIIDIDDD";
        String ans = smallestNumber(pattern);
        System.out.println(ans);
    }
}
