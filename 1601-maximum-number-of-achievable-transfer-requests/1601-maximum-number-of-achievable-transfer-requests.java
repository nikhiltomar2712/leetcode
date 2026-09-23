class Solution {
    public int maximumRequests(int n, int[][] requests) {
        int m = requests.length;
        int best = 0;

        for (int mask = 0; mask < (1 << m); mask++) {
            int[] balance = new int[n];
            int count = 0;

            for (int i = 0; i < m; i++) {
                if ((mask & (1 << i)) != 0) {
                    balance[requests[i][0]]--;  // leaving
                    balance[requests[i][1]]++;  // arriving
                    count++;
                }
            }

            // Check if all balances are zero
            boolean valid = true;
            for (int b : balance) {
                if (b != 0) { valid = false; break; }
            }

            if (valid) best = Math.max(best, count);
        }

        return best;
    }
}