package com.koleber.main;

import java.util.Scanner;

public class UF {
    
    private int[] id;
    private int count;

    public UF(int n) {
        this.count = n;
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    public int getCount() {
        return count;
    }
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        
        if (pId == qId) return;
        
        for (int i = 0; i < id.length; i++) 
            if (id[i] == pId) id[i] = qId;
        count--;
    }
    
    public int find(int p) {
        return id[p];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UF uf = new UF(100);
        uf.union(3, 4);
        int c = 0;
    }
}
