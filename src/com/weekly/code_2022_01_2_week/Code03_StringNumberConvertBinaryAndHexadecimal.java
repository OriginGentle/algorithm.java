package com.weekly.code_2022_01_2_week;

/**
 * @author ycb
 * @date 2022/1/15-12:17
 * @description 来自兴业数金
 * 给定一个字符串形式的数，比如"3421"或者"-8731"
 * 如果这个数不在-32768~32767范围上，那么返回"NODATA"
 * 如果这个数在-32768~32767范围上，那么这个数就没有超过16个二进制位所能表达的范围
 * 返回这个数的2进制形式的字符串和16进制形式的字符串，用逗号分割
 */
public class Code03_StringNumberConvertBinaryAndHexadecimal {

    public static String convert(String num) {
        // 根据题意：num的最大长度为6
        if (num == null || num.length() == 0 || num.length() > 6) {
            return "NODATA";
        }
        int n = Integer.valueOf(num);
        // 如果转换完成后超过了范围，那么返回"NODATA"
        if (n < -32768 || n > 32767) {
            return "NODATA";
        }
        // 提取出第15位符号位信息 : 如果n<0，第15位就是1，如果n>=0第15位就是0
        // 提取出n的14位~0位的信息 : 也就是(n & 65535)
        int info = (n < 0 ? (1 << 15) : 0) | (n & 65535);
        StringBuilder sb = new StringBuilder();
        // 依次提取二进制信息很简单
        for (int i = 15; i >= 0; i--) {
            sb.append(((1 << i) & info) != 0 ? '1' : '0');
        }
        sb.append(",0x");
        // 依次提取16进制的时候，每4位提取
        for (int i = 12; i >= 0; i -= 4) {
            int cur = (info & (15 << i)) >> i;
            if (cur < 10) {
                sb.append(cur);
            } else {
                switch (cur) {
                    case 10:
                        sb.append('a');
                        break;
                    case 11:
                        sb.append('b');
                        break;
                    case 12:
                        sb.append('c');
                        break;
                    case 13:
                        sb.append('d');
                        break;
                    case 14:
                        sb.append('e');
                        break;
                    default:
                        sb.append('f');
                        break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "0";
        System.out.println(convert(num1));

        String zuo = "457";
        System.out.println(convert(zuo));

        String num2 = "-32768";
        System.out.println(convert(num2));

        String num3 = "32768";
        System.out.println(convert(num3));

        String num4 = "32767";
        System.out.println(convert(num4));

        String num5 = "-1";
        System.out.println(convert(num5));
    }
}
