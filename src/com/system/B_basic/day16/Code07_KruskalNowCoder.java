package com.system.B_basic.day16;

import java.io.*;
import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/4/29-00:13
 * @desc 模拟 ACM 模式的输入输出
 * https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
 */
public class Code07_KruskalNowCoder {

    public static int MAXM = 100001;

    public static int[][] edges = new int[MAXM][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                edges[i][0] = (int) in.nval;
                in.nextToken();
                edges[i][1] = (int) in.nval;
                in.nextToken();
                edges[i][2] = (int) in.nval;
            }
            Arrays.sort(edges, 0, m, (a, b) -> a[2] - b[2]);
            build(n);
            int ans = 0;
            for (int[] edge : edges) {
                if (union(edge[0], edge[1])) {
                    ans += edge[2];
                }
            }
            out.println(ans);
            out.flush();
        }
    }

    public static int MAXN = 10001;

    public static int[] father = new int[MAXN];

    public static int[] size = new int[MAXN];

    public static int[] help = new int[MAXN];

    public static void build(int n) {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    private static int find(int i) {
        int size = 0;
        while (i != father[i]) {
            help[size++] = i;
            i = father[i];
        }
        while (size > 0) {
            father[help[--size]] = i;
        }
        return i;
    }

    // 如果i和j，原本是一个集合，返回false
    // 如果i和j，不是一个集合，合并，然后返回true
    public static boolean union(int i, int j) {
        int fi = find(i);
        int fj = find(j);
        if (fi != fj) {
            if (size[fi] >= size[fj]) {
                father[fj] = fi;
                size[fi] += size[fj];
            } else {
                father[fi] = fj;
                size[fj] += size[fi];
            }
            return true;
        } else {
            return false;
        }
    }
}
