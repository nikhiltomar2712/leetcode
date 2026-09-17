import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        int totalMasks = 1 << n;

        // For each hat (1..40), list of people who like it
        List<Integer>[] hatToPeople = new List[41];
        for (int h = 1; h <= 40; h++) {
            hatToPeople[h] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int h : hats.get(i)) {
                hatToPeople[h].add(i);
            }
        }

        int[] dp = new int[totalMasks];
        dp[0] = 1;

        for (int h = 1; h <= 40; h++) {
            // Clone so each hat is used at most once per transition step
            int[] next = dp.clone();
            for (int mask = 0; mask < totalMasks; mask++) {
                if (dp[mask] == 0) continue;
                for (int person : hatToPeople[h]) {
                    if ((mask & (1 << person)) == 0) {
                        int newMask = mask | (1 << person);
                        next[newMask] = (next[newMask] + dp[mask]) % MOD;
                    }
                }
            }
            dp = next;
        }

        return dp[totalMasks - 1];
    }
}