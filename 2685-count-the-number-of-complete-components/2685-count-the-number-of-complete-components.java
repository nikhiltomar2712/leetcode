class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            List<Integer> comp = new ArrayList<>();
            dfs(i, graph, visited, comp);
            int size = comp.size();
            boolean complete = true;
            for (int node : comp) {
                if (graph[node].size() != size - 1) {
                    complete = false;
                    break;
                }
            }
            if (complete) count++;
        }
        return count;
    }

    private void dfs(int u, List<Integer>[] graph, boolean[] visited, List<Integer> comp) {
        visited[u] = true;
        comp.add(u);
        for (int v : graph[u]) {
            if (!visited[v]) dfs(v, graph, visited, comp);
        }
    }
}