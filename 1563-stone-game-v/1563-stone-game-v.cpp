class Solution {
public:
    int stoneGameV(vector<int>& stoneValue) {
        int n = stoneValue.size();
        vector<int> prefix(n + 1, 0);
        for (int i = 0; i < n; ++i)
            prefix[i + 1] = prefix[i] + stoneValue[i];

        // dp[i][j] = max score obtainable from subarray i..j
        vector<vector<int>> dp(n, vector<int>(n, 0));

        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i + len - 1 < n; ++i) {
                int j = i + len - 1;
                int best = 0;

                for (int k = i; k < j; ++k) {
                    int leftSum = prefix[k + 1] - prefix[i];
                    int rightSum = prefix[j + 1] - prefix[k + 1];

                    if (leftSum < rightSum) {
                        best = max(best, leftSum + dp[i][k]);
                    } else if (leftSum > rightSum) {
                        best = max(best, rightSum + dp[k + 1][j]);
                    } else {
                        best = max(best, leftSum + max(dp[i][k], dp[k + 1][j]));
                    }
                }
                dp[i][j] = best;
            }
        }

        return dp[0][n - 1];
    }
};