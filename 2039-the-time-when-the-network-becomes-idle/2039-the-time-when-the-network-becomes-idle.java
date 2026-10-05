class Solution {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.offer(v);
                }
            }
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            int roundTrip = 2 * dist[i];
            int lastSend = ((roundTrip - 1) / patience[i]) * patience[i];
            res = Math.max(res, lastSend + roundTrip);
        }
        return res + 1;
    }
}