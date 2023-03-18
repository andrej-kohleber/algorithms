package com.koleber.graphs;

public class Topological {
    
    private Iterable<Integer> order;
  
    public Topological(DiGraph g) {
        DirectedCycle cycleFinder = new DirectedCycle(g);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(g);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() { return order; }
    
    public boolean isDag() { return order == null; }
  
    public static void main(String[] args) {
       
    }
}
