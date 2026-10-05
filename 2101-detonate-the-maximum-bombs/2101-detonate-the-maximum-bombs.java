class Solution {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                long dx = bombs[i][0] - bombs[j][0];
                long dy = bombs[i][1] - bombs[j][1];
                long r = bombs[i][2];
                if (dx * dx + dy * dy <= r * r) {
                    graph[i].add(j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            res = Math.max(res, dfs(i, graph, visited));
        }
        return res;
    }

    private int dfs(int u, List<Integer>[] graph, boolean[] visited) {
        visited[u] = true;
        int count = 1;
        for (int v : graph[u]) {
            if (!visited[v]) {
                count += dfs(v, graph, visited);
            }
        }
        return count;
    }
}