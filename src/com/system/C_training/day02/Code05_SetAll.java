package com.system.C_training.day02;

import java.util.HashMap;

/**
 * @Author ycb
 * @Date 2021/7/18-22:27
 * @Description 设计有setAll功能的哈希表
 * <p>
 * put、get、setAll方法，时间复杂度O(1)
 */
public class Code05_SetAll {

    public static class MyValue<V> {
        public V value;
        public long time;

        public MyValue(V v, long t) {
            this.value = v;
            this.time = t;
        }
    }

    public static class MyHashMap<K, V> {
        private HashMap<K, MyValue<V>> map;
        private long time;
        private MyValue<V> setAll;

        public void put(K key, V value) {
            map.put(key, new MyValue<V>(value, time++));
        }

        public void setAll(V value) {
            setAll = new MyValue<>(value, time++);
        }

        public V get(K key) {
            if (!map.containsKey(key)) {
                return null;
            }
            if (map.get(key).time > setAll.time) {
                return map.get(key).value;
            } else {
                return setAll.value;
            }
        }
    }
}
