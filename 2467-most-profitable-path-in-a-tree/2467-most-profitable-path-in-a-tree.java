class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int[] bobTime = new int[n];
        Arrays.fill(bobTime, Integer.MAX_VALUE);
        findBobPath(bob, -1, 0, graph, bobTime);
        return dfs(0, -1, 0, 0, graph, amount, bobTime);
    }

    private boolean findBobPath(int u, int p, int time, List<Integer>[] graph, int[] bobTime) {
        bobTime[u] = time;
        if (u == 0) return true;
        for (int v : graph[u]) {
            if (v != p && findBobPath(v, u, time + 1, graph, bobTime)) {
                return true;
            }
        }
        bobTime[u] = Integer.MAX_VALUE;
        return false;
    }

    private int dfs(int u, int p, int time, int income, List<Integer>[] graph, int[] amount, int[] bobTime) {
        int cur = amount[u];
        if (time < bobTime[u]) {
            income += cur;
        } else if (time == bobTime[u]) {
            income += cur / 2;
        }
        int res = Integer.MIN_VALUE;
        boolean isLeaf = true;
        for (int v : graph[u]) {
            if (v == p) continue;
            isLeaf = false;
            res = Math.max(res, dfs(v, u, time + 1, income, graph, amount, bobTime));
        }
        if (isLeaf) return income;
        return res;
    }
}