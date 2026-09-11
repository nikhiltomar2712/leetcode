class Solution {
    public int makeConnected(int n, int[][] connections) {
        // Need at least n-1 cables to connect n computers
        if (connections.length < n - 1) {
            return -1;
        }
        
        UnionFind uf = new UnionFind(n);
        for (int[] conn : connections) {
            uf.union(conn[0], conn[1]);
        }
        
        int components = uf.countComponents();
        return components - 1;
    }
    
    private static class UnionFind {
        int[] parent;
        int[] rank;
        int components;
        
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // path compression
            }
            return parent[x];
        }
        
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return; // already same component
            
            // union by rank
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            components--;
        }
        
        int countComponents() {
            return components;
        }
    }
}