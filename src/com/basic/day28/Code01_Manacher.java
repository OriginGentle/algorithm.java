package com.basic.day28;

/**
 * @Author ycb
 * @Date 2021/6/3-17:36
 * @Description Manacher算法
 */
public class Code01_Manacher {

    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // "12132" --> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        // 回文半径的大小
        int[] pArr = new int[str.length];
        // 最右回文边界中心
        int C = -1;
        // 最右回文边界
        // 理解上:R代表最右的扩成功的位置
        // coding:最右的扩成功位置的，再下一个位置(第一次失败的位置)
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            // R : 第一个违规的位置
            // R <= i : i在R外，以i位置字符为回文半径至少是1
            // R > i : 理解上i被R罩住的情况:
            // ① i'的回文半径在L内 --> i的回文半径 == i'的回文半径
            // ② i'的回文半径在L外 --> i的回文半径: i 到 R 的距离
            // ③ i'的回文半径正好在L上 --> i的回文半径: 至少是 i 到 R 的距离
            // (2 * C - i) : i'的位置    pArr[2 * C - i]: i'的回文半径
            // 至少不用验的区域 : Math.min(pArr[2 * C - i], R - i)
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // pArr[i]代表至少不用验的区域
            // 成立条件:往左的位置不越界，往右的位置不越界
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                // 情况③的处理,如果L往左的字符与R往右的字节配上了，则推高此时i位置的回文半径
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // 如果R被推高，更新R和C
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        // 1221 --> #1#2 # 2#1#
        // 回文半径：5 --> max
        // max - 1就是原始串的最大回文子串长度
        return max - 1;
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[2 * str.length() + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

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
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
