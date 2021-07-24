package com.test;

/**
 * @Author ycb
 * @Date 2021/7/17-14:04
 */
public class KMP {

    // str串中能否配出match串
    // 能配出，返回第一次匹配成功的起始位置，不能配出，返回-1
    // 比如：str= a a a b c c a b   match=ab
    //           0 1 2 3 4 5 6 7
    // str中可以匹配成功两次，分别2位置和6位置
    // 返回第一次匹配成功的位置：2
    public static int getIndexOf(String str, String match) {
        // 无效参数
        if (str == null || match == null || match.length() < 1 || match.length() > str.length()) {
            return -1;
        }
        char[] str1 = str.toCharArray();
        char[] str2 = match.toCharArray();
        // 获取next信息数组
        int[] next = getNextArray(str2);
        // str串中的位置指针
        int x = 0;
        // match串中的位置指针
        int y = 0;
        while (x < str1.length && y < str2.length) {
            // str串当前位置的字符与match串当前位置的字符匹配成功，跳下一位去进行验证
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) { // match已经不能再往左跳了，str换个开头吧
                x++;
            } else { // match还能往左跳，取出next信息，告诉我该跳到哪个位置
                y = next[y];
            }
        }
        // 从while出来了
        // 情况一:y先越界,代表配出来了,开始位置为x - y
        // 情况二:x先越界,但是还不知道是否配成功，所以需要判断 y == str2.length
        //       true:配成功了 所以开始位置为x - y    false:配不出来，返回-1
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] match) {
        // 边界判断
        if (match.length == 1) {
            return new int[]{-1};
        }
        // 准备一个next数组收集match中每个位置的next信息
        int[] next = new int[match.length];
        // 0位置的字符next信息为-1
        next[0] = -1;
        // 1位置的字符next信息为0
        next[1] = 0;
        // 目前在哪个位置求next信息
        int index = 2;
        // 根据我们讲述的流程，想知道i位置next信息
        // 永远是i-1的位置字符，和某一段前缀串的下一个位置的字符再进行比较
        // cn代表当前是哪个位置的字符在于i-1位置的字符进行比较
        // 简单说就是哪个位置的next信息
        // 因为我们就是根据next信息去进行比对，根据比对的结果决定是否往前跳
        int cn = 0;
        while (index < next.length) {
            // 当前来到index位置,如果index-1位置的值与cn位置的值相等
            if (match[index - 1] == match[cn]) {
                // 既记录当前位置的next信息值，也把index指针向后移动一位,继续去求解下一个位置next信息
                next[index++] = ++cn;
            } else if (cn > 0) { // 如果cn>0,也就是说如果匹配失败了，我还能往前跳
                cn = next[cn];
            } else {
                // 当前位置的next信息为0，去下一个位置继续求它的next信息吧
                next[index++] = 0;
            }
        }
        return next;
    }


    // 暴力解
    public static int func(String str, String match) {
        char[] str1 = str.toCharArray();
        char[] str2 = match.toCharArray();
        int N = 0;
        int M = 0;
        while (N < str1.length && M < str2.length) {
            // 当前字符匹配成功
            if (str1[N] == str2[M]) {
                N++;
                M++;
            } else { // 匹配失败
                N = N - M + 1;
                M = 0;
            }
        }
        if (M == str2.length) {
            return N - M;
        } else {
            return -1;
        }
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20000;
        int matchSize = 50;
        int testTimes = 50000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != func(str, match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
