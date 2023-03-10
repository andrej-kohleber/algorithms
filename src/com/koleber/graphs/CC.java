package com.koleber.graphs;

import edu.princeton.cs.algs4.In;

public class CC {
    
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G) {
        marked = new boolean[G.v()];
        id = new int[G.v()];
        for (int s = 0; s < G.v(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }
    
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }
    
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }
    
    public int id(int v) {
        return id[v];
    }
    
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph g = new Graph(new In("https://algs4.cs.princeton.edu/41graph/tinyG.txt"));
        CC cc = new CC(g);
        boolean connected = cc.connected(7, 8);
        int c = 0;
    }
}
