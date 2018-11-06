package map;


import set.FileOperation;

import java.util.ArrayList;

/**
 * Created by chenjian
 * 2018/7/29 20:19
 */
public class LinkedListMap<K, V> implements Map<K, V>{

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }


        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }



    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (null == node) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            ++size;
        } else {
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node node  = getNode(key);
        if (null == node) {
            throw new IllegalArgumentException(key + " does not exist");
        }
        Node cur = dummyHead.next;

        while (cur != null) {
            if (cur.next.equals(node)) {
                cur.next = node.next;
                node.next = null;
                --size;
                break;
            }
        }

        return node.value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
//        System.out.println("get......");
        Node node = getNode(key);

        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
//        System.out.println("set.......");
        Node node = getNode(key);
        if (null == node) {
            throw new IllegalArgumentException(key + "does not exist");
        } else {
            node.value = newValue;
        }
    }

    @Override
    public int getSize() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return 0 == size;
    }
    private Node getNode(K key) {
//        System.out.println("getNode........");
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Pride and prejudice");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("F:\\DataStructure\\DataStructureLearn\\src\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words :" +  words.size());
            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words : " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PRIEJUDICE: " + map.get("prejudice"));
        }
    }

}
