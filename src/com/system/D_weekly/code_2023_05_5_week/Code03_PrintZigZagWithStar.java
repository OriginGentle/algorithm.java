package com.system.D_weekly.code_2023_05_5_week;

import java.io.*;
import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/6/1-23:09
 * @desc 来自华为OD
 * 如果n = 1，打印
 * 1***
 * 如果n = 2，打印
 * 1***
 * 3***    2***
 * 如果n = 3，打印
 * 1***
 * 3***    2***
 * 4***    5***    6***
 * 如果n = 4，打印
 * 1***
 * 3***    2***
 * 4***    5***    6***
 * 10**    9***    8***    7***
 * 输入一个数n，表示有多少行，从1开始输出，
 * 奇数行输出奇数个数，奇数行正序，偶数行输出偶数个数，偶数行逆序
 * 每个数后面加*补满四位，中间空4个，第n行顶格输出
 */
public class Code03_PrintZigZagWithStar {

    public static int MAXN = 100001;

    public static char[] space = new char[MAXN];

    public static int n, m;

    public static void main(String[] args) throws IOException {
        System.out.println("提醒，请输入n : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            m = n * 8;
            Arrays.fill(space, 0, m, ' ');
            boolean from = true;
            for (int i = 1, j = 1; i <= n; j += i, i++) {
                fill(from, j, i);
                for (int k = 0; k < m - 4; k++) {
                    out.print(space[k]);
                }
                out.println();
                from = !from;
            }
            out.flush();
        }
    }

    public static void fill(boolean from, int start, int number) {
        if (from) {
            for (int i = m - number * 8, j = 1; j <= number; i += 8, start++, j++) {
                insert(start, i);
            }
        } else {
            for (int i = m - 8, j = 1; j <= number; i -= 8, start++, j++) {
                insert(start, i);
            }
        }
    }

    // 135,  i...
    // 1 3 5 *
    // i
    public static void insert(int cur, int i) {
        //            X
        // i +1 +2 +3 +4
        int end = i + 4;
        int bit = cur > 999 ? 4 : (cur > 99 ? 3 : (cur > 9) ? 2 : 1);
        // 135 bit = 3
        //     offset = 100
        // (135 / 100) % 10 = 1
        // (135 / 10) % 10 = 3
        // (135 / 1) % 10 = 5
        // 4567 bit = 4
        //     offset = 1000
        // (cur / offset) % 10 -> 提取每一位的数字
        int offset = bit == 4 ? 1000 : (bit == 3 ? 100 : (bit == 2 ? 10 : 1));
        while (offset > 0) {
            space[i++] = (char) (((cur / offset) % 10) + '0');
            offset /= 10;
        }
        while (i < end) {
            space[i++] = '*';
        }
    }
}
