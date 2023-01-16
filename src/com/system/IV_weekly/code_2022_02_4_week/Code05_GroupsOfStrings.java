package com.system.IV_weekly.code_2022_02_4_week;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/2/26-12:21
 * @description https://leetcode.com/problems/groups-of-strings/
 */
public class Code05_GroupsOfStrings {

    public static int[] groupStrings1(String[] words) {
        int n = words.length;
        // 0 1 2 ... n-1
        UnionFind uf = new UnionFind(n);
        int[] strs = new int[n];
        // abd -> 0..01011  7
        // 0..01011 key   value 7
        HashMap<Integer, Integer> stands = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int status = 0;
            for (char c : words[i].toCharArray()) {
                status |= 1 << (c - 'a');
            }
            strs[i] = status;
            if (stands.containsKey(status)) {
                uf.union(stands.get(status), i);
            } else {
                stands.put(status, i);
            }
        }
        for (int i = 0; i < n; i++) {
            // 一个字符串，状态
            int status = strs[i];
            for (int j = 0; j < 26; j++) {
                uf.union(i, stands.get(status | (1 << j)));
            }
            // 有的字符，减少一遍
            for (int j = 0; j < 26; j++) {
                if ((status & (1 << j)) != 0) {
                    uf.union(i, stands.get(status ^ (1 << j)));
                }
            }
            for (int has = 0; has < 26; has++) {
                if ((status & (1 << has)) != 0) {
                    status ^= 1 << has;
                    for (int replace = 0; replace < 26; replace++) {
                        uf.union(i, stands.get(status | (1 << replace)));
                    }
                    status |= 1 << has;
                }
            }
        }
        return new int[]{uf.sets(), uf.maxSize()};
    }

    /*
    ====================================================================================================================
     */

    public static int[] groupStrings2(String[] words) {
        int n = words.length;
        UnionFind uf = new UnionFind(n);
        int[] strs = new int[n];
        // key : 状态  value : 位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int status = 0;
            for (char c : words[i].toCharArray()) {
                status |= 1 << (c - 'a');
            }
            strs[i] = status;
            if (map.containsKey(status)) {
                uf.union(map.get(status), i);
            } else {
                map.put(status, i);
            }
        }
        for (int i = 0; i < n; i++) {
            int yes = strs[i], no = (~yes) & ((1 << 26) - 1);
            int tmpYes = yes, tmpNo = no;
            int rightOneYes = 0, rightOneNo = 0;
            while (tmpYes != 0) {
                rightOneYes = tmpYes & (-tmpYes);
                uf.union(i, map.get(yes ^ rightOneYes));
                tmpYes ^= rightOneYes;
            }
            while (tmpNo != 0) {
                rightOneNo = tmpNo & (-tmpNo);
                uf.union(i, map.get(yes | rightOneNo));
                tmpNo ^= rightOneNo;
            }
            tmpYes = yes;
            while (tmpYes != 0) {
                rightOneYes = tmpYes & (-tmpYes);
                tmpNo = no;
                while (tmpNo != 0) {
                    rightOneNo = tmpNo & (-tmpNo);
                    uf.union(i, map.get((yes ^ rightOneYes) | rightOneNo));
                    tmpNo ^= rightOneNo;
                }
                tmpYes ^= rightOneYes;
            }
        }
        return new int[]{uf.sets(), uf.maxSize()};
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(Integer i, Integer j) {
            if (i == null || j == null) {
                return;
            }
            int fi = find(i);
            int fj = find(j);
            if (fi != fj) {
                if (size[fi] >= size[fj]) {
                    size[fi] += size[fj];
                    parent[fj] = fi;
                } else {
                    size[fj] += size[fi];
                    parent[fi] = fj;
                }
            }
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public int sets() {
            int ans = 0;
            for (int i = 0; i < parent.length; i++) {
                ans += parent[i] == i ? 1 : 0;
            }
            return ans;
        }

        public int maxSize() {
            int ans = 0;
            for (int i = 0; i < size.length; i++) {
                ans = Math.max(ans, size[i]);
            }
            return ans;
        }
    }
}
