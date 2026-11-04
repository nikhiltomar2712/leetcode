class Solution {
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        long total = 0;
        for (int v : values) total += v;
        long[] dp = new long[n];
        dfs(0, -1, graph, values, dp);
        return total - dp[0];
    }

    private void dfs(int u, int p, List<Integer>[] graph, int[] values, long[] dp) {
        long sumChildren = 0;
        boolean isLeaf = true;
        for (int v : graph[u]) {
            if (v == p) continue;
            isLeaf = false;
            dfs(v, u, graph, values, dp);
            sumChildren += dp[v];
        }
        if (isLeaf) {
            dp[u] = values[u];
        } else {
            dp[u] = Math.min(values[u], sumChildren);
        }
    }
}