package com.leetcode.problem_0001_0200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2022/1/13-13:14
 */
public class Problem_0068_TextJustification {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int count = 0, start = 0;
        // 每次处理满足maxWidth的数量最多的单词量
        for (int i = 0; i < words.length; i++) {
            count += words[i].length();
            if (count > maxWidth) {
                ans.add(process(words, start, i - 1, maxWidth));
                start = i;
                count = words[i].length();
            }
            // 一个单词结束至少要有一个空格
            count++;
        }
        // 处理最后一截
        ans.add(process(words, start, words.length - 1, maxWidth));
        return ans;
    }

    private static String process(String[] words, int start, int end, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        if (start == end) {
            onWordOneRow(words, start, maxWidth, sb);
        } else if (end == words.length - 1) {
            lastRow(words, start, end, maxWidth, sb);
        } else {
            normal(words, start, end, maxWidth, sb);
        }
        return sb.toString();
    }

    private static void normal(String[] words, int start, int end, int maxWidth, StringBuilder sb) {
        // 正常情况下:要求单词间的空格尽量均匀分配，可能左边的空格会多一
        // 先计算出单词的长度
        int wordsLength = 0;
        for (int i = start; i <= end; i++) {
            wordsLength += words[i].length();
        }
        // 再看看均匀分配能分配几个
        int separate = (maxWidth - wordsLength) / (end - start);
        // 多余出来的空格,即不能平均的部分，这部分要按照从左到右依次分配
        // 比如，4个单词，3个空格，一共有5个空格
        // 平均的话是每个间隔一个空格，还剩下2个空格，从左往右分配
        // 最后的的间隔就是前两个各占2个空格，最后一个占1个空格
        int remain = (maxWidth - wordsLength) % (end - start);

        for (int i = start; i <= end; i++) {
            sb.append(words[i]);
            if (i != end) {
                // 先加上平均分配的空格
                for (int j = 0; j < separate; j++) {
                    sb.append(" ");
                }
                // 再看还有没有多余的空格
                if (remain-- > 0) {
                    sb.append(" ");
                }
            }
        }
    }

    private static void lastRow(String[] words, int start, int end, int maxWidth, StringBuilder sb) {
        // 最后一行：单词之间不用加额外的空格 --> 左对齐，剩下空位补空字符
        for (int i = start; i <= end; i++) {
            sb.append(words[i]);
            if (i != end) {
                // 单词之间只有一个空格
                sb.append(" ");
            } else {
                // 判断最后要加几个空格
                int rest = maxWidth - sb.length();
                for (int j = 0; j < rest; j++) {
                    sb.append(" ");
                }
            }
        }
    }

    private static void onWordOneRow(String[] words, int start, int maxWidth, StringBuilder sb) {
        // 一行只有一个单词的情况
        sb.append(words[start]);
        int rest = maxWidth - words[start].length();
        for (int i = 0; i < rest; i++) {
            sb.append(" ");
        }
    }
}
