package com.koleber.graphs;

public class KosarajuSCC {
    
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(DiGraph G) {
        marked = new boolean[G.v()];
        id = new int[G.v()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost())
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
    }
    
    private void dfs(DiGraph G, int v) {
       marked[v] = true;
       id[v] = count;
       for (int w : G.adj(v))
           if (!marked[w])
               dfs(G, w);
    }
    
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }
    
    public int id(int v) {
        return id[v];
    }
    
    public int count() {
        return count;
    }

    public static void main(String[] args) {
       
    }
}
