#include <string>
#include <vector>
using namespace std;

class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size(), len = s3.size();
        if (m + n != len) return false;
        vector<bool> dp(n + 1, false);
        dp[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i == 0 && j == 0) continue;
                bool fromS1 = (i > 0) && (s1[i-1] == s3[i+j-1]) && dp[j];
                bool fromS2 = (j > 0) && (s2[j-1] == s3[i+j-1]) && dp[j-1];
                dp[j] = fromS1 || fromS2;
            }
        }
        return dp[n];
    }
};