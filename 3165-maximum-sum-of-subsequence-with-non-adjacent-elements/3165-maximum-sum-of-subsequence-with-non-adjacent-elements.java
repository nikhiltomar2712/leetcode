class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] tree;   // tree[node] = {s00, s01, s10, s11}

    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        int n = nums.length;
        tree = new long[4 * n][4];
        build(1, 0, n - 1, nums);

        long ans = 0;
        for (int[] q : queries) {
            update(1, 0, n - 1, q[0], q[1]);
            // s11 of the root is the maximum non-adjacent sum of the whole array
            ans = (ans + tree[1][3]) % MOD;
        }
        return (int) ans;
    }

    private void merge(int node, int leftChild, int rightChild) {
        long a00 = tree[leftChild][0], a01 = tree[leftChild][1];
        long a10 = tree[leftChild][2], a11 = tree[leftChild][3];
        long b00 = tree[rightChild][0], b01 = tree[rightChild][1];
        long b10 = tree[rightChild][2], b11 = tree[rightChild][3];

        // Combine while respecting the non-adjacent constraint at the middle
        tree[node][0] = Math.max(a00 + b10, a01 + b00); // neither endpoint
        tree[node][1] = Math.max(a00 + b11, a01 + b01); // left free / right taken
        tree[node][2] = Math.max(a10 + b10, a11 + b00); // left taken / right free
        tree[node][3] = Math.max(a10 + b11, a11 + b01); // both free (unconstrained max)
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            // Leaf: empty sum = 0, taking the element = max(0, val)
            tree[node][0] = 0;
            tree[node][1] = 0;
            tree[node][2] = 0;
            tree[node][3] = Math.max(0L, nums[l]);
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        merge(node, 2 * node, 2 * node + 1);
    }

    private void update(int node, int l, int r, int pos, int val) {
        if (l == r) {
            tree[node][0] = 0;
            tree[node][1] = 0;
            tree[node][2] = 0;
            tree[node][3] = Math.max(0L, val);
            return;
        }
        int mid = l + (r - l) / 2;
        if (pos <= mid) {
            update(2 * node, l, mid, pos, val);
        } else {
            update(2 * node + 1, mid + 1, r, pos, val);
        }
        merge(node, 2 * node, 2 * node + 1);
    }
}