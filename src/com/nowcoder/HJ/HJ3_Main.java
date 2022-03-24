package com.nowcoder.HJ;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author ycb
 * @since 2022/3/24-10:32
 */
public class HJ3_Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<Integer> set = new TreeSet<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            set.add(sc.nextInt());
        }
        for (int num : set) {
            System.out.println(num);
        }
    }
}
