class Solution {
    private static final int MOD = 1000000007;
    private Integer[][][] memo;
    
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        memo = new Integer[m][n][maxMove + 1];
        return dp(m, n, maxMove, startRow, startColumn);
    }
    
    private int dp(int m, int n, int moves, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n) return 1;
        if (moves == 0) return 0;
        if (memo[r][c][moves] != null) return memo[r][c][moves];
        
        int paths = 0;
        paths = (paths + dp(m, n, moves - 1, r - 1, c)) % MOD;
        paths = (paths + dp(m, n, moves - 1, r + 1, c)) % MOD;
        paths = (paths + dp(m, n, moves - 1, r, c - 1)) % MOD;
        paths = (paths + dp(m, n, moves - 1, r, c + 1)) % MOD;
        
        memo[r][c][moves] = paths;
        return paths;
    }
}