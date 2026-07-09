#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        // dp[j] stores the minimum path sum to reach the current cell (i, j)
        // from the bottom row to the current row. Initialize with the last row.
        vector<int> dp(triangle.back());
        
        // Process rows from bottom-1 up to the top
        for (int i = n - 2; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                // For cell (i, j), the best from below is min(dp[j], dp[j+1]) + triangle[i][j]
                dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j];
            }
        }
        
        return dp[0];
    }
};