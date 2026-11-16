class Solution {
    private int n;
    private int[] nums;
    private int[][] memo;      // memo[mask][last] = min remaining cost
    private int[][] bestNext;  // bestNext[mask][last] = next index to pick

    public int[] findPermutation(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        int fullMask = (1 << n) - 1;

        memo = new int[1 << n][n];
        bestNext = new int[1 << n][n];

        for (int[] row : memo) {
            java.util.Arrays.fill(row, -1);
        }

        // Start with index 0 fixed as the first element
        dp(1, 0);

        // Reconstruct the lexicographically smallest permutation
        int[] result = new int[n];
        int mask = 1;
        int last = 0;
        result[0] = 0;

        for (int i = 1; i < n; i++) {
            int next = bestNext[mask][last];
            result[i] = next;
            mask |= (1 << next);
            last = next;
        }

        return result;
    }

    private int dp(int mask, int last) {
        // Base case: all indices used, add the closing edge back to 0
        if (mask == (1 << n) - 1) {
            return Math.abs(last - nums[0]);
        }

        if (memo[mask][last] != -1) {
            return memo[mask][last];
        }

        int minCost = Integer.MAX_VALUE;
        int bestChoice = -1;

        // Iterate in increasing order to naturally get lexicographically smallest
        for (int next = 1; next < n; next++) {
            if ((mask & (1 << next)) == 0) {
                int cost = Math.abs(last - nums[next]) + dp(mask | (1 << next), next);
                if (cost < minCost) {
                    minCost = cost;
                    bestChoice = next;
                }
            }
        }

        memo[mask][last] = minCost;
        bestNext[mask][last] = bestChoice;
        return minCost;
    }
}