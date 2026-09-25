class Solution {
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        
        // Prefix sum: prefix[i] = sum of stones[0..i-1]
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }
        
        // dp[i][j] = max score difference current player can achieve on stones[i..j]
        int[][] dp = new int[n][n];
        
        // Base case: single stone => 0 difference (no points for removing last stone)
        
        // Fill by increasing length
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                
                // Option 1: Remove left stone (stones[i])
                // Gain = sum of remaining stones[i+1..j]
                // Net = Gain - opponent's best difference on [i+1..j]
                int sumLeft = prefix[j + 1] - prefix[i + 1];
                int optionLeft = sumLeft - dp[i + 1][j];
                
                // Option 2: Remove right stone (stones[j])
                // Gain = sum of remaining stones[i..j-1]
                // Net = Gain - opponent's best difference on [i..j-1]
                int sumRight = prefix[j] - prefix[i];
                int optionRight = sumRight - dp[i][j - 1];
                
                dp[i][j] = Math.max(optionLeft, optionRight);
            }
        }
        
        return dp[0][n - 1];
    }
}