package com.system.III_training.day20;

import java.util.HashMap;

/**
 * @author ycb
 * @date 2021/10/3-11:41
 * @description https://leetcode.com/problems/largest-component-size-by-common-factor/
 */
public class Code02_LargestComponentSizeByCommonFactor {

    public static int largestComponentSize1(int[] arr) {
        int N = arr.length;
        UnionFind set = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (gcd(arr[i], arr[j]) != 1) {
                    set.union(i, j);
                }
            }
        }
        return set.maxSize();
    }

    /*
    ====================================================================================================================
     */

    public static int largestComponentSize2(int[] arr) {
        int N = arr.length;
        // arr中，N个位置，在并查集初始时，每个位置自己是一个集合
        UnionFind unionFind = new UnionFind(N);
        // key:某个因子  value:哪个位置拥有这个因子
        HashMap<Integer, Integer> factorsMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            int limit = (int) Math.sqrt(num);
            for (int j = 1; j <= limit; j++) {
                if (num % j == 0) {
                    if (j != 1) {
                        if (!factorsMap.containsKey(j)) {
                            factorsMap.put(j, i);
                        } else {
                            unionFind.union(factorsMap.get(j), i);
                        }
                    }
                    int other = num / j;
                    if (other != 1) {
                        if (!factorsMap.containsKey(other)) {
                            factorsMap.put(other, i);
                        } else {
                            unionFind.union(factorsMap.get(other), i);
                        }
                    }
                }
            }
        }
        return unionFind.maxSize();
    }

    // 辗转相除法：获取两数的最大公约数
    // 时间复杂度：O(1)
    // a,b是正数，不能有任何一个数等于0
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 并查集
    public static class UnionFind {
        private int[] parents;
        private int[] sizes;
        private int[] help;

        public UnionFind(int N) {
            parents = new int[N];
            sizes = new int[N];
            help = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public int maxSize() {
            int ans = 0;
            for (int size : sizes) {
                ans = Math.max(ans, size);
            }
            return ans;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parents[i]) {
                help[hi++] = i;
                i = parents[i];
            }
            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                int big = sizes[f1] >= sizes[f2] ? f1 : f2;
                int small = big == f1 ? f2 : f1;
                parents[small] = big;
                sizes[big] = sizes[f1] + sizes[f2];
            }
        }
    }
}
