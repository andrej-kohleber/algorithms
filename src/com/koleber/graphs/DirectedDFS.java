package com.koleber.graphs;

import edu.princeton.cs.algs4.In;

public class DirectedDFS {
    
    private boolean[] marked;
    
    public DirectedDFS(DiGraph g, int s) {
        marked = new boolean[g.v()];
        dfs(g, s);
    }
    
    public DirectedDFS(DiGraph g, Iterable<Integer> sources) {
        marked = new boolean[g.v()];
        for (int s : sources)
            if (!marked[s]) dfs(g, s);
    }

    private void dfs(DiGraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) dfs(g, w);
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public static void main(String[] args) {
        DiGraph G = new DiGraph(new In("https://algs4.cs.princeton.edu/42digraph/tinyDG.txt"));
        DirectedDFS search = new DirectedDFS(G, 0);
        for (int v = 0; v < G.v(); v++) {
            if (search.marked(v))
                System.out.print(v + " ");
        }
        boolean marked = search.marked(2);
    }
}
