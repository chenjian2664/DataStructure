package queue;

/**
 * Created by chenjian
 * 2018/7/15 19:57
 */
public class LinkedListQueue<E> implements Queue<E>{

    private class Node {
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enQueue(E e) {
        if (tail == null) {
            tail = new Node(e, null);
            head = tail;
            return;
        }
        tail.next = new Node(e, tail.next);
        tail = tail.next;
        ++size;
    }

    @Override
    public E deQueue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty !");
        }
        Node ret = head;
        head = head.next;
        ret.next = null;

        if (head == null) {
            tail = null;
        }
        --size;

        return ret.e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty!!");

        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue head : ");

        Node cur = head;
        while (cur != null) {
            stringBuilder.append(cur.e + "->");
            cur = cur.next;
        }
        stringBuilder.append("NULL tail");
        return stringBuilder.toString();
    }

    //测试
    public static void main(String[] args) {
        LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();

        for (int i = 0; i < 5; ++i) {
            listQueue.enQueue(i);
            System.out.println(listQueue);
        }

        listQueue.deQueue();
        System.out.println(listQueue);
    }
}
