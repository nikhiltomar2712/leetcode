class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] r : roads) {
            graph[r[0]].add(new int[]{r[1], r[2]});
            graph[r[1]].add(new int[]{r[0], r[2]});
        }
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int res = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int[] next : graph[u]) {
                int v = next[0], w = next[1];
                res = Math.min(res, w);
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(v);
                }
            }
        }
        return res;
    }
}