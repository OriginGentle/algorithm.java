package com.leetcode.questions.problem_1401_1600;

import com.leetcode.questions.problem_1201_1400.Problem_1252_CellsWithOddValuesInAMatrix;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2023/3/3-13:15
 */
public class Problem_1487_MakingFileNamesUnique {

    public static String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        int n = names.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(names[i])) {
                int k = map.get(names[i]);
                while (map.containsKey(addSuffix(names[i], k))) {
                    k++;
                }
                map.put(names[i], k);
                names[i] = addSuffix(names[i], k);
            }
            map.put(names[i], 1);
        }
        return names;
    }

    public static String addSuffix(String name, int k) {
        return name + "(" + k + ")";
    }

    public static void main(String[] args) {
        String[] names = {"gta", "gta(1)", "gta", "avalon"};
        String[] folderNames = getFolderNames(names);
        for (String folderName : folderNames) {
            System.out.println(folderName);
        }
    }
}
