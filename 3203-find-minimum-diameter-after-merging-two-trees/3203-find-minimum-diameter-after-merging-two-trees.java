class Solution {
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int d1 = getDiameter(edges1);
        int d2 = getDiameter(edges2);
        // The merged diameter is the maximum of the three: the original two diameters and the new diameter formed by connecting the centers
        return Math.max(Math.max(d1, d2), (d1 + 1) / 2 + (d2 + 1) / 2 + 1);
    }

    // Find the diameter of the tree: BFS twice
    private int getDiameter(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        // First BFS: start from node 0, find the farthest node
        int far = bfs(graph, 0)[0];
        // Second BFS: start from the farthest node, the distance obtained is the diameter
        return bfs(graph, far)[1];
    }

    // Return {farthest node, distance to farthest node}
    private int[] bfs(List<List<Integer>> graph, int start) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;
        int farNode = start;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                    if (dist[next] > dist[farNode]) farNode = next;
                }
            }
        }
        return new int[]{farNode, dist[farNode]};
    }
}