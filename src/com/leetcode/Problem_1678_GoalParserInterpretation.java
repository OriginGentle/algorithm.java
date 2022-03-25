package com.leetcode;

/**
 * @author ycb
 * @date 2022/3/25
 * @desc
 */
public class Problem_1678_GoalParserInterpretation {

    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') {
                sb.append("G");
            } else if (command.charAt(i) == '(' && command.charAt(i + 1) == ')') {
                sb.append("o");
                i++;
            } else {
                sb.append("al");
                i += 3;
            }
        }
        return sb.toString();
    }
}
