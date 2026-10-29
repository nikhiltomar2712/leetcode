import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] tin, tout, depth;
    private int[][] up;
    private int timer, LOG;
    private char[] chars;
    private BIT bit;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        chars = s.toCharArray();
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        up = new int[n][LOG];
        timer = 0;

        dfs(0, -1);          // root at 0

        bit = new BIT(2 * n + 5);
        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            // range update [tin[i], tout[i]] so that prefix queries give root-to-node XOR
            bit.update(tin[i], mask);
            bit.update(tout[i] + 1, mask);
        }

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int delta = oldMask ^ newMask;
                if (delta != 0) {
                    bit.update(tin[u], delta);
                    bit.update(tout[u] + 1, delta);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int l = lca(u, v);
                int pathMask = bit.query(tin[u]) ^ bit.query(tin[v]) ^ (1 << (chars[l] - 'a'));
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }
        return ans;
    }

    private void dfs(int u, int p) {
        tin[u] = ++timer;
        up[u][0] = p == -1 ? u : p;
        depth[u] = (p == -1) ? 0 : depth[p] + 1;
        for (int k = 1; k < LOG; k++) {
            up[u][k] = up[up[u][k - 1]][k - 1];
        }
        for (int v : adj[u]) {
            if (v != p) dfs(v, u);
        }
        tout[u] = timer;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u; u = v; v = t;
        }
        int diff = depth[u] - depth[v];
        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) u = up[u][k];
        }
        if (u == v) return u;
        for (int k = LOG - 1; k >= 0; k--) {
            if (up[u][k] != up[v][k]) {
                u = up[u][k];
                v = up[v][k];
            }
        }
        return up[u][0];
    }

    // Fenwick Tree supporting XOR updates and prefix XOR queries
    static class BIT {
        int[] tree;
        BIT(int n) { tree = new int[n + 2]; }
        void update(int i, int val) {
            for (; i < tree.length; i += i & -i) tree[i] ^= val;
        }
        int query(int i) {
            int res = 0;
            for (; i > 0; i -= i & -i) res ^= tree[i];
            return res;
        }
    }
}