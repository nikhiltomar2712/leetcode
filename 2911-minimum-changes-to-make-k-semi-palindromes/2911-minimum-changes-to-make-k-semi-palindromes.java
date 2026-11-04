import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // factors[i] = all proper divisors d of i (1 <= d < i and i % d == 0)
        List<Integer>[] factors = getFactors(n);
        
        // cost[i][j] = min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minCost = len; // upper bound
                for (int d : factors[len]) {
                    minCost = Math.min(minCost, getCostForD(s, i, j, d));
                }
                cost[i][j] = minCost;
            }
        }
        
        // dp[i][j] = min changes to split s[i:] into j semi-palindromic parts
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) Arrays.fill(row, n);
        dp[n][0] = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {
                for (int l = i + 1; l < n; l++) {
                    dp[i][j] = Math.min(dp[i][j], dp[l + 1][j - 1] + cost[i][l]);
                }
            }
        }
        
        return dp[0][k];
    }
    
    // Precompute proper divisors for all lengths up to n
    private List<Integer>[] getFactors(int n) {
        List<Integer>[] factors = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            factors[i] = new ArrayList<>();
            factors[i].add(1); // 1 is always a proper divisor (except length 1, but k >= 1 ensures lengths >= 2)
        }
        for (int d = 2; d < n; d++) {
            for (int multiple = d * 2; multiple <= n; multiple += d) {
                factors[multiple].add(d);
            }
        }
        return factors;
    }
    
    // Cost to make s[i..j] semi-palindromic with pattern length d
    private int getCostForD(String s, int i, int j, int d) {
        int cost = 0;
        // For each residue class modulo d, compare mirrored characters
        for (int offset = 0; offset < d; offset++) {
            int left = i + offset;
            int right = j - d + 1 + offset;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    cost++;
                }
                left += d;
                right -= d;
            }
        }
        return cost;
    }
}