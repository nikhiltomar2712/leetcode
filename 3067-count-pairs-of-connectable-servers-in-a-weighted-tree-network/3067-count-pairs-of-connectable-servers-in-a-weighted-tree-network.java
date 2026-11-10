class Solution {
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> counts = new ArrayList<>();
            for (int[] next : graph[i]) {
                int cnt = dfs(next[0], i, next[1], graph, signalSpeed);
                counts.add(cnt);
            }
            long total = 0;
            long sum = 0;
            for (int c : counts) {
                total += sum * c;
                sum += c;
            }
            res[i] = (int) total;
        }
        return res;
    }

    private int dfs(int u, int p, int dist, List<int[]>[] graph, int speed) {
        int count = (dist % speed == 0) ? 1 : 0;
        for (int[] next : graph[u]) {
            int v = next[0], w = next[1];
            if (v == p) continue;
            count += dfs(v, u, dist + w, graph, speed);
        }
        return count;
    }
}