/**
 * @param {number} n
 * @return {number}
 */
var getMoneyAmount = function(n) {
    // dp[i][j] represents the minimum money needed to guarantee a win for range [i, j]
    const dp = Array.from({ length: n + 2 }, () => Array(n + 2).fill(0));
    
    // Iterate over range lengths from 2 to n
    for (let len = 2; len <= n; len++) {
        for (let i = 1; i + len - 1 <= n; i++) {
            const j = i + len - 1;
            // Initialize with a large number
            dp[i][j] = Infinity;
            
            // Try every possible guess x in range [i, j]
            for (let x = i; x <= j; x++) {
                // Cost = x (for wrong guess) + max(cost for left range, cost for right range)
                // The max is because we need to guarantee a win regardless of which side the answer is on
                const cost = x + Math.max(
                    x > i ? dp[i][x - 1] : 0,
                    x < j ? dp[x + 1][j] : 0
                );
                // Take the minimum cost among all possible guesses
                dp[i][j] = Math.min(dp[i][j], cost);
            }
        }
    }
    
    return dp[1][n];
};