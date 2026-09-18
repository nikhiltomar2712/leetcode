class TreeAncestor {
    private int[][] up;
    private int LOG;

    public TreeAncestor(int n, int[] parent) {
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;   // enough levels to cover n

        up = new int[LOG][n];
        up[0] = parent.clone();

        for (int j = 1; j < LOG; j++) {
            for (int v = 0; v < n; v++) {
                int mid = up[j - 1][v];
                up[j][v] = (mid == -1) ? -1 : up[j - 1][mid];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for (int j = 0; j < LOG && node != -1; j++) {
            if (((k >> j) & 1) == 1) {
                node = up[j][node];
            }
        }
        return node;
    }
}