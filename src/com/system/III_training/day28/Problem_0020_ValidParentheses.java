package com.system.III_training.day28;

/**
 * @author ycb
 * @since 2021/10/14-8:34
 */
public class Problem_0020_ValidParentheses {

    public static boolean isValid(String s) {
        if (s == null || s.equals("")) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] stack = new char[str.length];
        int size = 0;
        for (int i = 0; i < str.length; i++) {
            char ch = str[i];
            // 遇到 '(' , '[' , '{' 就压栈
            if (ch == '(' || ch == '[' || ch == '{') {
                stack[size++] = ch == '(' ? ')' : (ch == '[' ? ']' : '}');
            } else {
                if (size == 0) {
                    return false;
                }
                char last = stack[--size];
                if (last != str[i]) {
                    return false;
                }
            }
        }
        return size == 0;
    }
}
