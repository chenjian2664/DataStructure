package linkedList;

/**
 * Created by chenjian
 * 2018/7/15 16:41
 */
public class LinkedList<E> {

    private Node dummyHead;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public Node getHead() {
        return dummyHead.next;
    }

    public void setHead(Node head) {
        dummyHead.next = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    private class Node {
        public E e;
        public Node next;

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

    //在链表头部添加元素
    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
//        dummyHead.next = new Node(e, dummyHead.next);
//        ++size;
        addIndex(0, e);
    }


    //索引添加元素e
    public void addIndex( int index, E e) {
        int pos = 0;
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("wrong index to add ");
        }

        Node prev = dummyHead;
        while (pos < index ) {
            prev = prev.next;
            ++ pos;
        }
//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;

        prev.next = new Node(e, prev.next);
        ++size;
    }

    //向链表末尾添加元素
    public void addLast(E e) {
        addIndex(size, e);
    }

    //获得链表第index元素 0-based
    public E getIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("wrong index !");
        }
        int pos = 0;
        Node cur = dummyHead.next;

        while (pos < index) {
            cur = cur.next;
            ++pos;
        }

        return cur.e;
    }

    public E getFirst() {
        return getIndex(0);
    }

    public E getLast() {
        return getIndex(size - 1);

    }

    //跟新第index个元素 0-based
    public void setIndex(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("wrong index of setIndex");
        }
        Node cur = dummyHead.next;
        int pos = 0;

        while (pos < index) {
            cur = cur.next;
            ++pos;
        }
        cur.e = e;
    }

    //查找链表中是否含有这个元素
    public boolean contains(E e) {
        Node node = dummyHead.next;
        while (node != null) {
            if (node.e.equals(e)) {
                return true;
            }
            node = node.next;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            stringBuilder.append(cur + "->");
            cur = cur.next;
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }

    //删除索引位置元素  version 1
    public void deleteIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("wrong delete operation ");
        }
        Node cur = dummyHead;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        --size;
    }

    //删除索引位置元素 version 2
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("wrong index of operation");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; ++i) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        --size;
        return delNode.e;
    }

    //从链表中删除指定元素
    public void remove(E e) {
        Node pre = dummyHead;
        Node cur = pre.next;
        while (null != cur) {
            if (cur.e.equals(e)) {
                pre.next = cur.next;
                cur.next = null;
                break;
            }
        }
    }

    //删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    //删除最后一个元素
    public E removeLast() {
        return remove(size - 1);
    }

    //测试
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList();
        for (int i = 0; i < 5; ++i) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.addIndex(2, 666);
        System.out.println(linkedList);

        linkedList.addLast(888);
        System.out.println(linkedList);

        linkedList.deleteIndex(2);
        System.out.println(linkedList);

        linkedList.deleteIndex(0);
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);
    }
}
