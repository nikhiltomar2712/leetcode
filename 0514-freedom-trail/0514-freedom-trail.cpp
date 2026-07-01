class Solution {
public:
    int findRotateSteps(string ring, string key) {
        int n = ring.size();
        int m = key.size();
        
        // Preprocess: store all indices where each character appears in the ring
        unordered_map<char, vector<int>> pos;
        for (int i = 0; i < n; i++) {
            pos[ring[i]].push_back(i);
        }
        
        // dp[i][j] = minimum steps to spell key[i:] 
        // when the pointer is at ring index j
        // We'll use a 2D DP array with dimensions (m+1) x n
        vector<vector<int>> dp(m + 1, vector<int>(n, INT_MAX));
        
        // Base case: no more characters to spell, cost 0
        fill(dp[m].begin(), dp[m].end(), 0);
        
        // Iterate from the last character of key backwards
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                // Try all positions where key[i] appears in the ring
                for (int k : pos[key[i]]) {
                    // Calculate min distance (clockwise or counterclockwise)
                    int dist = min(abs(j - k), n - abs(j - k));
                    // 1 for pressing the button + dist to rotate + future cost
                    dp[i][j] = min(dp[i][j], dist + 1 + dp[i + 1][k]);
                }
            }
        }
        
        // Start at ring index 0
        return dp[0][0];
    }
};