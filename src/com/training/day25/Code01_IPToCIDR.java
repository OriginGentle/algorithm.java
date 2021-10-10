package com.training.day25;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ycb
 * @date 2021/10/10-13:28
 * @description https://leetcode.com/problems/ip-to-cidr/
 */
public class Code01_IPToCIDR {

    public static List<String> ipToCIDR(String ip, int n) {
        int cur = status(ip);
        // cur这个状态，最右侧的1，能表示下2的几次方
        int maxPower = 0;
        // 已经解决了多少ip了
        int solved = 0;
        int power = 0;
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            // cur最右侧的1，能搞定2的几次方个ip
            // cur : 000...000 01001000
            // 3
            maxPower = mostRightPower(cur);
            // cur : 0000....0000 00001000 -> 2的3次方的问题
            // sol : 0000....0000 00000001 -> 1 2的0次方
            // sol : 0000....0000 00000010 -> 2 2的1次方
            // sol : 0000....0000 00000100 -> 4 2的2次方
            // sol : 0000....0000 00001000 -> 8 2的3次方
            solved = 1;
            power = 0;
            // 防止溢出问题
            while ((solved << 1) <= n && (power + 1) <= maxPower) {
                solved <<= 1;
                power++;
            }
            ans.add(content(cur, power));
            n -= solved;
            cur += solved;
        }
        return ans;
    }

    public static String content(int status, int power) {
        StringBuilder builder = new StringBuilder();
        for (int move = 24; move >= 0; move -= 8) {
            builder.append(((status & (255 << move)) >>> move) + ".");
        }
        builder.setCharAt(builder.length() - 1, '/');
        builder.append(32 - power);
        return builder.toString();
    }

    public static HashMap<Integer, Integer> map = new HashMap<>();

    // 1 000000....000000 -> 2的32次方
    // 认为此时的1处于溢出位置
    public static int mostRightPower(int num) {
        if (map.isEmpty()) {
            map.put(0, 32);
            for (int i = 0; i < 32; i++) {
                // 00...0000 00000001 2的0次方
                // 00...0000 00000010 2的1次方
                // 00...0000 00000100 2的2次方
                // 00...0000 00001000 2的3次方
                map.put(1 << i, i);
            }
        }
        // num & (-num) -> num & (~num+1) -> 提取出最右侧的1
        return map.get(num & (-num));
    }

    public static int status(String ip) {
        int ans = 0;
        int move = 24;
        for (String str : ip.split("\\.")) {
            // 17.23.16.5  -- > "17" "23" "16" "5"
            // "17" --> 17 << 24
            // "23" --> 23 << 16
            // "16" --> 16 << 8
            // "5"  -->  5 << 0
            ans |= Integer.parseInt(str) << move;
            move -= 8;
        }
        return ans;
    }
}
