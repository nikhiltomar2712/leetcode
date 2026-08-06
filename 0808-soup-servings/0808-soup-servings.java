class Solution {
    public double soupServings(int n) {
        // If n is large enough, probability approaches 1
        if (n > 4800) {
            return 1.0;
        }
        
        // Use units of 25ml to reduce state space
        int m = (int) Math.ceil(n / 25.0);
        Double[][] memo = new Double[m + 1][m + 1];
        return dfs(m, m, memo);
    }
    
    private double dfs(int a, int b, Double[][] memo) {
        // Base cases
        if (a <= 0 && b <= 0) {
            return 0.5; // Both empty simultaneously
        }
        if (a <= 0) {
            return 1.0; // Soup A empty first
        }
        if (b <= 0) {
            return 0.0; // Soup B empty first
        }
        
        if (memo[a][b] != null) {
            return memo[a][b];
        }
        
        // Four operations, each with 0.25 probability
        double prob = 0.0;
        prob += 0.25 * dfs(Math.max(0, a - 4), b, memo);     // Serve 100ml A
        prob += 0.25 * dfs(Math.max(0, a - 3), Math.max(0, b - 1), memo); // Serve 75ml A, 25ml B
        prob += 0.25 * dfs(Math.max(0, a - 2), Math.max(0, b - 2), memo); // Serve 50ml A, 50ml B
        prob += 0.25 * dfs(Math.max(0, a - 1), Math.max(0, b - 3), memo); // Serve 25ml A, 75ml B
        
        memo[a][b] = prob;
        return prob;
    }
}