class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];   // parent[v] = u means edge u → v
        int[] cand1 = null, cand2 = null;

        // Step 1: Find the node with two parents (if any)
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (parent[v] == 0) {
                parent[v] = u;
            } else {
                // v already has a parent → record both candidate edges
                cand1 = new int[]{parent[v], v};  // earlier edge
                cand2 = new int[]{u, v};          // later edge
                // Temporarily invalidate the later edge for cycle check
                edge[1] = 0;
            }
        }

        // Step 2: Union-Find to detect cycle
        int[] root = new int[n + 1];
        for (int i = 1; i <= n; i++) root[i] = i;

        for (int[] edge : edges) {
            if (edge[1] == 0) continue;  // skip the invalidated edge

            int u = edge[0], v = edge[1];
            int ru = find(root, u);
            int rv = find(root, v);

            if (ru == rv) {
                // Cycle found
                if (cand1 != null) {
                    // Case 3: two parents + cycle → remove the earlier edge
                    return cand1;
                }
                // Case 2: pure cycle → remove current edge
                return edge;
            }
            root[rv] = ru;
        }

        // Case 1: two parents, no cycle → remove the later edge
        return cand2;
    }

    private int find(int[] root, int x) {
        if (root[x] != x) {
            root[x] = find(root, root[x]);
        }
        return root[x];
    }
}