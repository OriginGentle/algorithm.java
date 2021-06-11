package com.algorithm.day35;

/**
 * @Author ycb
 * @Date 2021/3/10-9:06
 * @Description AVL树 (搜索二叉树，有序表的一种实现方式)
 * 平衡因子：左树与右树的高度差不能大于1
 * (1)LL:右旋              RR:左旋
 * (2)LR:左旋->右旋         RL:右旋->左旋
 * (3)LL+LR:以LL为准，右旋  RR+RL:以RR为准，左旋
 */
public class AVLTree {

    // AVL树的节点
    public static class AVLNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public AVLNode<K, V> l; // 左指针
        public AVLNode<K, V> r; // 右指针
        public int h; // 高度

        public AVLNode(K key, V value) {
            k = key;
            v = value;
            h = 1;
        }
    }

    // AVL树的实现
    public static class AVLTreeMap<K extends Comparable<K>, V> {
        // 根节点
        private AVLNode<K, V> root;
        private int size;

        public AVLTreeMap() {
            root = null;
            size = 0;
        }

        // 右旋
        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) { // cur表示要当前需要调整的节点
            // 记录当前节点的左孩子
            AVLNode<K, V> left = cur.l;
            // left节点的右树直接挂到当前节点左树上
            cur.l = left.r;
            // 当前节点挂到left的右树上
            left.r = cur;
            // 更新高度 --> 更新平衡因子
            cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
            left.h = Math.max((left.l != null ? left.l.h : 0), (left.r != null ? left.r.h : 0)) + 1;
            return left;
        }

        // 左旋
        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) { // cur表示要当前需要调整的节点
            AVLNode<K, V> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            // 更新高度 --> 更新平衡因子
            cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
            right.h = Math.max((right.l != null ? right.l.h : 0), (right.r != null ? right.r.h : 0)) + 1;
            return right;
        }

        // 维持平衡方法
        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            // 获取当前节点的左树高度
            int leftHeight = cur.l != null ? cur.l.h : 0;
            // 获取当前节点的右树高度
            int rightHeight = cur.r != null ? cur.r.h : 0;
            if (Math.abs(leftHeight - rightHeight) > 1) {
                if (leftHeight > rightHeight) {
                    int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                    int leftRightHeight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
                    // LL类型 或者 LL+LR类型:以LL为准，只进行右旋
                    if (leftLeftHeight >= leftRightHeight) {
                        cur = rightRotate(cur);
                    } else {
                        // LR类型:左旋 -> 右旋
                        cur.l = leftRotate(cur.l);
                        cur = rightRotate(cur);
                    }
                } else {
                    int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
                    int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                    // RR类型 或者 RR+RL类型:以RR为准，只进行左旋
                    if (rightRightHeight >= rightLeftHeight) {
                        cur = leftRotate(cur);
                    } else {
                        // RL类型:右旋 -> 左旋
                        cur.r = rightRotate(cur.r);
                        cur = leftRotate(cur);
                    }
                }
            }
            return cur;
        }

        private AVLNode<K, V> findLastIndex(K key) {
            AVLNode<K, V> cur = root;
            AVLNode<K, V> pre = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.k) == 0) {
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return pre;
        }

        private AVLNode<K, V> findLastNoSmallIndex(K key) {
            AVLNode<K, V> ans = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    ans = cur;
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return ans;
        }

        private AVLNode<K, V> findLastNoBigIndex(K key) {
            AVLNode<K, V> ans = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                } else {
                    ans = cur;
                    cur = cur.r;
                }
            }
            return ans;
        }

        // 添加节点 --> 不接收重复的key
        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AVLNode<K, V>(key, value);
            } else {
                // 加入节点的key小于当前节点的key
                if (key.compareTo(cur.k) < 0) {
                    cur.l = add(cur.l, key, value);
                } else { // 加入节点的key大于当前节点的key
                    cur.r = add(cur.r, key, value);
                }
                cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
                return maintain(cur);
            }
        }

        // 删除节点，返回新的头部
        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
            if (key.compareTo(cur.k) > 0) {
                cur.r = delete(cur.r, key);
            } else if ((key.compareTo(cur.k) < 0)) {
                cur.l = delete(cur.l, key);
            } else {
                if (cur.l == null && cur.r == null) {
                    cur = null;
                } else if (cur.l == null && cur.r != null) {
                    cur = cur.r;
                } else if (cur.l != null && cur.r == null) {
                    cur = cur.l;
                } else {
                    AVLNode<K, V> des = cur.r;
                    while (des.l != null) {
                        des = des.l;
                    }
                    // 找到边界删除，并依次调整好子树的平衡
                    cur.r = delete(cur.r, des.k);
                    des.l = cur.l;
                    des.r = cur.r;
                    cur = des;
                }
            }
            if (cur != null) {
                cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            }
            return maintain(cur);
        }

        public int getSize() {
            return size;
        }

        // 对外提供，判断节点是否存在
        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.k) == 0 ? true : false;
        }

        // 对外提供，增加节点的方法
        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                lastNode.v = value;
            } else {
                size++;
                root = add(root, key, value);
            }
        }

        // 对外提供，删除节点的方法
        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                size--;
                root = delete(root, key);
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                return lastNode.v;
            }
            return null;
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.l != null) {
                cur = cur.l;
            }
            return cur.k;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.r != null) {
                cur = cur.r;
            }
            return cur.k;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.k;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.k;
        }
    }
}
