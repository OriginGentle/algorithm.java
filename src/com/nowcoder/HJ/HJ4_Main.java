package com.nowcoder.HJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author ycb
 * @since 2022/3/24-10:43
 */
public class HJ4_Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        while (sc.hasNext()) {
            builder.append(sc.next());
        }
        List<String> res = getRes(builder.toString());
        for (String cur : res) {
            System.out.println(cur);
        }
    }

    public static List<String> getRes(String str) {
        int s = str.length();
        int rest = s % 8;
        StringBuilder sb = null;
        if (rest != 0) {
            sb = new StringBuilder();
            for (int i = 0; i < 8 - rest; i++) {
                sb.append("0");
            }
        }
        str = str + sb.toString();
        int l = 0, r = 8;
        List<String> ans = new ArrayList<>();
        while (r <= str.length()) {
            ans.add(str.substring(l, r));
            l += 8;
            r += 8;
        }
        return ans;
    }
}
