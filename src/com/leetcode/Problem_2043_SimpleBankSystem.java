package com.leetcode;

/**
 * @author ycb
 * @date 2022/3/18-8:19
 */
public class Problem_2043_SimpleBankSystem {

    class Bank {
        private long[] balance;

        public Bank(long[] balance) {
            this.balance = balance;
        }

        public boolean check(int acct) {
            return 1 <= acct && acct <= balance.length;
        }

        public boolean transfer(int a, int b, long money) {
            if (!check(a) || !check(b)) return false;
            if (balance[a - 1] >= money) {
                balance[a - 1] -= money;
                balance[b - 1] += money;
                return true;
            }
            return false;
        }

        public boolean deposit(int a, long money) {
            if (!check(a)) {
                return false;
            }
            balance[a - 1] += money;
            return true;
        }

        public boolean withdraw(int a, long money) {
            if (!check(a) || balance[a - 1] < money) {
                return false;
            }
            balance[a - 1] -= money;
            return true;
        }
    }
}
