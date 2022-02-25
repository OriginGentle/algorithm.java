package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2022/2/25-11:23
 */
public class Problem_0093_RestoreIpAddresses {

    public static List<String> restoreIpAddresses1(String s) {
        List<String> ans = new ArrayList<>();
        int len = s.length();
        if (len < 4 || len > 12) {
            return ans;
        }
        process(s, ans, "", 0, 4);
        return ans;
    }

    // 当前来到index位置，0...index之前已经做过决定了
    // 之前做的决定所形成的答案是path
    // 还需要凑够rest段才能形成完整的ip地址
    // 最终形成的答案都放在ans里面
    public static void process(String str, List<String> ans, String path, int index, int rest) {
        if (index == str.length() && rest == 0) {
            ans.add(path);
            return;
        }
        // 剪枝:剩余字符长度如果大于剩余ip所需的最大长度,说明一定有字符剩余
        if (rest * 3 < str.length() - index) return;
        int sum = 0;
        for (int i = index; i < str.length(); i++) {
            // 超过两位数的ip，开头不能为'0'
            if (str.charAt(index) == '0' && i > index) break;
            sum = sum * 10 + str.charAt(i) - '0';
            if (sum >= 0 && sum <= 255) { // 证明当前做的决定有效
                process(str, ans, path + sum + (rest > 1 ? "." : ""), i + 1, rest - 1);
            } else {
                return;
            }
        }
    }

    /*
    ====================================================================================================================
     */

    private final int SEG_COUNT = 4;
    private int[] segments;
    private List<String> ans = new ArrayList<>();

    public List<String> restoreIpAddresses2(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    private void dfs(String str, int segId, int segStart) {
        // 如果找到了4段ip，并且刚好遍历完字符串
        if (segId == SEG_COUNT) {
            if (segStart == str.length()) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < SEG_COUNT; i++) {
                    builder.append(segments[i]);
                    if (i != SEG_COUNT - 1) builder.append(".");
                }
                ans.add(builder.toString());
            }
            return;
        }
        // 没找到4段ip,就遍历完了字符，直接返回
        if (segStart == str.length()) return;
        if (str.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(str, segId + 1, segStart + 1);
        }
        int addr = 0;
        for (int segEnd = segStart; segEnd < str.length(); ++segEnd) {
            addr = addr * 10 + (str.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 255) {
                segments[segId] = addr;
                dfs(str, segId + 1, segEnd + 1);
            } else break;
        }
    }
}
