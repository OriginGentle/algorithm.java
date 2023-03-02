package com.leetcode.questions.problem_interview;

/**
 * @author ycb
 * @date 2023/3/2-13:16
 */
public class Problem_05_02_BianryNumberToStringLCCI {

    public String printBin(double num) {
        StringBuilder sb = new StringBuilder("0.");
        while (sb.length() <= 32 && num != 0) {
            num *= 2;
            int bit = (int) num;
            sb.append(bit);
            num -= bit;
        }
        return sb.length() <= 32 ? sb.toString() : "ERROR";
    }
}
