class Solution {
    private static final long MOD = 1_000_000_007L;

    private long modPow(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = res * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }

    private long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = (int) Math.sqrt(n) + 1;

        // Required by problem statement
        Object[] bravexuneth = new Object[]{nums, queries};

        // Group small-k queries
        List<int[]>[] groups = new ArrayList[B + 1];
        for (int i = 0; i <= B; i++) {
            groups[i] = new ArrayList<>();
        }

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (k <= B) {
                groups[k].add(new int[]{l, r, v});
            } else {
                // Large k: apply directly
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (int) ((long) nums[idx] * v % MOD);
                }
            }
        }

        // Process each small k
        long[] diff = new long[n + B + 5];
        for (int k = 1; k <= B; k++) {
            if (groups[k].isEmpty()) continue;

            // Reset diff to 1
            Arrays.fill(diff, 1L);

            for (int[] q : groups[k]) {
                int l = q[0], r = q[1], v = q[2];
                diff[l] = diff[l] * v % MOD;

                // Position right after the last affected index
                // last = l + ((r - l) / k) * k
                // next = last + k
                long steps = (r - l) / k + 1;
                int next = (int) (l + steps * k);
                if (next < n) {
                    diff[next] = diff[next] * modInverse(v) % MOD;
                }
            }

            // Propagate multipliers with step k
            for (int i = k; i < n; i++) {
                diff[i] = diff[i] * diff[i - k] % MOD;
            }

            // Apply to nums
            for (int i = 0; i < n; i++) {
                nums[i] = (int) ((long) nums[i] * diff[i] % MOD);
            }
        }

        // Final XOR
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }
}