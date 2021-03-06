package com.peter.day02;

/**
 * @Author ycb
 * @Date 2021/2/8-16:44
 */
public class LinkedList<T> {
    // 链表的头结点
    private Node head;
    // 链表的长度
    private int size;

    // 保证每次初始化链表都是新的链表结构
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * 链表中的节点
     */
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 头插法
     */
    public boolean addFirstNode(T data) {
        Node node = new Node(data);
        node.next = this.head;
        this.head = node;
        this.size++;
        return true;
    }

    public boolean addLastNode(T data) {
        addNode(data, this.size);
        return true;
    }

    // 增加节点
    public boolean addNode(T data, int index) {
        // 判断索引的位置，是否在0 ~ size的范围内，超出范围抛异常
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index illegal");
        }
        // 索引的位置为0，调用头插法
        if (index == 0) {
            addFirstNode(data);
            return true;
        }
        Node preNode = head;
        // 找到要插入节点的前一个节点
        for (int i = 0; i < index - 1; i++) {
            preNode = preNode.next;
        }
        // 要插入的节点的下一个节点指向preNode节点的下一个节点
        Node next = preNode.next;
        Node node = new Node(data, next);
        // preNode的下一个节点指向要插入节点node
        preNode.next = node;
        size++;
        return true;
    }

    public void removeNode(T data) {
        if (head == null) {
            throw new RuntimeException("当前链表为空");
        }
        while (head.data.equals(data)) {
            head = head.next;
            this.size--;
        }
        Node cur = this.head;
        while (cur != null && cur.next != null) {
            if (cur.next.data.equals(data)) {
                this.size--;
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
    }

    public void display() {
        Node cur = head;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.addFirstNode(new String("李四"));
        list.addNode(new String("张三"),1);
        list.addNode(new String("王五"),2);
        list.removeNode(new String("张三"));
        list.addLastNode(new String("赵大"));
        list.display();
    }

}
