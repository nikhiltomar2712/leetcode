class Solution {
    int maxQuality = 0;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }
        int[] visited = new int[n];
        visited[0] = 1;
        dfs(0, values[0], 0, maxTime, graph, values, visited);
        return maxQuality;
    }

    private void dfs(int u, int quality, int time, int maxTime, List<int[]>[] graph, int[] values, int[] visited) {
        if (u == 0) {
            maxQuality = Math.max(maxQuality, quality);
        }
        for (int[] next : graph[u]) {
            int v = next[0], w = next[1];
            if (time + w > maxTime) continue;
            if (visited[v] == 0) {
                visited[v]++;
                dfs(v, quality + values[v], time + w, maxTime, graph, values, visited);
                visited[v]--;
            } else {
                visited[v]++;
                dfs(v, quality, time + w, maxTime, graph, values, visited);
                visited[v]--;
            }
        }
    }
}