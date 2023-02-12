package com.system.C_training.day01;

import java.io.File;
import java.util.Stack;

/**
 * @Author ycb
 * @Date 2021/7/13-11:48
 * @Description 给定一个文件目录的路径，
 * 写一个函数统计这个目录下所有的文件数量并返回
 * 隐藏文件也算，但是文件夹不算
 */
public class Code02_CountFiles {

    // 深度优先遍历
    public static int getFilesNum(String folderPath) {
        File root = new File(folderPath);
        if (root.isFile()) {
            return 0;
        }
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int count = 0;
        // 是文件夹就压栈
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    stack.push(file);
                }
                if (file.isFile()) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String folderPath = "E:\\Program Files (x86)";
        System.out.println(getFilesNum(folderPath));
    }

}
