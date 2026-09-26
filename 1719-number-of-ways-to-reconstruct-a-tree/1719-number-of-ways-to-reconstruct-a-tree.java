class Solution {
    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] p : pairs) {
            graph.computeIfAbsent(p[0], k -> new HashSet<>()).add(p[1]);
            graph.computeIfAbsent(p[1], k -> new HashSet<>()).add(p[0]);
        }

        int n = graph.size();
        int root = -1;
        for (int node : graph.keySet()) {
            if (graph.get(node).size() == n - 1) {
                root = node;
                break;
            }
        }
        if (root == -1) return 0;

        int ways = 1;
        for (int node : graph.keySet()) {
            if (node == root) continue;
            int parent = -1;
            int parentDegree = Integer.MAX_VALUE;
            for (int neighbor : graph.get(node)) {
                if (graph.get(neighbor).size() < parentDegree
                        && graph.get(neighbor).size() >= graph.get(node).size()) {
                    parent = neighbor;
                    parentDegree = graph.get(neighbor).size();
                }
            }
            if (parent == -1) return 0;
            for (int neighbor : graph.get(node)) {
                if (neighbor != parent && !graph.get(parent).contains(neighbor)) {
                    return 0;
                }
            }
            if (graph.get(parent).size() == graph.get(node).size()) {
                ways = 2;
            }
        }

        return ways;
    }
}