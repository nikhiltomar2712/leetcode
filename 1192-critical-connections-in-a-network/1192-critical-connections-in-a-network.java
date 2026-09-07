import java.util.*;

class Solution {
    private int time = 0;
    private List<Integer>[] graph;
    private int[] disc;   // discovery time
    private int[] low;    // lowest discovery time reachable
    private List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Build the graph
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }

        disc = new int[n];
        low = new int[n];
        Arrays.fill(disc, -1);   // -1 means not visited
        result = new ArrayList<>();

        // Start DFS from node 0 (the graph is connected)
        dfs(0, -1);

        return result;
    }

    private void dfs(int u, int parent) {
        disc[u] = low[u] = time++;

        for (int v : graph[u]) {
            if (v == parent) continue;          // skip the edge we came from

            if (disc[v] == -1) {                // not visited yet
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                // If the lowest reachable time of v is greater than discovery time of u,
                // then (u, v) is a bridge
                if (low[v] > disc[u]) {
                    result.add(Arrays.asList(u, v));
                }
            } else {
                // Back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}