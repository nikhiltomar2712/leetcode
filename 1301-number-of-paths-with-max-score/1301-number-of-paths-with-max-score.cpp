class Solution {
public:
    vector<int> pathsWithMaxScore(vector<string>& board) {
        const int MOD = 1e9 + 7;
        int n = board.size();
        
        // dp[i][j][0] = maxScore, dp[i][j][1] = count
        vector<vector<vector<int>>> dp(n, vector<vector<int>>(n, vector<int>(2, 0)));
        
        // Initialize all as unreachable
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = -1;
            }
        }
        
        dp[n-1][n-1][0] = 0;
        dp[n-1][n-1][1] = 1;
        
        // Directions: up, left, up-left
        vector<pair<int, int>> dirs = {{1, 0}, {0, 1}, {1, 1}};
        
        for (int i = n-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (board[i][j] == 'X') {
                    dp[i][j][0] = -1;
                    dp[i][j][1] = 0;
                    continue;
                }
                if (i == n-1 && j == n-1) continue;
                
                int maxScore = -1;
                int count = 0;
                
                // Check from (i+1, j)
                if (i + 1 < n && dp[i+1][j][0] != -1) {
                    maxScore = dp[i+1][j][0];
                    count = dp[i+1][j][1];
                }
                
                // Check from (i, j+1)
                if (j + 1 < n && dp[i][j+1][0] != -1) {
                    if (dp[i][j+1][0] > maxScore) {
                        maxScore = dp[i][j+1][0];
                        count = dp[i][j+1][1];
                    } else if (dp[i][j+1][0] == maxScore) {
                        count = (count + dp[i][j+1][1]) % MOD;
                    }
                }
                
                // Check from (i+1, j+1)
                if (i + 1 < n && j + 1 < n && dp[i+1][j+1][0] != -1) {
                    if (dp[i+1][j+1][0] > maxScore) {
                        maxScore = dp[i+1][j+1][0];
                        count = dp[i+1][j+1][1];
                    } else if (dp[i+1][j+1][0] == maxScore) {
                        count = (count + dp[i+1][j+1][1]) % MOD;
                    }
                }
                
                if (maxScore != -1 && board[i][j] != 'E') {
                    maxScore += (board[i][j] - '0');
                }
                
                dp[i][j][0] = maxScore;
                dp[i][j][1] = count;
            }
        }
        
        if (dp[0][0][0] == -1) return {0, 0};
        return {dp[0][0][0], dp[0][0][1]};
    }
};