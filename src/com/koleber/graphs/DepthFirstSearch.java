package com.koleber.graphs;

import edu.princeton.cs.algs4.In;


public class DepthFirstSearch {
    
    private boolean[] marked;
    private int count;
    
    public DepthFirstSearch(Graph g, int s) {
        marked = new boolean[g.v()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        count++;
        for (int w : g.adj(v)) {
            if (!marked[w]) dfs(g, w);
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }
    
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In("https://algs4.cs.princeton.edu/41graph/tinyG.txt"));
        DepthFirstSearch search = new DepthFirstSearch(G, 0);
        for (int v = 0; v < G.v(); v++) {
            if (search.marked(v))
                System.out.print(v + " ");
        }
        int count = search.count();
        


    }
}
