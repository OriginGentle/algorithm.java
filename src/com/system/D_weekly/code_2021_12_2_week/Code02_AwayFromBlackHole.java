package com.system.D_weekly.code_2021_12_2_week;

/**
 * @author ycb
 * @date 2021/12/9-8:24
 * @description 所有黑洞的中心点记录在holes数组里
 * 比如[[3,5] [6,9]]表示，第一个黑洞在(3,5)，第二个黑洞在(6,9)
 * 并且所有黑洞的中心点都在左下角(0,0)，右上角(x,y)的区域里
 * 飞船一旦开始进入黑洞，就会被吸进黑洞里
 * 返回如果统一所有黑洞的半径，最大半径是多少，
 * 依然能保证飞船从(0,0)能到达(x,y)
 */
public class Code02_AwayFromBlackHole {

    public static int maxRadius(int[][] holes, int x, int y) {
        int L = 1;
        int R = Math.max(x, y);
        int ans = 0;
        while (L <= R) {
            int M = (L + R) / 2;
            if (canTo(holes, x, y, M)) {
                ans = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return ans;
    }

    public static boolean canTo(int[][] holes, int x, int y, int r) {
        int N = holes.length;
        UnionFind uf = new UnionFind(holes, N, r);
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (touch(holes[i][0], holes[i][1], holes[j][0], holes[j][1], r)) {
                    uf.union(i, j);
                }
                if (uf.block(i, x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean touch(int x1, int y1, int x2, int y2, int r) {
        return (r << 1) >= Math.sqrt((Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2)));
    }

    public static class UnionFind {
        public int[] father;
        public int[] size;
        public int[] help;
        public int[] xMin;
        public int[] xMax;
        public int[] yMin;
        public int[] yMax;

        public UnionFind(int[][] holes, int n, int r) {
            father = new int[n];
            size = new int[n];
            xMin = new int[n];
            xMax = new int[n];
            yMin = new int[n];
            yMax = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                size[i] = 1;
                xMin[i] = holes[i][0] - r;
                xMax[i] = holes[i][0] + r;
                yMin[i] = holes[i][1] - r;
                yMax[i] = holes[i][1] + r;
            }
        }

        private int find(int i) {
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

        public void union(int i, int j) {
            int fi = find(i);
            int fj = find(j);
            if (fi != fj) {
                int si = size[fi];
                int sj = size[fj];
                int big = si >= sj ? fi : fj;
                int small = big == fi ? fj : fi;
                father[small] = big;
                size[big] = si + sj;
                xMin[big] = Math.min(xMin[big], xMin[small]);
                xMax[big] = Math.max(xMax[big], xMax[small]);
                yMin[big] = Math.min(yMin[big], yMin[small]);
                yMax[big] = Math.max(yMax[big], yMax[small]);
            }
        }

        public boolean block(int i, int x, int y) {
            i = find(i);
            return (xMin[i] <= 0 && xMax[i] >= x)
                    || (yMin[i] <= 0 && yMax[i] >= y)
                    || (xMin[i] <= 0 && yMin[i] <= 0)
                    || (xMax[i] >= x && yMax[i] >= y);
        }
    }

    public static void main(String[] args) {
        int[][] holes = { { 1, 2 }, { 4, 4 }, { 3, 0 }, { 5, 2 } };
        int x = 4;
        int y = 6;
        System.out.println(maxRadius(holes, x, y));
    }
}
