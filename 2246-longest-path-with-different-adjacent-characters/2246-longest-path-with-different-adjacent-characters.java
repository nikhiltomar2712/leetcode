class Solution {
    int res = 0;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }
        dfs(0, children, s);
        return res;
    }

    private int dfs(int u, List<Integer>[] children, String s) {
        int max1 = 0, max2 = 0;
        for (int v : children[u]) {
            int len = dfs(v, children, s);
            if (s.charAt(v) == s.charAt(u)) continue;
            if (len > max1) {
                max2 = max1;
                max1 = len;
            } else if (len > max2) {
                max2 = len;
            }
        }
        res = Math.max(res, max1 + max2 + 1);
        return max1 + 1;
    }
}