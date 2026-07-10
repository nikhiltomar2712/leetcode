class Solution(object):
    def calculateMinimumHP(self, dungeon):
        """
        :type dungeon: List[List[int]]
        :rtype: int
        """
        m, n = len(dungeon), len(dungeon[0])
        
        # dp[i][j] = min health needed at (i,j) to reach end
        dp = [[0] * n for _ in range(m)]
        
        # Bottom-right cell
        dp[m-1][n-1] = max(1, 1 - dungeon[m-1][n-1])
        
        # Last row (can only move right)
        for j in range(n-2, -1, -1):
            dp[m-1][j] = max(1, dp[m-1][j+1] - dungeon[m-1][j])
        
        # Last column (can only move down)
        for i in range(m-2, -1, -1):
            dp[i][n-1] = max(1, dp[i+1][n-1] - dungeon[i][n-1])
        
        # Fill the rest
        for i in range(m-2, -1, -1):
            for j in range(n-2, -1, -1):
                min_health_on_exit = min(dp[i+1][j], dp[i][j+1])
                dp[i][j] = max(1, min_health_on_exit - dungeon[i][j])
        
        return dp[0][0] 