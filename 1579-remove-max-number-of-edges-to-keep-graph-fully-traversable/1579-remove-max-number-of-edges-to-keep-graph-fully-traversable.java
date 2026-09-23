class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        DSU alice = new DSU(n);
        DSU bob = new DSU(n);

        int used = 0;

        // Step 1: type 3 edges — add to both if they connect new components
        for (int[] e : edges) {
            if (e[0] == 3) {
                boolean a = alice.union(e[1], e[2]);
                boolean b = bob.union(e[1], e[2]);
                if (a || b) used++;   // this edge is needed by at least one
            }
        }

        // Step 2: type 1 edges for Alice, type 2 for Bob
        for (int[] e : edges) {
            if (e[0] == 1) {
                if (alice.union(e[1], e[2])) used++;
            } else if (e[0] == 2) {
                if (bob.union(e[1], e[2])) used++;
            }
        }

        // Both must be fully connected
        if (alice.components != 1 || bob.components != 1) return -1;

        return edges.length - used;
    }

    private static class DSU {
        int[] parent, rank;
        int components;

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            components = n;
            for (int i = 1; i <= n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return false;   // already connected → redundant
            if (rank[ra] < rank[rb]) { int t = ra; ra = rb; rb = t; }
            parent[rb] = ra;
            if (rank[ra] == rank[rb]) rank[ra]++;
            components--;
            return true;
        }
    }
}