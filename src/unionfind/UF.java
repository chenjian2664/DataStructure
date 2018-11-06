package unionfind;

/**
 * Created by chenjian
 * 2018/10/20 21:32
 */
public interface UF {
    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
