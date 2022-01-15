package com.weekly.code_2022_01_2_week;

/**
 * @author ycb
 * @date 2022/1/15-12:16
 * @description 给定一个非常大的List<String> list
 * 每一个字符串类似 : "hello,world,have,hello,world"
 * 这一个字符串中，有2个hello，2个world，1个have
 * 请设计一种多线程处理方案，统计list中每一个字符串，切分出来的单词数量，并且汇总
 * 最终返回一个HashMap<String, Integer>表示每个字符串在list中一共出现几次
 */
public class Code01_StringCounts {
    // 准备两个线程池,一个读取线程池，一个处理线程池
    // 读取字符串的线程池，随机均分交给处理线程池的执行线程进行处理
}
