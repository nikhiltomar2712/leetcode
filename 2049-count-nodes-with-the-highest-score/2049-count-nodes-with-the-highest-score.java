class Solution {
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            children[parents[i]].add(i);
        }
        int[] size = new int[n];
        dfs(0, children, size);
        long maxScore = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            long score = 1;
            for (int child : children[i]) {
                score *= size[child];
            }
            if (n - size[i] > 0) {
                score *= (n - size[i]);
            }
            if (score > maxScore) {
                maxScore = score;
                count = 1;
            } else if (score == maxScore) {
                count++;
            }
        }
        return count;
    }

    private int dfs(int u, List<Integer>[] children, int[] size) {
        size[u] = 1;
        for (int v : children[u]) {
            size[u] += dfs(v, children, size);
        }
        return size[u];
    }
}