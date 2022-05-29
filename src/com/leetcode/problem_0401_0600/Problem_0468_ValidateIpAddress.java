package com.leetcode.problem_0401_0600;

/**
 * @author ycb
 * @date 2022/5/29-12:02
 */
public class Problem_0468_ValidateIpAddress {

    public static String validIPAddress(String ip) {
        if (ip.indexOf(".") >= 0 && isIPv4(ip)) return "IPv4";

        if (ip.indexOf(":") >= 0 && isIPV6(ip)) return "IPv6";

        return "Neither";
    }

    public static boolean isIPV6(String ip) {
        int n = ip.length(), cnt = 0;
        char[] cs = ip.toCharArray();
        for (int i = 0; i < n && cnt <= 7; ) {
            int j = i;

            while (j < n && ((cs[j] >= 'a' && cs[j] <= 'f') ||
                    (cs[j] >= 'A' && cs[j] <= 'F') || (cs[j] >= '0' && cs[j] <= '9')))
                j++;

            // 非 item 字符之间没有 item 或 长度超过 4
            if (i == j || j - i > 4) return false;

            i = j + 1;
            if (j == n) continue;

            // 存在除 : 以外的其他非数字字符
            if (cs[j] != ':') return false;

            cnt++;
        }
        // 恰好存在 7 个不位于两段的 :
        return cnt == 7 && cs[0] != ':' && cs[n - 1] != ':';
    }

    public static boolean isIPv4(String ip) {
        int n = ip.length(), cnt = 0;
        char[] cs = ip.toCharArray();
        for (int i = 0; i < n && cnt <= 3; ) {
            // 找到连续数字段，以 x 存储
            int j = i, x = 0;
            while (j < n && cs[j] >= '0' && cs[j] <= '9' && x <= 255) x = x * 10 + (cs[j++] - '0');

            // 非 item 字符之间没有 item
            if (i == j) return false;

            // 含前导零 或 数值大于 255
            if ((j - i > 1 && cs[i] == '0') || (x > 255)) return false;

            i = j + 1;
            if (j == n) continue;

            // 存在除 . 以外的其他非数字字符
            if (cs[j] != '.') return false;

            cnt++;
        }
        // 恰好存在 3 个不位于两端的 .
        return cnt == 3 && cs[0] != '.' && cs[n - 1] != '.';
    }


    public static void main(String[] args) {
        String ip = "172.16.254.1";
        String ans = validIPAddress(ip);
        System.out.println(ans);
    }
}
