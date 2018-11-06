package queue;

/**
 * Created by chenjian
 * 2018/7/14 8:39
 */
public interface Queue<E> {
    void enQueue(E e);
    E deQueue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
