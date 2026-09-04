class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        // dp[i] = minimum height needed to place the first i books
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;   // no books → height 0
        
        for (int i = 1; i <= n; i++) {
            int width = 0;
            int maxHeight = 0;
            
            // Try placing books[j..i-1] on the last shelf
            for (int j = i; j >= 1; j--) {
                width += books[j - 1][0];
                if (width > shelfWidth) break;
                
                maxHeight = Math.max(maxHeight, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + maxHeight);
            }
        }
        
        return dp[n];
    }
}