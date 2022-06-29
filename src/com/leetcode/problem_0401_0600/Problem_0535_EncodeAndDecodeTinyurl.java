package com.leetcode.problem_0401_0600;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ycb
 * @date 2022/6/29-08:23
 */
public class Problem_0535_EncodeAndDecodeTinyurl {

    public class Codec {
        public Map<Integer, String> map = new HashMap<>();
        public Random random = new Random();
        public final String BASE_URL = "http://tinyurl.com/";

        public String encode(String longUrl) {
            int key;
            while (true) {
                key = random.nextInt();
                if (!map.containsKey(key)) {
                    break;
                }
            }
            map.put(key, longUrl);
            return BASE_URL + key;
        }

        public String decode(String shortUrl) {
            int pos = shortUrl.lastIndexOf('/') + 1;
            int key = Integer.parseInt(shortUrl.substring(pos));
            return map.get(key);
        }
    }
}
