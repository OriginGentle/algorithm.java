package com.nowcoder.HJ;

import java.util.Scanner;

/**
 * @author ycb
 * @since 2022/3/22-14:29
 */
public class HJ1_Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int ans = getMaxLen(str);
        System.out.println(ans);
        sc.close();
    }

    public static int getMaxLen(String s) {
        String[] str = s.split(" ");
        return str[str.length - 1].length();
    }
}
