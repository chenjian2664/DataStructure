package hashtable;

import java.util.TreeMap;

/**
 * Created by chenjian
 * 2018/10/31 6:53
 */
public class HashTable<K, V> {

    private TreeMap<K, V>[] hashTable;
    private int M;
    private int size;

    public HashTable(int M) {
        this.M = M;
        size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; ++i)
            hashTable[i] = new TreeMap<>();
    }

    public HashTable() {
        this(97);
    }
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public int getSize() {
        return size;
    }
    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key))
            ++size;
        map.put(key, value);
    }
    public V remove(K key) {
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            --size;
        }
        return ret;
    }
    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key))
            map.put(key,value);
    }
    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }
    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }
}
