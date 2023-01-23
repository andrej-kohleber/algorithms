package com.koleber.main;

import java.util.Scanner;

public class WeightedQuickUnionUF {
    
    private int[] id;
    private int[] sz;
    private int count;

    public WeightedQuickUnionUF(int n) {
        this.count = n;
        
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
        
        sz = new int[n];
        for (int i = 0; i < n; i++) 
            sz[i] = 1;
    }

    public int getCount() {
        return count;
    }
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        
        if (i == j) return;
        
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
    
    public int find(int p) {
        while (p != id[p]) 
            p = id[p];
        return p;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(100);
        uf.union(4, 3);
        int c = 0;
    }
}
