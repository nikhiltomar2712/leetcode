class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int[] degree = new int[n];
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
            degree[e[0]]++;
            degree[e[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1 && coins[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            degree[u]--;
            for (int v : graph[u]) {
                if (--degree[v] == 1 && coins[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1 && coins[i] == 1) {
                queue.offer(i);
            }
        }
        int removed = 0;
        int round = 0;
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty() && round < 2) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                visited[u] = true;
                degree[u]--;
                removed++;
                for (int v : graph[u]) {
                    if (!visited[v] && --degree[v] == 1) {
                        queue.offer(v);
                    }
                }
            }
            round++;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && degree[i] > 0) res++;
        }
        return res > 0 ? (res - 1) * 2 : 0;
    }
}