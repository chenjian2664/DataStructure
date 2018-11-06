package queue;

import array.Array;

/**
 * Created by chenjian
 * 2018/7/14 8:38
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue() {
        array = new Array<>();
    }

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }


    @Override
    public void enQueue(E o) {
        array.addLast(o);
    }

    @Override
    public E deQueue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Queue: size = %d, capacity = %d\n", array.getSize(), array.getCapacity()));
        stringBuilder.append("front [");
        for (int i = 0; i < array.getSize(); ++i) {
            stringBuilder.append(array.get(i));
            if (i != getSize() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue= new ArrayQueue<>();
        for (int i = 0; i < 11; ++i) {
            arrayQueue.enQueue(i);
            System.out.println(arrayQueue);
        }

    }
}
