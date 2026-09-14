class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // Build adjacency list: manager -> list of direct subordinates
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                graph.get(manager[i]).add(i);
            }
        }
        
        return dfs(headID, graph, informTime);
    }
    
    private int dfs(int emp, List<List<Integer>> graph, int[] informTime) {
        int maxTime = 0;
        for (int sub : graph.get(emp)) {
            maxTime = Math.max(maxTime, dfs(sub, graph, informTime));
        }
        return informTime[emp] + maxTime;
    }
}