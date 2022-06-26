package com.weekly.code_2022_06_3_week;

/**
 * @author ycb
 * @date 2022/6/26-14:06
 * @desc 一个字符串s，表示仓库的墙 与 货物，其中'｜'表示墙,'*'表示货物。
 * 给定一个起始下标start和一个终止下标end，
 * 找出子串中 被墙包裹的货物 数量
 * 比如
 * s = "|**|**|*"
 * start = 1, end = 7
 * start和end截出的子串是 "**|**|*"
 * 被 '|'包裹的 '*' 有两个，所以返回2
 * 现在给定一系列的start，startIndices[]，和对应一系列的end ,endIndices[]
 * 返回每一对[start,end]的截出来的货物数量
 * 数据规模：
 * 字符串s长度<=10^5
 * startIndices长度 == endIndices长度 <=10^5
 */
public class Code04_StarNumber {

    public static int[] number(String s, int[] starts, int[] ends) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] sum = new int[n];

        int pre = -1;
        int num = 0;
        for (int i = 0; i < n; i++) {
            pre = str[i] == '|' ? i : pre;
            num += str[i] == '*' ? 1 : 0;
            left[i] = pre;
            sum[i] = num;
        }
        pre = -1;
        for (int i = n - 1; i >= 0; i--) {
            pre = str[i] == '|' ? i : pre;
            right[i] = pre;
        }
        int m = starts.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = stars(starts[i], ends[i], left, right, sum);
        }
        return ans;
    }

    public static int stars(int start, int end, int[] l, int[] r, int[] s) {
        int left = r[start];
        int right = l[end];
        if (left == -1 || right == -1 || (left >= right)) {
            return 0;
        }
        return left == 0 ? s[right] : (s[right] - s[left - 1]);
    }

    // for test
    public static void main(String[] args) {
        String s = "|**|**|*";
        int[] a = new int[]{0, 1, 3, 4};
        int[] b = new int[]{7, 7, 6, 5};
        int[] arr = number(s, a, b);
        for (int ans : arr) {
            System.out.println(ans);
        }
    }
}
