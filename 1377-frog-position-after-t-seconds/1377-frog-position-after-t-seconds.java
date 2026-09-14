class Solution {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        boolean[] visited = new boolean[n + 1];
        return dfs(1, t, target, graph, visited);
    }
    
    private double dfs(int node, int timeLeft, int target,
                       List<List<Integer>> graph, boolean[] visited) {
        // Count valid next nodes (exclude already visited parent)
        int choices = 0;
        for (int nei : graph.get(node)) {
            if (!visited[nei]) choices++;
        }
        
        // If this is the target
        if (node == target) {
            // Stay here only if no more moves possible or time is exactly used up
            return (choices == 0 || timeLeft == 0) ? 1.0 : 0.0;
        }
        
        // Time is up and we are not at the target
        if (timeLeft == 0) return 0.0;
        
        visited[node] = true;
        
        for (int nei : graph.get(node)) {
            if (!visited[nei]) {
                double prob = dfs(nei, timeLeft - 1, target, graph, visited);
                if (prob > 0) {
                    // probability of choosing this child
                    return prob / choices;
                }
            }
        }
        
        // No path reaches the target from here
        return 0.0;
    }
}