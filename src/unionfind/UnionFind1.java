package unionfind;

/**
 * Created by chenjian
 * 2018/10/20 21:32
 */
public class UnionFind1 implements UF{
    // 元素对应的集合
    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        for (int i = 0; i < size; ++i) {
            id[i] = i;
        }

    }
    @Override
    public int getSize() {
        return id.length;
    }
    // 查找元素p所对应的编号
    private int find(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("p not illegal");
        return id[p];
    }
    // 查看元素p和元素q是否所属于一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并两个集合
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID)
            return;
        for (int i = 0; i < id.length; ++i) {
            if (id[i] == pID)
                id[i] = qID;
        }
    }
}
