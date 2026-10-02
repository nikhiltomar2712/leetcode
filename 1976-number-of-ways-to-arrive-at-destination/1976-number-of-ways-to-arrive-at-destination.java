class Solution {
    public int countPaths(int n, int[][] roads) {
        int mod = 1000000007;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] r : roads) {
            graph[r[0]].add(new int[]{r[1], r[2]});
            graph[r[1]].add(new int[]{r[0], r[2]});
        }
        long[] dist = new long[n];
        long[] ways = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        ways[0] = 1;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{0, 0});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long d = cur[1];
            if (d > dist[u]) continue;
            for (int[] next : graph[u]) {
                int v = next[0], w = next[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    ways[v] = ways[u];
                    pq.offer(new long[]{v, dist[v]});
                } else if (dist[u] + w == dist[v]) {
                    ways[v] = (ways[v] + ways[u]) % mod;
                }
            }
        }
        return (int) ways[n - 1];
    }
}