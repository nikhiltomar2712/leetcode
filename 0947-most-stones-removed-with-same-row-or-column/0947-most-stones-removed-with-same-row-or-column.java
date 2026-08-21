class Solution {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind uf = new UnionFind(n);
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Same row or same column → connect them
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    uf.union(i, j);
                }
            }
        }
        
        return n - uf.getComponentCount();
    }
    
    class UnionFind {
        int[] parent;
        int components;
        
        public UnionFind(int n) {
            parent = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // path compression
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                components--;
            }
        }
        
        public int getComponentCount() {
            return components;
        }
    }
}