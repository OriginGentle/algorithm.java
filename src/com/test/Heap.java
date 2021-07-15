package com.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author ycb
 * @Date 2021/7/8-14:24
 */
// 堆 堆排序
public class Heap {
    // 堆结构
    private int[] heap;
    // 堆的容量
    private final int limit;
    // 位置指针
    private int heapSize;

    public Heap(int limit) {
        heap = new int[limit];
        this.limit = limit;
        heapSize = 0;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 新增的元素，现在停在index位置，不断往上看
    // 依次与父节点进行pk，赢了就交换，直到干不过父节点
    private static void heapInsert(int[] arr, int index) {
        /**
         * 注意：隐含的条件
         * index = 0 时，(index - 1) / 2 == 0，自己和自己比较，条件不成立
         */
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 从index位置往下看，不断往下沉
    // 停止条件：较大的孩子都不再比index位置的数大 或 没有孩子
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) { // 说明有左孩子,可能没有右孩子
            // ① left + 1 < heapSize : 判断有没有右孩子,注意heapSize的定义，条件<=其实是不成立的
            // ② 左右孩子比较，拿到较大孩子的位置
            int largest = left + 1 < heapSize &&
                    arr[left + 1] > arr[left] ? left + 1 : left;
            // 父节点与较大的孩子进行pk，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == limit;
    }

    public void push(int val) {
        if (heapSize == limit) {
            throw new RuntimeException("heap is full");
        }
        heap[heapSize] = val;
        heapInsert(heap, heapSize++);
    }

    public int pop() {
        if (heapSize == 0) {
            throw new RuntimeException("heap is full");
        }
        int ans = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return ans;
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 建堆（大跟堆）--> 从上到下 --> O(N * logN)
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // 建堆 --> 从下到上 --> O(N)
//        for (int i = arr.length - 1; i >= 0; i--) {
//            heapify(arr, i, arr.length);
//        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 6, 8, 7, 1};
        heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}

// 加强堆
class HeapGreater<T> {

    // 堆结构
    private ArrayList<T> heap;
    // key:元素  value:位置
    private HashMap<T, Integer> indexMap;
    // 位置指针
    private int heapSize;
    // 比较器
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    // 交换、更新位置信息
    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    // 获取堆顶元素
    public T peek() {
        return heap.get(0);
    }

    // 判断元素是否在堆中
    public boolean contains(T o) {
        return indexMap.containsKey(o);
    }

    public void push(T o) {
        heap.add(o);
        indexMap.put(o, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    // 删除指定的元素
    public void remove(T o) {
        // 获取堆上最后一个元素
        T replace = heap.get(heapSize - 1);
        // 获取指定元素的位置
        int index = indexMap.get(o);
        // 从索引表中删除指定元素位置信息
        indexMap.remove(o);
        // 最后位置的元素从堆中断连
        heap.remove(--heapSize);
        // 注意:删除的元素可能就是堆中的最后一个元素 --> 不进行后续操作
        if (o != replace) {
            // 删除的不是最后一个元素
            // 用最后一个元素去替删除元素
            heap.set(index, replace);
            // 替换之后更新位置信息
            indexMap.put(replace, index);
            // 调整堆结构
            resign(replace);
        }
    }

    // 获取堆上所有的元素
    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T o : heap) {
            ans.add(o);
        }
        return ans;
    }

    // 调整堆结构 --> 两个方法只会执行一个,时间复杂度O(log N)
    public void resign(T o) {
        heapInsert(indexMap.get(o));
        heapify(indexMap.get(o));
    }

    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize
                    && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            largest = comp.compare(heap.get(largest), heap.get(index)) < 0 ? largest : index;
            if (largest == index) {
                break;
            }
            swap(largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

}