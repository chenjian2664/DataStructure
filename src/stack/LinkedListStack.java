package stack;

import linkedList.LinkedList;

/**
 * Created by chenjian
 * 2018/7/15 19:31
 */
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> linkedList;


    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }
    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack top : ");
        stringBuilder.append(linkedList);
        return stringBuilder.toString();
    }

    //测试
    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack();

        for (int i = 0; i < 5; ++i) {
            linkedListStack.push(i);
            System.out.println(linkedListStack);
        }

        linkedListStack.pop();
        System.out.println(linkedListStack);
    }
}
