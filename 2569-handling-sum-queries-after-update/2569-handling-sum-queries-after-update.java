class Solution {
    int[] tree;
    int[] lazy;
    int n;

    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        n = nums1.length;
        tree = new int[4 * n];
        lazy = new int[4 * n];
        build(nums1, 1, 0, n - 1);
        long sum2 = 0;
        for (int num : nums2) sum2 += num;
        List<Long> resList = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                update(1, 0, n - 1, q[1], q[2]);
            } else if (q[0] == 2) {
                sum2 += (long) query(1, 0, n - 1, 0, n - 1) * q[1];
            } else {
                resList.add(sum2);
            }
        }
        long[] res = new long[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    private void build(int[] nums, int node, int l, int r) {
        if (l == r) {
            tree[node] = nums[l];
            return;
        }
        int mid = (l + r) / 2;
        build(nums, 2 * node, l, mid);
        build(nums, 2 * node + 1, mid + 1, r);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    private void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            tree[node] = (r - l + 1) - tree[node];
            lazy[node] ^= 1;
            return;
        }
        int mid = (l + r) / 2;
        if (lazy[node] == 1) {
            applyFlip(2 * node, l, mid);
            applyFlip(2 * node + 1, mid + 1, r);
            lazy[node] = 0;
        }
        update(2 * node, l, mid, ql, qr);
        update(2 * node + 1, mid + 1, r, ql, qr);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    private void applyFlip(int node, int l, int r) {
        tree[node] = (r - l + 1) - tree[node];
        lazy[node] ^= 1;
    }

    private int query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) / 2;
        if (lazy[node] == 1) {
            applyFlip(2 * node, l, mid);
            applyFlip(2 * node + 1, mid + 1, r);
            lazy[node] = 0;
        }
        return query(2 * node, l, mid, ql, qr) + query(2 * node + 1, mid + 1, r, ql, qr);
    }
}