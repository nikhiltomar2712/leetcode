class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[n];
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            indegree[e[1]]++;
        }
        int[][] dp = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        int visited = 0, res = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited++;
            int c = colors.charAt(u) - 'a';
            dp[u][c]++;
            res = Math.max(res, dp[u][c]);
            for (int v : graph[u]) {
                for (int i = 0; i < 26; i++) {
                    dp[v][i] = Math.max(dp[v][i], dp[u][i]);
                }
                if (--indegree[v] == 0) queue.offer(v);
            }
        }
        return visited == n ? res : -1;
    }
}