package com.system.D_weekly.code_2023_08_2_week;

import java.io.*;

/**
 * @author ycb
 * @date 2023/8/6-14:05
 * @desc 有一个长度为 n 的数组，{ a1, a2, ... an }
 * 有m次询问，每次询问一个区间内最小没有出现过的自然数
 * 测试链接 : https://www.luogu.com.cn/problem/P4137
 */
public class Code05_QueryRangeNoAppearMinNumber {

    public static int MAXN = 200001;

    public static int[] root = new int[MAXN];

    // 正常来讲，可持久化线段树要开MAXN * 32的空间
    // 不过洛谷对java提交的空间判断很苛刻，直接开32倍空间会爆内存
    // 如果是C++写的话可以直接开32倍空间，也不会超内存
    // 对java不友好，哈哈，这是洛谷对java的歧视
    // 所以需要改成刚够用的情况，假设需要支持的范围是0~n
    // 那么就看看2的几次方刚刚>=n，假设2的k次方刚刚>=n
    // 那么每一个版本的线段树，在大范围上不断二分，至少需要k+1个新空间
    // 一共有n个版本的线段树(可持久化线段树)，所以一共开n*(k+1)的空间
    public static int MAXM;

    static {
        int k = 1;
        while ((1 << k) < MAXN) {
            k++;
        }
        MAXM = MAXN * (k + 1);
    }

    public static int[] left = new int[MAXM];

    public static int[] right = new int[MAXM];

    // 最重要的信息 : l ~ r范围上，所有数字最右出现的位置中，最左是在哪
    public static int[] last = new int[MAXM];

    public static int n, m, cnt;

    public static int update(int pre, int l, int r, int val, int pos) {
        int rt = ++cnt;
        left[rt] = left[pre];
        right[rt] = right[pre];
        last[rt] = last[pre];
        if (l == r) {
            last[rt] = pos;
        } else {
            int mid = (l + r) / 2;
            if (val <= mid) {
                left[rt] = update(left[pre], l, mid, val, pos);
            } else {
                right[rt] = update(right[pre], mid + 1, r, val, pos);
            }
            last[rt] = Math.min(last[left[rt]], last[right[rt]]);
        }
        return rt;
    }

    public static int query(int rt, int l, int r, int pos) {
        if (l == r) {
            return l;
        }
        int mid = (l + r) / 2;
        if (last[left[rt]] < pos) {
            // l...mid范围上，所有数字最右出现的位置中，最左的位置在pos以左
            // 说明l...mid范围上，一定有缺失的数字
            return query(left[rt], l, mid, pos);
        } else {
            // l...mid范围上，所有数字最右出现的位置中，最左的位置在pos以右
            // 说明l...mid范围上，没有缺失的数字
            // 那么缺失的数字一定在右侧！mid+1....r
            // 因为l...r一定有缺失的数字才会来到这个范围的
            // 如果左侧不缺失，那缺失的数字一定在右侧范围上
            return query(right[rt], mid + 1, r, pos);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            cnt = 0;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                int value = (int) in.nval;
                if (value >= n) {
                    root[i] = root[i - 1];
                } else {
                    root[i] = update(root[i - 1], 0, n, value, i);
                }
            }
            for (int i = 1; i <= m; i++) {
                in.nextToken();
                int l = (int) in.nval;
                in.nextToken();
                int r = (int) in.nval;
                out.println(query(root[r], 0, n, l));
            }
            out.flush();
        }
    }
}
