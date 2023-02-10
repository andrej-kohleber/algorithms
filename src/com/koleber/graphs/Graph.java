package com.koleber.graphs;

import com.koleber.main.Bag;

import java.util.concurrent.BlockingQueue;

public class Graph {
    
    private final int v; //number of vertices
    private int e; // number of edges
    private Bag<Integer>[] adj; //lists of adjacent vertices
    public Graph(int v) {
        this.v = v;
        this.e = 0;
        for (int i = 0; i < v; i++) {
            adj[v] = new Bag<>();
        }
    }

    public int v() {
        return v;
    }
    
    public int e() {
        return e;
    }
    
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        e++;
    }     
    
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
