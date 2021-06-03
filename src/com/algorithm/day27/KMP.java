package com.algorithm.day27;

/**
 * @Author ycb
 * @Date 2021/2/4-9:09
 * @Description KMP算法
 * 假设字符串str长度为N，字符串match长度为M，M <= N
 * 想确定str中是否有某个子串是等于match的。
 * 时间复杂度 O(N)
 */
public class KMP {
    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s2.length() > s1.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0; // s1串中画圈的位置
        int y = 0; // s2串中画圈的位置
        int[] next = getNextArray(str2); // 获取s2串的有效信息
        while (x < str1.length && y < str2.length) {
            // 如果str1中的字符与str2中的字符匹配，则画圈的位置加1，去匹配下一位
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) { // 如果next数组的值为-1，即y==0,则画圈的位置不能再往前移动了，即s1串当前位置的值与s2串首位置的值不能匹配
                x++;
            } else {
                // 还能往左跳，s2串中画圈的位置向前移动到当前位置有效信息值的位置
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // 目前在哪个位置上求next数组的值
        int cn = 0; // next数组中的当前位置的有效信息的值，即当前是哪个位置的值再和i-1位置的字符比较
        while (i < next.length) {
            // 如果i位置的前一位的值与cn位置的值相等，则当前位置的有效信息值就等于cn + 1
            if (str2[i - 1] == str2[cn]) {
                // 既记录当前位置的有效信息值，也把next数组的指针向后移动一位
                next[i++] = ++cn;
            } else if (cn > 0) { // 如果cn > 0,则画圈的位置还可以向左移动
                cn = next[cn];
            } else {
                // 当前位置没有有效信息
                next[i++] = 0;
            }
        }
        return next;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
