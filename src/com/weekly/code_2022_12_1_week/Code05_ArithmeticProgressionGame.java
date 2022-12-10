package com.weekly.code_2022_12_1_week;

import java.io.*;

/**
 * @author ycb
 * @date 2022/12/10-19:55
 * @desc 蓝桥杯练习题(洛谷原题)
 * 等差数列的概念人人都知道
 * 给定一个原始数组arr，长度为N
 * 并且实现如下两个操作 :
 * void add(int l, int r, int a, int b) :
 * 表示在arr[l...r]这个范围上，
 * 从左往右依次加 : a、a + b * 1、a + b*2、...、a + b*(r-l)
 * int number(int l, int r) :
 * 表示arr[l...r]这一段，最少可以划分成几个等差数列
 * 这两个方法都要求实现的特别高效，因为调用次数很多
 * N <= 100000
 * add调用次数 <= 100000
 * number调用次数 <= 100000
 * 测试链接 : https://www.luogu.com.cn/problem/P4243
 */
public class Code05_ArithmeticProgressionGame {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            n--;
            build(1, n, 1);
            in.nextToken();
            int qT = (int) in.nval;
            for (int i = 0; i < qT; i++) {
                in.nextToken();
                String op = in.sval;
                if (op.equals("A")) {
                    in.nextToken();
                    int l = (int) in.nval;
                    in.nextToken();
                    int r = (int) in.nval;
                    in.nextToken();
                    int a = (int) in.nval;
                    in.nextToken();
                    int b = (int) in.nval;
                    add(l, r, a, b);
                } else {
                    in.nextToken();
                    int l = (int) in.nval;
                    in.nextToken();
                    int r = (int) in.nval;
                    out.println(number(l, r));
                    out.flush();
                }
            }
        }
    }

    public static class Info {
        public long lDiff;
        public long rDiff;
        public int lSplit;
        public int rSplit;
        public int stable;
        public int size;

        public Info(long a, long b, int c, int d, int e, int f) {
            lDiff = a;
            rDiff = b;
            lSplit = c;
            rSplit = d;
            stable = e;
            size = f;
        }
    }

    public static final int MAXN = 100010;

    public static int[] arr = new int[MAXN];

    public static Info[] resident = new Info[MAXN << 2];

    public static Info[] temporary = new Info[MAXN << 2];

    static {
        for (int i = 0; i < MAXN << 2; i++) {
            resident[i] = new Info(0, 0, 0, 0, 0, 0);
            temporary[i] = new Info(0, 0, 0, 0, 0, 0);
        }
    }

    public static long[] lazy = new long[MAXN << 2];

    public static int n;

    private static void build(int l, int r, int rt) {
        if (l == r) {
            resident[rt].lDiff = arr[l] - arr[l - 1];
            resident[rt].rDiff = resident[rt].lDiff;
            resident[rt].lSplit = 1;
            resident[rt].rSplit = 1;
            resident[rt].stable = 0;
            resident[rt].size = 1;
        } else {
            int m = (l + r) >> 1;
            build(l, m, rt << 1);
            build(m + 1, r, rt << 1 | 1);
            pushUp(resident, rt, rt << 1, rt << 1 | 1);
        }
    }

    private static void pushUp(Info[] info, int f, int l, int r) {
        if (l == -1 || r == -1) {
            int i = l == -1 ? r : l;
            info[f].lDiff = info[i].lDiff;
            info[f].rDiff = info[i].rDiff;
            info[f].lSplit = info[i].lSplit;
            info[f].rSplit = info[i].rSplit;
            info[f].stable = info[i].stable;
            info[f].size = info[i].size;
        } else {
            boolean connect = info[l].rDiff == info[r].lDiff;
            info[f].lDiff = info[l].lDiff;
            info[f].rDiff = info[r].rDiff;
            info[f].size = info[l].size + info[r].size;
            info[f].stable = info[l].stable + info[r].stable;
            if (info[l].stable == 0 && info[r].stable == 0) {
                if (connect) {
                    info[f].lSplit = info[l].lSplit - 1;
                    info[f].rSplit = info[r].rSplit - 1;
                    info[f].stable++;
                } else {
                    info[f].lSplit = info[f].size;
                    info[f].rSplit = info[f].size;
                }
            } else if (info[l].stable == 0) {
                info[f].rSplit = info[r].rSplit;
                if (connect) {
                    info[f].lSplit = info[l].lSplit - 1;
                    if (info[r].lSplit > 0) {
                        info[f].stable += (info[r].lSplit - 1) / 2 + 1;
                    }
                } else {
                    info[f].lSplit = info[l].size + info[r].lSplit;
                }
            } else if (info[r].stable == 0) {
                info[f].lSplit = info[l].lSplit;
                if (connect) {
                    info[f].rSplit = info[r].rSplit - 1;
                    if (info[l].rSplit > 0) {
                        info[f].stable += (info[l].rSplit - 1) / 2 + 1;
                    }
                } else {
                    info[f].rSplit = info[r].size + info[l].rSplit;
                }
            } else {
                info[f].lSplit = info[l].lSplit;
                info[f].rSplit = info[r].rSplit;
                if (info[l].rSplit == 0 && info[r].lSplit == 0) {
                    if (connect) {
                        info[f].stable--;
                    }
                } else if (info[l].rSplit == 0) {
                    if (connect) {
                        info[f].stable += (info[r].lSplit - 1) / 2;
                    } else {
                        info[f].stable += info[r].lSplit / 2;
                    }
                } else if (info[r].lSplit == 0) {
                    if (connect) {
                        info[f].stable += (info[l].rSplit - 1) / 2;
                    } else {
                        info[f].stable += info[l].rSplit / 2;
                    }
                } else {
                    int add = (info[l].rSplit + info[r].lSplit) / 2;
                    if (connect) {
                        add = (info[l].rSplit - 1) / 2 + (info[r].lSplit - 1) / 2 + 1;
                    }
                    info[f].stable += add;
                }
            }
        }
    }

    private static void pushDown(int rt) {
        if (lazy[rt] != 0) {
            lazy[rt << 1] += lazy[rt];
            resident[rt << 1].lDiff += lazy[rt];
            resident[rt << 1].rDiff += lazy[rt];
            lazy[rt << 1 | 1] += lazy[rt];
            resident[rt << 1 | 1].lDiff += lazy[rt];
            resident[rt << 1 | 1].rDiff += lazy[rt];
            lazy[rt] = 0;
        }
    }

    public static void add(int l, int r, int a, int b) {
        if (l > 1) {
            add(l - 1, l - 1, a, 1, n, 1);
        }
        if (r <= n) {
            add(r, r, -((long) a + ((long) (r - l)) * b), 1, n, 1);
        }
        if (l < r) {
            add(l, r - 1, b, 1, n, 1);
        }
    }

    public static void add(int L, int R, long V, int l, int r, int rt) {
        if (L <= l && r <= R) {
            resident[rt].lDiff += V;
            resident[rt].rDiff += V;
            lazy[rt] += V;
        } else {
            int m = (l + r) >> 1;
            pushDown(rt);
            if (L <= m) {
                add(L, R, V, l, m, rt << 1);
            }
            if (R > m) {
                add(L, R, V, m + 1, r, rt << 1 | 1);
            }
            pushUp(resident, rt, rt << 1, rt << 1 | 1);
        }
    }

    public static long number(int l, int r) {
        if (l == r) {
            return 1;
        }
        query(l, r - 1, 1, n, 1);
        long ans = (r - l + 2) / 2;
        if (temporary[1].stable != 0) {
            ans = temporary[1].stable + (temporary[1].lSplit + 1) / 2 + (temporary[1].rSplit + 1) / 2;
        }
        return ans;
    }

    public static void query(int L, int R, int l, int r, int rt) {
        if (L <= l && r <= R) {
            temporary[rt].lDiff = resident[rt].lDiff;
            temporary[rt].rDiff = resident[rt].rDiff;
            temporary[rt].lSplit = resident[rt].lSplit;
            temporary[rt].rSplit = resident[rt].rSplit;
            temporary[rt].stable = resident[rt].stable;
            temporary[rt].size = resident[rt].size;
        } else {
            int m = (l + r) / 2;
            pushDown(rt);
            int ll = -1;
            if (L <= m) {
                ll = rt << 1;
                query(L, R, l, m, rt << 1);
            }
            int rr = -1;
            if (R > m) {
                rr = rt << 1 | 1;
                query(L, R, m + 1, r, rt << 1 | 1);
            }
            pushUp(temporary, rt, ll, rr);
        }
    }
}
