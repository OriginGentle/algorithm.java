package com.system.C_training.day09;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ycb
 * @date 2021/8/24-8:22
 * @description https://leetcode.com/problems/russian-doll-envelopes/
 */
public class Code04_EnvelopesProblem {

    public static int maxEnvelopes(int[][] matrix) {
        Envelope[] envelopes = sort(matrix);
        int[] end = new int[matrix.length];
        end[0] = envelopes[0].h;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < envelopes.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (envelopes[i].h > end[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            end[l] = envelopes[i].h;
        }
        return right + 1;
    }

    public static class Envelope {
        public int h;
        public int l;

        public Envelope(int weight, int height) {
            l = weight;
            h = height;
        }
    }

    public static class EnvelopeComparator implements Comparator<Envelope> {
        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.l != o2.l ? o1.l - o2.l : o2.h - o1.h;
        }
    }

    public static Envelope[] sort(int[][] matrix) {
        Envelope[] res = new Envelope[matrix.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(res, new EnvelopeComparator());
        return res;
    }

}
