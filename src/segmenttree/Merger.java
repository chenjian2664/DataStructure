package segmenttree;

/**
 * Created by chenjian
 * 2018/10/10 23:50
 */
public interface Merger<E> {
    E merge(E e1, E e2);
}