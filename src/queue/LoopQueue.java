package queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjian
 * 2018/7/14 9:27
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front;
    private int tail;

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
    }


    @Override
    public void enQueue(E e) {
        if (isFull()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
    }

    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity + 1];
        int size = this.getSize();
        for (int i = front; i < front + size; ++i) {
            newData[i] = data[i % data.length];
        }
        this.data = newData;
        tail = front + size;
    }

    @Override
    public E deQueue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        E res = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        if (getSize() < getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return res;
    }

    @Override
    public E getFront() {
        return get(front);
    }

    @Override
    public int getSize() {
        return (tail + data.length - front) % data.length;
    }

    public int getCapacity() {
        return data.length - 1;
    }
    @Override
    public boolean isEmpty() {
//        if (front == tail) {
//            return true;
//        }
//        return false;
        return front == tail;
    }

    private boolean isFull() {
//        if ((tail + 1) % data.length == front) {
//            return true;
//        }
//        return false;
        return (tail + 1) % data.length == front;
    }

    public E get(int index) {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        if (front < tail && (index < front || index >= tail)) {
            throw new IllegalArgumentException("wrong index of Queue");
        }

        if (tail < front && (index < front && index >= tail)) {
            throw new IllegalArgumentException("wrong index of Queue");
        }

        return data[index];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Queue: size = %d, capacity = %d\n", getSize(), getCapacity()));
        stringBuilder.append("front [");
        for (int i = front; i < this.getSize() + front; ++i) {
            stringBuilder.append(get(i % data.length));
            if (i != getSize() + front - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.println(loopQueue.getSize());
        System.out.println(loopQueue.getCapacity());
        for (int i = 0; i < 11; ++i) {
            loopQueue.enQueue(i);
            System.out.println(loopQueue);
        }

        for (int i = 0; i < 9; ++i)
            loopQueue.deQueue();

        System.out.println(loopQueue);
//        System.out.println(loopQueue.getSize());
//        System.out.println(loopQueue.getCapacity());

    }
}
