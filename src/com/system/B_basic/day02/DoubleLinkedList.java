package com.system.B_basic.day02;

/**
 * @Author ycb
 * @Date 2021/2/8-16:44
 * @Description 双向链表的实现
 */
public class DoubleLinkedList<T> {

    // 链表中的节点
    public class Node<T> {
        public T value;
        Node next;
        Node prev;

        public Node(T data) {
            value = data;
        }
    }

    private Node<T> head; // 头结点
    private Node<T> tail; // 尾节点
    private Node<T> other; // 临时节点
    private int size;

    // 保证每次初始化的时候链表为空
    public DoubleLinkedList() {
        head = new Node<T>(null);
        tail = head;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void add(T data) {
        if (isEmpty()) {
            head = new Node<T>(data);
            tail = head;
        } else {
            other = new Node<T>(data);
            other.prev = tail;
            tail.next = other;
            tail = other;
        }
        size++;
    }

    public void add(T data, Integer index) {
        if (index > size || index < 0) {
            throw new IllegalArgumentException("index illegal");
        } else if (index == size) {
            add(data);
        } else {
            for (int i = 0; i < index - 1; i++) {
                other = head.next;
            }
            Node next = other.next;
            Node<T> cur = new Node<>(data);
            other.next = cur;
            cur.prev = other;
            cur.next = next;
            next.prev = cur;
            size++;
        }
    }

    public void remove(T data) {
        other = head;
        while (other != null) {
            if (other.value.equals(data)) {
                other.prev.next = other.next;
                size--;
            }
            other = other.next;
        }
    }

    // 打印链表中的节点
    public void display() {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(2);
        list.display();
    }

}
