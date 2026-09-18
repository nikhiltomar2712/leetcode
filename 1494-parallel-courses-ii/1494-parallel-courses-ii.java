class Solution {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] prereq = new int[n];
        for (int[] r : relations) {
            prereq[r[1] - 1] |= (1 << (r[0] - 1));
        }

        int full = (1 << n) - 1;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // Precompute popcount for all masks
        int[] pop = new int[1 << n];
        for (int m = 1; m < (1 << n); m++) {
            pop[m] = pop[m >> 1] + (m & 1);
        }

        for (int mask = 0; mask < (1 << n); mask++) {
            if (dp[mask] == Integer.MAX_VALUE) continue;

            // Find all courses whose prereqs are satisfied and not taken
            int available = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0 && (prereq[i] & mask) == prereq[i]) {
                    available |= (1 << i);
                }
            }

            if (available == 0) continue;

            if (pop[available] <= k) {
                // Take all available courses in one semester
                int next = mask | available;
                dp[next] = Math.min(dp[next], dp[mask] + 1);
            } else {
                // Enumerate all subsets of `available` with exactly k bits
                for (int sub = available; sub > 0; sub = (sub - 1) & available) {
                    if (pop[sub] == k) {
                        int next = mask | sub;
                        dp[next] = Math.min(dp[next], dp[mask] + 1);
                    }
                }
            }
        }

        return dp[full];
    }
}