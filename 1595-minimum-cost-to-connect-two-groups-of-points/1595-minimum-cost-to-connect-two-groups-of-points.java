import java.util.Arrays;

class Solution {
    public int connectTwoGroups(java.util.List<java.util.List<Integer>> cost) {
        int size1 = cost.size();
        int size2 = cost.get(0).size();
        int FULL = (1 << size2) - 1;
        int INF = Integer.MAX_VALUE / 2;

        // minCost2[j] = cheapest cost to connect group-2 point j to ANY group-1 point
        int[] minCost2 = new int[size2];
        Arrays.fill(minCost2, INF);
        for (int j = 0; j < size2; j++) {
            for (int i = 0; i < size1; i++) {
                minCost2[j] = Math.min(minCost2[j], cost.get(i).get(j));
            }
        }

        // dp[mask] = min cost after processing some group-1 points, with group-2 connectivity = mask
        int[] dp = new int[1 << size2];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < size1; i++) {
            int[] next = new int[1 << size2];
            Arrays.fill(next, INF);

            for (int mask = 0; mask <= FULL; mask++) {
                if (dp[mask] == INF) continue;

                // Connect group-1 point i to each group-2 point j
                for (int j = 0; j < size2; j++) {
                    int newMask = mask | (1 << j);
                    int c = cost.get(i).get(j);
                    next[newMask] = Math.min(next[newMask], dp[mask] + c);
                }
            }
            dp = next;
        }

        // Now all group-1 points are connected. Fill in any group-2 points not yet connected.
        int result = INF;
        for (int mask = 0; mask <= FULL; mask++) {
            if (dp[mask] == INF) continue;

            int total = dp[mask];
            // For each group-2 point not in mask, add its cheapest connection
            for (int j = 0; j < size2; j++) {
                if ((mask & (1 << j)) == 0) {
                    total += minCost2[j];
                }
            }
            result = Math.min(result, total);
        }

        return result;
    }
}