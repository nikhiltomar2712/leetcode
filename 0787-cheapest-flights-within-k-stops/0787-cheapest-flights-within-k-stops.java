class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // At most k+1 edges
        for (int i = 0; i <= k; i++) {
            int[] next = dist.clone();          // important: use previous distances
            for (int[] f : flights) {
                int u = f[0], v = f[1], price = f[2];
                if (dist[u] < INF) {
                    next[v] = Math.min(next[v], dist[u] + price);
                }
            }
            dist = next;
        }

        return dist[dst] == INF ? -1 : dist[dst];
    }
}