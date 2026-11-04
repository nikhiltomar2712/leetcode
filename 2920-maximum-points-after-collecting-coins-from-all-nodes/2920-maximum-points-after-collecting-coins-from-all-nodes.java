class Solution {
    private Map<Long, Integer> memo = new HashMap<>();

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        int n = coins.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        return dfs(0, -1, 0, graph, coins, k);
    }

    private int dfs(int u, int parent, int halvings, List<Integer>[] graph, int[] coins, int k) {
        halvings = Math.min(halvings, 14);
        long key = (long) u * 20 + halvings;
        if (memo.containsKey(key)) return memo.get(key);
        int coinValue = coins[u] >> halvings;
        int take = coinValue - k;
        int skip = coinValue / 2;
        for (int v : graph[u]) {
            if (v == parent) continue;
            take += dfs(v, u, halvings, graph, coins, k);
            skip += dfs(v, u, halvings + 1, graph, coins, k);
        }
        int res = Math.max(take, skip);
        memo.put(key, res);
        return res;
    }
}