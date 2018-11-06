package set;

/**
 * Created by chenjian
 * 2018/7/28 16:57
 */
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
