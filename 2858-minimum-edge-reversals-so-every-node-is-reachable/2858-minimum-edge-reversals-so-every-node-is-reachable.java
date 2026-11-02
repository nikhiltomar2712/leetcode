class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], 0});
            graph[e[1]].add(new int[]{e[0], 1});
        }
        int[] res = new int[n];
        res[0] = dfs(0, -1, graph);
        dfs2(0, -1, graph, res);
        return res;
    }

    private int dfs(int u, int p, List<int[]>[] graph) {
        int count = 0;
        for (int[] next : graph[u]) {
            int v = next[0], cost = next[1];
            if (v == p) continue;
            count += cost + dfs(v, u, graph);
        }
        return count;
    }

    private void dfs2(int u, int p, List<int[]>[] graph, int[] res) {
        for (int[] next : graph[u]) {
            int v = next[0], cost = next[1];
            if (v == p) continue;
            res[v] = res[u] + (cost == 0 ? 1 : -1);
            dfs2(v, u, graph, res);
        }
    }
}