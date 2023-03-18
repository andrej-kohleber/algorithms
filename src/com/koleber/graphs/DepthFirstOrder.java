package com.koleber.graphs;

import com.koleber.main.Queue;
import com.koleber.main.Stack;

public class DepthFirstOrder {
    
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;
    
    public DepthFirstOrder(DiGraph g) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[g.v()];
        for (int i = 0; i < g.v(); i++)
            if (!marked[i]) dfs(g, i);
    
    }

    private void dfs(DiGraph g, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : g.adj(v))
            if (!marked[w])
                dfs(g, w);
        post.enqueue(v);
        reversePost.push(v);
    }

    public Queue<Integer> pre() {
        return pre;
    }

    public Queue<Integer> post() {
        return post;
    }

    public Stack<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
       DiGraph graph = new DiGraph(13);
       graph.addEdge(0, 1);
       graph.addEdge(0, 5);
       graph.addEdge(0, 6);
       graph.addEdge(2, 0);
       graph.addEdge(2, 3);
       graph.addEdge(7, 6);
       graph.addEdge(6, 9);
       graph.addEdge(9, 11);
       graph.addEdge(9, 12);
       graph.addEdge(9, 10);
       graph.addEdge(7, 6);
       graph.addEdge(8, 7);
       
       DepthFirstOrder depthFirstOrder = new DepthFirstOrder(graph);
       Queue<Integer> pre = depthFirstOrder.pre();
       int c = 0;
    }
}
