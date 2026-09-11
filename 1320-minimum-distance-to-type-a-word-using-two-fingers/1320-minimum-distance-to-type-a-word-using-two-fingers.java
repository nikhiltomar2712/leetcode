class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        final int OFF = 26; // virtual "off keyboard" state
        final int INF = Integer.MAX_VALUE / 2;
        
        // dp[i][j] = min distance after typing word[0..i],
        // with one finger on word[i], other on word[j] (or OFF)
        int[][] dp = new int[n][27];
        for (int[] row : dp) Arrays.fill(row, INF);
        
        // Base: type word[0] with one finger, other finger is OFF
        dp[0][OFF] = 0;
        
        for (int i = 1; i < n; i++) {
            int curr = word.charAt(i) - 'A';
            int prev = word.charAt(i - 1) - 'A';
            int moveCost = dist(prev, curr);
            
            for (int j = 0; j <= OFF; j++) {
                if (dp[i - 1][j] == INF) continue;
                
                // Option A: same finger moves from prev to curr
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + moveCost);
                
                // Option B: other finger (on j) moves to curr;
                // the finger previously on prev stays put (now it's the "other")
                int cost = (j == OFF) ? 0 : dist(j, curr);
                dp[i][prev] = Math.min(dp[i][prev], dp[i - 1][j] + cost);
            }
        }
        
        // Answer: best over all final positions of the "other" finger
        int ans = INF;
        for (int j = 0; j <= OFF; j++) {
            ans = Math.min(ans, dp[n - 1][j]);
        }
        return ans;
    }
    
    // Manhattan distance between two keys (0-25), or 0 if one is OFF
    private int dist(int a, int b) {
        if (a == 26 || b == 26) return 0;
        int rowA = a / 6, colA = a % 6;
        int rowB = b / 6, colB = b % 6;
        return Math.abs(rowA - rowB) + Math.abs(colA - colB);
    }
}