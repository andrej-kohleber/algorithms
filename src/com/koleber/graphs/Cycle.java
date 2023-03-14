package com.koleber.graphs;

import edu.princeton.cs.algs4.In;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Cycle {
    
    private boolean[] marked;
    private boolean hasCycle;
    
    public Cycle(Graph G) {
        marked = new boolean[G.v()];
        for (int s = 0; s < G.v(); s++) {
            if (!marked[s]) {
                dfs(G, s, s);
            }
        }
    }
    
    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) 
                dfs(G, w, v);
            else if (w != u) 
                hasCycle = true;
        }
    }
    
    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(new File("/Users/andrej/Desktop/tinyCG.txt")));
        Cycle cycle = new Cycle(graph);
        boolean hasCycle = cycle.hasCycle;
        int c = 0;



//        int s = Integer.parseInt()
        List<String> list = new ArrayList<>();
        int y = 0;
    }
}
