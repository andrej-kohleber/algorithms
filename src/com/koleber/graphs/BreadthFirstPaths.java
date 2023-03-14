package com.koleber.graphs;

import com.koleber.main.Queue;
import com.koleber.main.Stack;
import edu.princeton.cs.algs4.In;

public class BreadthFirstPaths {
    
    private boolean[] marked; //Is the shortest way for this vertex known 
    private int[] edgeTo; //Last vertex on the known path for the given vertex
    private final int s; //Original vertex

    public BreadthFirstPaths(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.v()];
        edgeTo = new int[G.v()];
        bfs(G, s);
    }
    
    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true; //marking the original vertex
        queue.enqueue(s); //and placing it in to the queue
        
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {   //for all not marked adjacent vertex.
                    edgeTo[w] = v;  //saving the last edge into the shortest path
                    marked[w] = true; //marking it since the path is known
                    queue.enqueue(w); //placing it into the queue
                }
            }
        }
    }
    
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        return path;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In("https://algs4.cs.princeton.edu/41graph/tinyCG.txt"));
        BreadthFirstPaths paths = new BreadthFirstPaths(G, 0);
        boolean b = paths.hasPathTo(5);
        Iterable<Integer> integers = paths.pathTo(3);
       
    }
}
