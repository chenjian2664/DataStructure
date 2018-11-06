package set;

import linkedList.LinkedList;

import java.util.ArrayList;

/**
 * Created by chenjian
 * 2018/7/29 8:48
 */
public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("F:\\DataStructure\\DataStructureLearn\\src\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        LinkedListSet<String> set1 = new LinkedListSet<>();
        for (String word : word1) {
            set1.add(word);
        }
        System.out.println("Total different words: " + set1.getSize());
    }
}
