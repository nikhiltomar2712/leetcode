class Solution {
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int[] parent = new int[n];
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            cost[i] = -1;
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int ru = find(parent, u), rv = find(parent, v);
            cost[ru] &= w;
            if (ru != rv) {
                parent[rv] = ru;
                cost[ru] &= cost[rv];
            }
        }
        int[] res = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int u = query[i][0], v = query[i][1];
            int ru = find(parent, u), rv = find(parent, v);
            if (ru == rv) {
                res[i] = cost[ru];
            } else {
                res[i] = -1;
            }
        }
        return res;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}