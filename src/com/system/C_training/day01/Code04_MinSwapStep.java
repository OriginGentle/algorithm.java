package com.system.C_training.day01;

/**
 * @Author ycb
 * @Date 2021/7/13-13:29
 * @Description 一个 数组中只有两种字符'G'和’B’，
 * 可以让所有的G都放在左侧，所有的B都放在右侧
 * 或者可以让所有的G都放在右侧，所有的B都放在左侧
 * 但是只能在相邻字符之间进行交换操作，
 * 返回至少需要交换几次
 */
public class Code04_MinSwapStep {

    public static int minStep1(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int step1 = 0;
        int gi = 0;
        // G放左边
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G') {
                step1 += i - (gi++);
            }
        }
        int step2 = 0;
        int bi = 0;
        // B放左边
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'B') {
                step2 += i - (bi++);
            }
        }
        return Math.min(step1, step2);
    }

    public static int minStep2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int step1 = 0;
        int step2 = 0;
        int gi = 0;
        int bi = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G') {
                step1 += i - (gi++);
            } else {
                step2 += i - (bi++);
            }
        }
        return Math.min(step1, step2);
    }

    public static String generateStr(int maxLen) {
        char[] str = new char[(int) (Math.random() * maxLen + 1)];
        for (int i = 0; i < str.length; i++) {
            str[i] = Math.random() < 0.5 ? 'G' : 'B';
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int maxLen = 1000;
        int testTimes = 100000;
        System.out.println("测试开始:");
        for (int i = 0; i < testTimes; i++) {
            String s = generateStr(maxLen);
            int ans1 = minStep1(s);
            int ans2 = minStep2(s);
            if (ans1 != ans2) {
                System.out.println(s);
                System.out.println("Oops!");
                System.out.println("ans1:" + ans1 + " ans2:" + ans2);
                break;
            }
        }
        System.out.println("测试结束！");
    }

}
