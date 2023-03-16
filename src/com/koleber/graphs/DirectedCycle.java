package com.koleber.graphs;

import com.koleber.main.Stack;

public class DirectedCycle {
    
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;
    
    public DirectedCycle(DiGraph g) {
        marked = new boolean[g.v()];
        edgeTo = new int[g.v()];
        onStack = new boolean[g.v()];
        for (int i = 0; i < g.v(); i++) 
            if (!marked[i]) dfs(g, i);
    }

    private void dfs(DiGraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) 
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }
    
    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
       DiGraph graph = new DiGraph(6);
       graph.addEdge(0, 5);
       graph.addEdge(5, 4);
       graph.addEdge(4, 3);
       graph.addEdge(3, 5);
       
       DirectedCycle dc = new DirectedCycle(graph);
        boolean b = dc.hasCycle();
        int c = 0;
    }
}
