package com.system.IV_weekly.code_2022_06_2_week;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/6/19-13:06
 * @desc
 */
public class Code01_MostStonesRemovedWithSameRowOrColumn {

    public static int removeStones(int[][] stones) {
        int n = stones.length;
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            int x = stones[i][0];
            int y = stones[i][1];
            if (!row.containsKey(x)) {
                row.put(x, i);
            } else {
                uf.union(i, row.get(x));
            }

            if (!col.containsKey(y)) {
                col.put(y, i);
            } else {
                uf.union(i, col.get(y));
            }
        }
        return n - uf.size();
    }

    public static class UnionFind {
        public int[] father;
        public int[] size;
        public int[] help;
        public int sets;

        public UnionFind(int n) {
            father = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                size[i] = 1;
            }
            sets = n;
        }

        private int find(int i) {
            int hi = 0;
            while (i != father[i]) {
                help[hi++] = i;
                i = father[i];
            }
            while (hi != 0) {
                father[help[--hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
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
                sets--;
            }
        }

        public int size() {
            return sets;
        }
    }
}
