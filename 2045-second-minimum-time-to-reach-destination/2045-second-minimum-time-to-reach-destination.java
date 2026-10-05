class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int[][] dist = new int[n + 1][2];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[1][0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int u = cur[0], d = cur[1];
            int wait = 0;
            if ((d / change) % 2 == 1) {
                wait = change - (d % change);
            }
            int nd = d + wait + time;
            for (int v : graph[u]) {
                if (dist[v][0] == Integer.MAX_VALUE) {
                    dist[v][0] = nd;
                    queue.offer(new int[]{v, nd});
                } else if (dist[v][1] == Integer.MAX_VALUE && dist[v][0] != nd) {
                    dist[v][1] = nd;
                    queue.offer(new int[]{v, nd});
                }
            }
        }
        return dist[n][1];
    }
}