import java.util.*;

class Solution {
    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        long[] ans = new long[n];
        dfs(0, -1, graph, cost, ans);
        return ans;
    }

    private List<Long> dfs(int node, int parent, List<Integer>[] graph, int[] cost, long[] ans) {
        List<Long> values = new ArrayList<>();
        values.add((long) cost[node]);

        for (int neighbor : graph[node]) {
            if (neighbor == parent) continue;
            values.addAll(dfs(neighbor, node, graph, cost, ans));
        }

        Collections.sort(values);
        int size = values.size();

        if (size < 3) {
            ans[node] = 1;
        } else {
            long maxProduct = Math.max(
                values.get(0) * values.get(1) * values.get(size - 1),
                values.get(size - 3) * values.get(size - 2) * values.get(size - 1)
            );
            ans[node] = Math.max(0L, maxProduct);
        }

        if (size <= 5) {
            return values;
        }

        List<Long> compressed = new ArrayList<>();
        compressed.add(values.get(0));
        compressed.add(values.get(1));
        compressed.add(values.get(size - 3));
        compressed.add(values.get(size - 2));
        compressed.add(values.get(size - 1));
        return compressed;
    }
}