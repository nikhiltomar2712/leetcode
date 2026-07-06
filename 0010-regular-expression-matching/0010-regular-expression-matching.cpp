#include <string>
#include <vector>

class Solution {
public:
    bool isMatch(std::string s, std::string p) {
        int m = s.length();
        int n = p.length();
        
        // dp[i][j] = true if s[0..i-1] matches p[0..j-1]
        std::vector<std::vector<bool>> dp(m + 1, std::vector<bool>(n + 1, false));
        
        // Empty string matches empty pattern
        dp[0][0] = true;
        
        // Handle patterns that can match empty string, e.g., "a*", "a*b*", etc.
        for (int j = 1; j <= n; ++j) {
            if (p[j - 1] == '*') {
                // '*' means zero occurrences of the preceding element
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p[j - 1] == '*') {
                    // Case 1: '*' matches zero occurrences of the preceding character
                    dp[i][j] = dp[i][j - 2];
                    
                    // Case 2: '*' matches one or more occurrences
                    // Check if the preceding character matches s[i-1]
                    char prev = p[j - 2];
                    if (prev == '.' || prev == s[i - 1]) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    // Normal character or '.'
                    if (p[j - 1] == '.' || p[j - 1] == s[i - 1]) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
};