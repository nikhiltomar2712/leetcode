class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[n];
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            indegree[e[1]]++;
        }
        List<Set<Integer>> ancestors = new ArrayList<>();
        for (int i = 0; i < n; i++) ancestors.add(new TreeSet<>());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph[u]) {
                ancestors.get(v).add(u);
                ancestors.get(v).addAll(ancestors.get(u));
                if (--indegree[v] == 0) queue.offer(v);
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>(ancestors.get(i)));
        }
        return res;
    }
}