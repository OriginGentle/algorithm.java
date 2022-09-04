package com.weekly.code_2022_08_5_week;

/**
 * @author ycb
 * @date 2022/9/2-16:48
 * @desc nim博弈 & 巴什博弈
 * 有a块草莓蛋糕，有b块芝士蛋糕，两人轮流拿蛋糕
 * 每次不管是谁只能选择在草莓蛋糕和芝士蛋糕中拿一种
 * 拿的数量在1~m之间随意
 * 谁先拿完最后的蛋糕谁赢
 * 返回先手赢还是后手赢
 */
public class Code01_Cakes {

    public static final String BEFORE_HAND = "先手";

    public static final String BACK_HAND = "后手";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 草莓蛋糕a块
    // 芝士蛋糕b快
    // 每次在任意一种蛋糕上选 1-m 块
    // 返回谁会赢，"先手" or "后手"
    public static String[][][] dp = new String[101][101][101];

    public static String whoWin1(int a, int b, int m) {
        if (m >= Math.max(a, b))  // nim博弈
            return a != b ? BEFORE_HAND : BACK_HAND;

        if (a == b)
            // 蛋糕一样多，不论先手怎么拿
            // 后手和先手选择一样的数量
            // 先手必输，后手必赢
            return BACK_HAND;

        if (dp[a][b][m] != null)
            return dp[a][b][m];

        String ans = BACK_HAND;

        for (int pick = 1; pick <= Math.min(a, m); pick++) {

            if (whoWin1(a - pick, b, m).equals(BACK_HAND))
                ans = BEFORE_HAND;

            if (ans.equals(BEFORE_HAND))
                break;

        }

        for (int pick = 1; pick <= Math.min(b, m); pick++) {

            if (whoWin1(a, b - pick, m).equals(BACK_HAND))
                ans = BEFORE_HAND;

            if (ans.equals(BEFORE_HAND))
                break;

        }

        dp[a][b][m] = ans;
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static String whoWin2(int a, int b, int m) {
        if (m >= Math.max(a, b))
            return a != b ? BEFORE_HAND : BACK_HAND;

        if (a == b)
            return BACK_HAND;

        // 如果 a != b
        // 关注a和b的差值，
        // 谁最先遇到差值为0，谁输
        // 巴什博奕理念
        // 差值蛋糕数量共rest个。
        // 每次从最少取1个，最多取m个，最后取光的人取胜。
        // 如果rest=(m+1)*k + s (s!=0) 那么先手一定必胜
        // 因为第一次取走s个，
        // 接下来无论对手怎么取，
        // 先手都能保证取到所有(m+1)倍数的点，
        // 那么循环下去一定能取到差值最后一个。
        int rest = Math.max(a, b) - Math.min(a, b);
        return (rest % (m + 1)) != 0 ? BEFORE_HAND : BACK_HAND;
    }

    public static void main(String[] args) {
        int V = 100;
        System.out.println("测试开始");
        int cnt = 0;
        int all = (V + 1) * (V + 1) * (V + 1);
        for (int a = 0; a <= V; a++) {
            for (int b = 0; b <= V; b++) {
                for (int m = 0; m <= V; m++) {
                    String ans1 = whoWin1(a, b, m);
                    String ans2 = whoWin2(a, b, m);
                    if (!ans1.equals(ans2)) {
                        System.out.println("出错了！");
                        System.out.println("a : " + a);
                        System.out.println("b : " + b);
                        System.out.println("m : " + m);
                        System.out.println("ans1 : " + ans1);
                        System.out.println("ans2 : " + ans2);
                        break;
                    }
                    cnt++;
                    if (cnt % 1000 == 0) {
                        System.out.println("已经测了" + cnt + "组(共" + all + "组)");
                    }
                }
            }
        }
        System.out.println("测试结束");
    }
}
