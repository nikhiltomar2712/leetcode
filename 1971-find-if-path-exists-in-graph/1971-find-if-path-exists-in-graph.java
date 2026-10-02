class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (u == destination) return true;
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(v);
                }
            }
        }
        return false;
    }
}