class Solution {
public:
    const int MOD = 1e9 + 7;
    
    int gcd(int a, int b) {
        while (b) {
            a %= b;
            swap(a, b);
        }
        return a;
    }
    
    int subsequencePairCount(vector<int>& nums) {
        int maxVal = *max_element(nums.begin(), nums.end());
        
        // dp[g1][g2] = number of ways
        vector<vector<long long>> dp(maxVal + 1, vector<long long>(maxVal + 1, 0));
        dp[0][0] = 1;  // empty state
        
        for (int x : nums) {
            vector<vector<long long>> new_dp = dp;
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;
                    
                    // Add to first subsequence
                    int new_g1 = (g1 == 0) ? x : gcd(g1, x);
                    new_dp[new_g1][g2] = (new_dp[new_g1][g2] + dp[g1][g2]) % MOD;
                    
                    // Add to second subsequence
                    int new_g2 = (g2 == 0) ? x : gcd(g2, x);
                    new_dp[g1][new_g2] = (new_dp[g1][new_g2] + dp[g1][g2]) % MOD;
                }
            }
            
            dp = move(new_dp);
        }
        
        // Sum dp[g][g] for g >= 1
        long long result = 0;
        for (int g = 1; g <= maxVal; g++) {
            result = (result + dp[g][g]) % MOD;
        }
        
        return result;
    }
};