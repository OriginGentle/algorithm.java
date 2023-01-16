package com.system.IV_weekly.code_2022_08_1_week;

import java.util.ArrayList;

/**
 * @author ycb
 * @date 2022/8/5-11:10
 * @desc https://leetcode.cn/problems/online-majority-element-in-subarray/
 */
public class Code03_OnlineMajorityElementInSubarray {

    class MajorityChecker {

        SegmentTree segmentTree;

        CountQuicker countQuicker;

        public MajorityChecker(int[] arr) {
            segmentTree = new SegmentTree(arr);
            countQuicker = new CountQuicker(arr);
        }

        public int query(int left, int right, int threshold) {
            int cand = segmentTree.query(left, right);
            return countQuicker.realTimes(left, right, cand) >= threshold ? cand : -1;
        }
    }

    class CountQuicker {

        ArrayList<ArrayList<Integer>> cmt;

        public CountQuicker(int[] arr) {
            cmt = new ArrayList<>();
            int max = 0;
            for (int num : arr) {
                max = Math.max(max, num);
            }
            for (int i = 0; i <= max; i++) {
                cmt.add(new ArrayList<>());
            }
            for (int i = 0; i < arr.length; i++) {
                cmt.get(arr[i]).add(i);
            }
        }

        public int realTimes(int l, int r, int num) {
            ArrayList<Integer> indexs = cmt.get(num);
            return size(indexs, r) - size(indexs, l - 1);
        }

        private int size(ArrayList<Integer> indexs, int idx) {
            int l = 0, r = indexs.size() - 1;
            int ans = -1;
            while (l <= r) {
                int m = (l + r) >> 1;
                if (indexs.get(m) <= idx) {
                    ans = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return ans + 1;
        }
    }

    class SegmentTree {
        int n;
        int[] cand;
        int[] hp;

        public SegmentTree(int[] arr) {
            n = arr.length;
            cand = new int[(n + 1) << 2];
            hp = new int[(n + 1) << 2];
            build(arr, 1, n, 1);
        }

        private void build(int[] arr, int l, int r, int rt) {
            if (l == r) {
                cand[rt] = arr[l - 1];
                hp[rt] = 1;
            } else {
                int m = (l + r) >> 1;
                build(arr, l, m, rt << 1);
                build(arr, m + 1, r, rt << 1 | 1);
                int lc = cand[rt << 1], rc = cand[rt << 1 | 1];
                int lh = hp[rt << 1], rh = hp[rt << 1 | 1];
                if (lc == rc) {
                    cand[rt] = lc;
                    hp[rt] = lh + rh;
                } else {
                    cand[rt] = lh >= rh ? lc : rc;
                    hp[rt] = Math.abs(lh - rh);
                }
            }
        }

        public int query(int left, int right) {
            return query(left + 1, right + 1, 1, n, 1)[0];
        }

        private int[] query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return new int[]{cand[rt], hp[rt]};
            }
            int m = (l + r) >> 1;
            if (R <= m) {
                return query(L, R, l, m, rt << 1);
            } else if (L > m) {
                return query(L, R, m + 1, r, rt << 1 | 1);
            } else {
                int[] ansL = query(L, R, l, m, rt << 1);
                int[] ansR = query(L, R, m + 1, r, rt << 1 | 1);
                if (ansL[0] == ansR[0]) {
                    ansL[1] += ansR[1];
                    return ansL;
                } else {
                    if (ansL[1] >= ansR[1]) {
                        ansL[1] -= ansR[1];
                        return ansL;
                    } else {
                        ansR[1] -= ansL[1];
                        return ansR;
                    }
                }
            }
        }
    }
}
