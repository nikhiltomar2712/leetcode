class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];

        // Initialize each node as its own parent
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (!union(u, v, parent, rank)) {
                // Already connected → this edge is redundant
                return edge;
            }
        }

        return new int[0]; // Guaranteed not to reach here
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent); // Path compression
        }
        return parent[x];
    }

    private boolean union(int x, int y, int[] parent, int[] rank) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);

        if (rootX == rootY) {
            return false; // Already in the same set
        }

        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}