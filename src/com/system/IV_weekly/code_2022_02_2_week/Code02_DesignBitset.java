package com.system.IV_weekly.code_2022_02_2_week;

/**
 * @author ycb
 * @date 2022/2/11-18:22
 * @description https://leetcode-cn.com/problems/design-bitset/
 */
public class Code02_DesignBitset {

    class Bitset {
        private int[] bits;
        private int size;
        private int zeros;
        private int ones;
        private boolean reverse;

        public Bitset(int n) {
            // 向上取整
            bits = new int[(n + 31) / 32];
            size = n;
            zeros = n;
            ones = 0;
            reverse = false;
        }

        public void fix(int idx) {
            int index = idx / 32;
            int bit = idx % 32;
            // 如果没有翻转过
            if (!reverse) {
                if ((bits[index] & (1 << bit)) == 0) {
                    zeros--;
                    ones++;
                    bits[index] |= (1 << bit);
                }
            } else {
                if ((bits[index] & (1 << bit)) != 0) {
                    zeros--;
                    ones++;
                    bits[index] ^= (1 << bit);
                }
            }
        }

        public void unfix(int idx) {
            int index = idx / 32;
            int bit = idx % 32;
            if (!reverse) {
                if ((bits[index] & (1 << bit)) != 0) {
                    ones--;
                    zeros++;
                    bits[index] ^= (1 << bit);
                }
            } else {
                if ((bits[index] & (1 << bit)) == 0) {
                    ones--;
                    zeros++;
                    bits[index] |= (1 << bit);
                }
            }
        }

        public void flip() {
            reverse = !reverse;
            int tmp = zeros;
            zeros = ones;
            ones = tmp;
        }

        public boolean all() {
            return ones == size;
        }

        public boolean one() {
            return ones > 0;
        }

        public int count() {
            return ones;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                int status = bits[i / 32] & (1 << (i % 32));
                sb.append(reverse ? (status == 0 ? '1' : '0') : (status == 0 ? '0' : '1'));
            }
            return sb.toString();
        }
    }
}
