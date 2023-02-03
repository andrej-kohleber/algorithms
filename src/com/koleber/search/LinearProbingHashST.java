package com.koleber.search;


public class LinearProbingHashST<K, V> {

    private static final int INIT_CAPACITY = 16;
    private int N; // number of key value pairs in the table
    private int M = 16; // size of the table with linear probing
    private K[] keys;
    private V[] values;
    
    public LinearProbingHashST() {
       this(INIT_CAPACITY);
    }
    
    public LinearProbingHashST(int capacity) {
        M = capacity;
        N = 0;
        keys = (K[]) new Object[M];
        values = (V[]) new Object[M];
    }
    
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    
    public void put(K key, V value) {
        if (N >= M / 2) resize(2 * M);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }         
        keys[i] = key;
        values[i] = value;
        N++;         
    }
    
    public V get(K key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }
    
    public boolean contains(K key) {
        return get(key) != null;
    }
    
    public void delete(K key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % M;
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            K keyToRedo = keys[i];
            V valueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyToRedo, valueToRedo);
            i = (i + i) % M;
        }
        N--;
        if (N > 0 && N <= M / 8) resize(M / 2);
    }
    
    public void resize(int capacity) {
        LinearProbingHashST<K, V> tmp = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                tmp.put(keys[i], values[i]);
            }
        }
        keys = tmp.keys;
        values = tmp.values;
        M = tmp.M;
    }

    public static void main(String[] args) {
        
        LinearProbingHashST<String, Integer> set = new LinearProbingHashST<>(4);
        set.put("A",  1);
        set.put("B",  1);
        set.put("C",  1);
        set.put("D",  1);
        set.put("E",  1);
        set.put("Z",  1);
      
        int w = 0;
    }
    
    
}
