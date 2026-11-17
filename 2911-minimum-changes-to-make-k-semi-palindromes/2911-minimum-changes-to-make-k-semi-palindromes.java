class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // Precompute factors for each length
        List<Integer>[] factors = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            factors[i] = new ArrayList<>();
            factors[i].add(1); // 1 is always a valid divisor
        }
        for (int d = 2; d < n; d++) {
            for (int multiple = d * 2; multiple <= n; multiple += d) {
                factors[multiple].add(d);
            }
        }
        
        // cost[i][j] = min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minCost = len; // Max possible changes
                for (int d : factors[len]) {
                    minCost = Math.min(minCost, getCost(s, i, j, d));
                }
                cost[i][j] = minCost;
            }
        }
        
        // dp[i][j] = min changes to partition first i chars into j parts
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int parts = 1; parts <= k; parts++) {
                // The last partition is s[j...i-1]
                for (int j = 0; j < i; j++) {
                    // Ensure the substring length is at least 2 (semi-palindrome requires len >= 2)
                    if (i - j < 2) continue;
                    if (dp[j][parts - 1] != Integer.MAX_VALUE / 2) {
                        dp[i][parts] = Math.min(dp[i][parts], 
                            dp[j][parts - 1] + cost[j][i - 1]);
                    }
                }
            }
        }
        
        return dp[n][k];
    }
    
    // Calculate changes to make s[i..j] a semi-palindrome with divisor d
    private int getCost(String s, int i, int j, int d) {
        int changes = 0;
        int len = j - i + 1;
        // For each offset group (0 to d-1)
        for (int offset = 0; offset < d; offset++) {
            int left = i + offset;
            int right = j - d + 1 + offset;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    changes++;
                }
                left += d;
                right -= d;
            }
        }
        return changes;
    }
}