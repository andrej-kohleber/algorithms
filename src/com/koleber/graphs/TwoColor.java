package com.koleber.graphs;

import edu.princeton.cs.algs4.In;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TwoColor {
    
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.v()];
        color = new boolean[G.v()];
        for (int s = 0; s < G.v(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }
    
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            }
            else if (color[w] == color[v]) 
                isTwoColorable = false;
        }
    }

    public boolean isTwoColorable() {
        return isTwoColorable;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(new File("/Users/andrej/Desktop/tinyCG.txt")));
        TwoColor cycle = new TwoColor(graph);
        



//        int s = Integer.parseInt()
        List<String> list = new ArrayList<>();
        int y = 0;
    }
}
