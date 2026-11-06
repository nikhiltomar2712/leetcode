import java.util.*;

class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        // Collect all distinct strings
        Map<String, Integer> id = new HashMap<>();
        List<String> nodes = new ArrayList<>();
        for (String s : original) {
            if (!id.containsKey(s)) { id.put(s, nodes.size()); nodes.add(s); }
        }
        for (String s : changed) {
            if (!id.containsKey(s)) { id.put(s, nodes.size()); nodes.add(s); }
        }
        int N = nodes.size();
        long INF = Long.MAX_VALUE / 4;
        long[][] dist = new long[N][N];
        for (long[] row : dist) Arrays.fill(row, INF);
        for (int i = 0; i < N; i++) dist[i][i] = 0;

        for (int i = 0; i < original.length; i++) {
            int u = id.get(original[i]), v = id.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], (long) cost[i]);
        }

        // Floyd-Warshall
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 0; j < N; j++) {
                    if (dist[k][j] == INF) continue;
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Relevant substring lengths (only those in graph)
        Set<Integer> lengths = new HashSet<>();
        for (String s : nodes) lengths.add(s.length());

        int n = source.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == INF) continue;
            if (source.charAt(i) == target.charAt(i) && dp[i] < dp[i + 1]) {
                dp[i + 1] = dp[i];
            }
            for (int len : lengths) {
                int j = i + len;
                if (j > n) continue;
                String s = source.substring(i, j);
                String t = target.substring(i, j);
                if (s.equals(t)) {
                    if (dp[i] < dp[j]) dp[j] = dp[i];
                    continue;
                }
                Integer u = id.get(s), v = id.get(t);
                if (u != null && v != null && dist[u][v] != INF) {
                    if (dp[i] + dist[u][v] < dp[j]) {
                        dp[j] = dp[i] + dist[u][v];
                    }
                }
            }
        }

        return dp[n] == INF ? -1 : dp[n];
    }
}