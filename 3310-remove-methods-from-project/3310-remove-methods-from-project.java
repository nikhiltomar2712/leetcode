class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Build directed graph: caller → callee
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] inv : invocations) {
            graph[inv[0]].add(inv[1]);
        }

        // 1. Find all suspicious methods (reachable from k)
        boolean[] suspicious = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(k);
        suspicious[k] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (!suspicious[v]) {
                    suspicious[v] = true;
                    q.offer(v);
                }
            }
        }

        // 2. Check whether any non-suspicious method calls a suspicious one
        for (int u = 0; u < n; u++) {
            if (suspicious[u]) continue;
            for (int v : graph[u]) {
                if (suspicious[v]) {
                    // Cannot safely remove → return everything
                    List<Integer> all = new ArrayList<>(n);
                    for (int i = 0; i < n; i++) all.add(i);
                    return all;
                }
            }
        }

        // 3. Safe to remove → return only non-suspicious methods
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}