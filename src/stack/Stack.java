package stack;

/**
 * Created by chenjian
 * 2018/7/8 9:55
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
