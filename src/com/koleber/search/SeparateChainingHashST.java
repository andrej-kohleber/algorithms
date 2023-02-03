package com.koleber.search;

public class SeparateChainingHashST<K, V> {
    
    private int N;
    private int M;
    private SequentialSearchST<K, V>[] st;
    
    public SeparateChainingHashST() {
        this(10);
    }
    
    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<K, V>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }
    
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    
    public V get(K key) {
        return (V) st[hash(key)].get(key);
    }
    
    public void put(K key, V value) {
        st[hash(key)].put(key, value);
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> set = new SeparateChainingHashST<>();
        set.put("A", 1);
        set.put("B", 1);
        set.put("C", 1);
        set.put("D", 1);
        set.put("E", 1);
        set.put("D", 1);
    }
}
