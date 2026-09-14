class Solution {
    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        int k = n / 3;               // number of slices you can take
        
        // Case 1: exclude the last slice
        int case1 = maxSum(slices, 0, n - 2, k);
        
        // Case 2: exclude the first slice
        int case2 = maxSum(slices, 1, n - 1, k);
        
        return Math.max(case1, case2);
    }
    
    // Maximum sum by picking exactly 'k' non-adjacent elements from slices[start..end]
    private int maxSum(int[] slices, int start, int end, int k) {
        int m = end - start + 1;
        // dp[i][j] = max sum using the first i slices, picking j of them
        int[][] dp = new int[m + 1][k + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= k; j++) {
                // Option 1: skip current slice
                int skip = dp[i - 1][j];
                
                // Option 2: take current slice (cannot take previous)
                int take = slices[start + i - 1];
                if (i >= 2) {
                    take += dp[i - 2][j - 1];
                } else if (j == 1) {
                    // only one pick and this is the first slice
                    // take stays as is
                } else {
                    take = Integer.MIN_VALUE / 2; // impossible
                }
                
                dp[i][j] = Math.max(skip, take);
            }
        }
        
        return dp[m][k];
    }
}