class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        // Build adjacency list: weight = cnt + 1
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1], cnt = e[2];
            graph[u].add(new int[]{v, cnt + 1});
            graph[v].add(new int[]{u, cnt + 1});
        }

        // Dijkstra: dist[u] = minimum moves to reach original node u
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0}); // {distance, node}

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0], u = curr[1];
            if (d > dist[u]) continue;

            for (int[] next : graph[u]) {
                int v = next[0], w = next[1];
                if (d + w < dist[v]) {
                    dist[v] = d + w;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        // Count reachable original nodes
        int ans = 0;
        for (int d : dist) {
            if (d <= maxMoves) ans++;
        }

        // Count reachable intermediate nodes on each edge
        for (int[] e : edges) {
            int u = e[0], v = e[1], cnt = e[2];
            int fromU = Math.max(0, maxMoves - dist[u]);
            int fromV = Math.max(0, maxMoves - dist[v]);
            ans += Math.min(cnt, fromU + fromV);
        }

        return ans;
    }
}