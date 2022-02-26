package com.weekly.code_2022_02_4_week;

/**
 * @author ycb
 * @date 2022/2/26-12:18
 * @description 来自微软
 * 比如，str = "ayxbx"
 * 有以下4种切法 : a | yxbx、ay | xbx、ayx | bx、ayxb | x
 * 其中第1、3、4种切法符合：x和y的个数，至少在左右两块中的一块里有相同的数量
 * 所以返回3
 * 给定一个字符串str，长度为N
 * 你有N-1种划分方法，把str切成左右两半，返回有几种切法满足：
 * x和y的个数，至少在左右两块中的一块里有相同的数量
 */
public class Code01_SplitSameNumberWays {

    public static int splitSameNumberWays(char[] str) {
        if (str == null || str.length == 0) {
            return 0;
        }
        int allX = 0, allY = 0;
        for (char ch : str) {
            allX += ch == 'x' ? 1 : 0;
            allY += ch == 'y' ? 1 : 0;
        }
        int leftX = str[0] == 'x' ? 1 : 0;
        int leftY = str[0] == 'y' ? 1 : 0;
        int ans = 0;
        for (int i = 1; i < str.length; i++) {
            if ((leftX == leftY) || (allX - leftX == allY - leftY)) {
                ans++;
            }
            leftX += str[i] == 'x' ? 1 : 0;
            leftY += str[i] == 'y' ? 1 : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "ayxbx";
        int ans = splitSameNumberWays(str.toCharArray());
        System.out.println(ans);
    }
}
