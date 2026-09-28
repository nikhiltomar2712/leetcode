class Solution {
    public int countRestrictedPaths(int n, int[][] edges) {
        List<int[]>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{n, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];
            if (d > dist[u]) continue;
            for (int[] next : graph[u]) {
                int v = next[0], w = next[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        int mod = 1000000007;
        Integer[] order = new Integer[n];
        for (int i = 1; i <= n; i++) order[i - 1] = i;
        Arrays.sort(order, (a, b) -> dist[a] - dist[b]);
        long[] ways = new long[n + 1];
        ways[n] = 1;
        for (int u : order) {
            for (int[] next : graph[u]) {
                int v = next[0];
                if (dist[u] < dist[v]) {
                    ways[v] = (ways[v] + ways[u]) % mod;
                }
            }
        }
        return (int) ways[1];
    }
}