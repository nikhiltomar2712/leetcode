class Solution {
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        boolean[] res = new boolean[requests.length];
        for (int i = 0; i < requests.length; i++) {
            int u = find(parent, requests[i][0]);
            int v = find(parent, requests[i][1]);
            boolean valid = true;
            if (u != v) {
                for (int[] r : restrictions) {
                    int ru = find(parent, r[0]);
                    int rv = find(parent, r[1]);
                    if ((ru == u && rv == v) || (ru == v && rv == u)) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    parent[u] = v;
                }
            }
            res[i] = valid;
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