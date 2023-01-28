package com.koleber.sort;

public class MaxPriorityQueue<T extends Comparable<T>> {
    
    private T[] pq;
    private int size = 0;
    
    public MaxPriorityQueue(int size) {
        pq = (T[] )new Comparable[size + 1];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void insert(T v) {
        pq[++size] = v;
        swim(size);
    }
    
    public T delMax() {
        T max = pq[1];
        exchange(1, size--);
        pq[size + 1] = null;
        sink(1);
        return max;
    }
    
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
    
    private void exchange(int i, int j) {
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k / 2, k);
            k = k / 2;
        }
    }
    
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        MaxPriorityQueue<Integer> priorityQueue = new MaxPriorityQueue<>(10);
        priorityQueue.insert(10);
        priorityQueue.insert(10);
        priorityQueue.insert(9);
        priorityQueue.insert(3);
        priorityQueue.insert(15);

        Integer integer = priorityQueue.delMax();
        int c = 0;
    }
}
