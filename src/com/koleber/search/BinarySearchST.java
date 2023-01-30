package com.koleber.search;

import com.koleber.main.Queue;

public class BinarySearchST<K extends Comparable<K>, V> {
    
    private K[] keys;
    private V[] values;
    private int size;

    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public V get(K key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) return values[i];
        else return null;
    }
    
    public void put(K key, V value) {
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for (int j = size; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }
    
    public int rank(K key) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
    
    public K min() {
        return keys[0];
    }
    
    public K max() {
        return keys[size - 1];
    }

    public K select(int k) {
        return keys[k];
    }
    
    boolean contains(K key) {
        int i = rank(key);
        return keys[i].compareTo(key) == 0;
    }
    
    public Iterable<K> keys(K lo, K hi) {
        Queue<K> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<>(100);
        binarySearchST.put("A", 1);
        binarySearchST.put("B", 1);
        binarySearchST.put("D", 1);
        binarySearchST.put("C", 1);
        int c = 0;
    }
}
