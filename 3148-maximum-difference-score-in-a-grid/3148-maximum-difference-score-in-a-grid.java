class Solution {
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dp = new int[m][n]; // dp[i][j] = min value reachable to (i,j)
        int maxScore = Integer.MIN_VALUE;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int current = grid.get(i).get(j);
                int minPrev = Integer.MAX_VALUE;
                
                if (i > 0) minPrev = Math.min(minPrev, dp[i - 1][j]);
                if (j > 0) minPrev = Math.min(minPrev, dp[i][j - 1]);
                
                if (minPrev != Integer.MAX_VALUE) {
                    maxScore = Math.max(maxScore, current - minPrev);
                }
                
                dp[i][j] = Math.min(current, minPrev);
            }
        }
        
        return maxScore;
    }
}