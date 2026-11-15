class Solution {
    public boolean[] findAnswer(int n, int[][] edges) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            graph[u].add(new int[]{v, w, i});
            graph[v].add(new int[]{u, w, i});
        }
        long[] dist1 = dijkstra(graph, 0, n);
        long[] dist2 = dijkstra(graph, n - 1, n);
        boolean[] res = new boolean[edges.length];
        if (dist1[n - 1] == Long.MAX_VALUE) return res;
        long shortest = dist1[n - 1];
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            if (dist1[u] != Long.MAX_VALUE && dist2[v] != Long.MAX_VALUE && dist1[u] + w + dist2[v] == shortest) {
                res[i] = true;
            } else if (dist1[v] != Long.MAX_VALUE && dist2[u] != Long.MAX_VALUE && dist1[v] + w + dist2[u] == shortest) {
                res[i] = true;
            }
        }
        return res;
    }

    private long[] dijkstra(List<int[]>[] graph, int src, int n) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{src, 0});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            if (cur[1] > dist[u]) continue;
            for (int[] next : graph[u]) {
                int v = next[0], w = next[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new long[]{v, dist[v]});
                }
            }
        }
        return dist;
    }
}