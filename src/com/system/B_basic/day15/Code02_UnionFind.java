package com.system.B_basic.day15;

import java.io.*;

/**
 * @author ycb
 * @date 2023/4/29-00:23
 * @desc 基于数组结构实现并查集结构，提供 ACM 模式的输入输出
 * https://www.nowcoder.com/questionTerminal/e7ed657974934a30b2010046536a5372
 */
public class Code02_UnionFind {

    public static int MAXN = 1000001;

    public static int[] father = new int[MAXN];

    public static int[] size = new int[MAXN];

    public static int[] help = new int[MAXN];

    // 初始化并查集
    public static void init(int n) {
        for (int i = 0; i <= n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    // 从i开始寻找集合代表点
    public static int find(int i) {
        int hi = 0;
        while (i != father[i]) {
            help[hi++] = i;
            i = father[i];
        }
        for (hi--; hi >= 0; hi--) {
            father[help[hi]] = i;
        }
        return i;
    }

    // 查询x和y是不是一个集合
    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    // x所在的集合，和y所在的集合，合并成一个集合
    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            if (size[fx] >= size[fy]) {
                size[fx] += size[fy];
                father[fy] = fx;
            } else {
                size[fy] += size[fx];
                father[fx] = fy;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            init(n);
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int op = (int) in.nval;
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                if (op == 1) {
                    out.println(isSameSet(x, y) ? "Yes" : "No");
                    out.flush();
                } else {
                    union(x, y);
                }
            }
        }
    }
}
