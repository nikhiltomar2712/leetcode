#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int n = nums.size();
        if (n < 3) return 0;
        
        vector<unordered_map<long long, int>> dp(n); // dp[i][diff] = number of arithmetic subsequences ending at i with difference 'diff'
        int result = 0;
        
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long long diff = (long long)nums[i] - nums[j]; // Use long long to prevent overflow
                
                dp[i][diff] += dp[j].count(diff) ? dp[j][diff] + 1 : 1;
                
                // Every new sequence of length >= 3 contributes
                if (dp[j].count(diff)) {
                    result += dp[j][diff];
                }
            }
        }
        
        return result;
    }
};