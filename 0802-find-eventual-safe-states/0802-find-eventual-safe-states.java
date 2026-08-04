class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> reverseGraph = new ArrayList<>();
        int[] outDegree = new int[n];

        for (int i = 0; i < n; i++) {
            reverseGraph.add(new ArrayList<>());
        }

        // Build reverse graph and record original out-degrees
        for (int u = 0; u < n; u++) {
            for (int v : graph[u]) {
                reverseGraph.get(v).add(u);
            }
            outDegree[u] = graph[u].length;
        }

        // Nodes with out-degree 0 are terminal → safe
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int prev : reverseGraph.get(node)) {
                if (--outDegree[prev] == 0) {
                    queue.offer(prev);
                }
            }
        }

        // All nodes that ended with out-degree 0 are safe
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0) {
                result.add(i);
            }
        }
        return result;
    }
}