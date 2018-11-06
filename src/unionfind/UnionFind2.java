package unionfind;

/**
 * Created by chenjian
 * 2018/10/21 9:37
 */
public class UnionFind2 implements UF {
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];
        for (int i = 0; i < size; ++i)
            parent[i] = i;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 返回元素p的根节点
    // O(h) 复杂度，h为树的高度
    private int find(int p) {
        while (p != parent[p])
            p = parent[p];
        return p;
    }
    // 查看元素p和元素q是否属于一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        parent[pRoot] = qRoot;
    }
}
