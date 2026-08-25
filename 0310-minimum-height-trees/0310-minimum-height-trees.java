class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Arrays.asList(0);
        }
        
        // Build adjacency list
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());
        }
        
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        // Find all leaves (nodes with degree 1)
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) {
                leaves.add(i);
            }
        }
        
        // Trim leaves layer by layer until 1 or 2 nodes remain
        int remaining = n;
        while (remaining > 2) {
            remaining -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            
            for (int leaf : leaves) {
                // Get the neighbor of this leaf
                int neighbor = adj.get(leaf).iterator().next();
                // Remove leaf from neighbor's adjacency list
                adj.get(neighbor).remove(leaf);
                // If neighbor becomes a leaf, add to newLeaves
                if (adj.get(neighbor).size() == 1) {
                    newLeaves.add(neighbor);
                }
            }
            
            leaves = newLeaves;
        }
        
        return leaves;
    }
}