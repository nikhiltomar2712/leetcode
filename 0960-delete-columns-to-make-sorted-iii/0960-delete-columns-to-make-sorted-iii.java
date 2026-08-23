class Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs[0].length();          // number of columns
        int[] dp = new int[m];
        Arrays.fill(dp, 1);                // each column alone is a valid subsequence
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < i; j++) {
                if (canFollow(strs, j, i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int maxKeep = 0;
        for (int len : dp) {
            maxKeep = Math.max(maxKeep, len);
        }
        
        return m - maxKeep;                // minimum deletions
    }
    
    // Returns true if column j can come before column i in every row
    private boolean canFollow(String[] strs, int j, int i) {
        for (String s : strs) {
            if (s.charAt(j) > s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}