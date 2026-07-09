#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    int numDistinct(string s, string t) {
        int m = s.size(), n = t.size();
        // dp[j] = number of distinct subsequences of s[0..i-1] that equal t[0..j-1]
        vector<unsigned long long> dp(n + 1, 0);
        dp[0] = 1; // empty subsequence matches empty t

        for (int i = 1; i <= m; ++i) {
            // Traverse backwards to reuse the previous row's values
            for (int j = n; j >= 1; --j) {
                if (s[i - 1] == t[j - 1]) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return (int)dp[n];
    }
};