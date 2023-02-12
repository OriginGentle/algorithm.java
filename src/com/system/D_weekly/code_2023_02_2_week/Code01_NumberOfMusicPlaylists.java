package com.system.D_weekly.code_2023_02_2_week;

/**
 * @author ycb
 * @date 2023/2/12-00:05
 * @desc 你的音乐播放器里有 N 首不同的歌
 * 在旅途中，你的旅伴想要听 L 首歌不一定不同，即允许歌曲重复
 * 请你为她按如下规则创建一个播放列表
 * 每首歌至少播放一次
 * 一首歌只有在其他 K 首歌播放完之后才能再次播放
 * 返回可以满足要求的播放列表的数量
 * 由于答案可能非常大，请返回它模 10^9 + 7 的结果
 * 测试链接 : https://leetcode.cn/problems/number-of-music-playlists/
 */
public class Code01_NumberOfMusicPlaylists {

    public static final int MOD = (int) 1e9 + 7;
    public static final int LIMIT = 100;

    // 阶乘表
    public static long[] fac = new long[LIMIT + 1];
    // 阶乘结果的乘法逆元表
    public static long[] inv = new long[LIMIT + 1];

    static {
        fac[0] = inv[0] = 1;
        for (int i = 1; i <= LIMIT; i++) {
            fac[i] = ((long) i * fac[i - 1]) % MOD;
        }

        // 费马小定理计算乘法逆元
        // 优化技巧：阶乘的逆元倒推
        inv[LIMIT] = power(fac[LIMIT], MOD - 2);
        for (int i = LIMIT; i > 1; i--) {
            inv[i - 1] = ((long) i * inv[i]) % MOD;
        }
    }

    // x的n次方，% mod之后，是多少？
    // 快速幂技巧
    public static long power(long x, int n) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = (ans * x) % MOD;
            }
            x = (x * x) % MOD;
            n >>= 1;
        }
        return ans;
    }

    // N * logN
    public static int numMusicPlaylists(int n, int l, int k) {
        long cur, ans = 0, sign = 1;
        for (int i = 0; i <= n - k; i++, sign = sign == 1 ? (MOD - 1) : 1) {
            cur = (sign * power(n - k - i, l - k)) % MOD;
            cur = (cur * fac[n]) % MOD;
            cur = (cur * inv[i]) % MOD;
            cur = (cur * inv[n - k - i]) % MOD;
            ans = (ans + cur) % MOD;
        }
        return (int) ans;
    }
}
