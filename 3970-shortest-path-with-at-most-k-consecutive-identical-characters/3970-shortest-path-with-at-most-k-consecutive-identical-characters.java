import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: list of (to, weight)
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        // dist[u][cnt] = min cost to reach u with current consecutive run length = cnt
        // cnt ranges from 1 to k
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // PriorityQueue: (cost, node, consecutiveCount)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Start at node 0 with consecutive count 1 (the single label of node 0)
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int u = cur[1];
            int cnt = cur[2];

            // Skip if we already found a better way to this state
            if (cost > dist[u][cnt]) continue;

            // First time we extract n-1 is optimal (non-negative weights)
            if (u == n - 1) {
                return cost;
            }

            char labelU = labels.charAt(u);

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];
                char labelV = labels.charAt(v);

                int nextCnt = (labelU == labelV) ? cnt + 1 : 1;

                if (nextCnt > k) continue;          // violates the constraint

                int newCost = cost + w;
                if (newCost < dist[v][nextCnt]) {
                    dist[v][nextCnt] = newCost;
                    pq.offer(new int[]{newCost, v, nextCnt});
                }
            }
        }

        return -1;   // unreachable under the constraint
    }
}