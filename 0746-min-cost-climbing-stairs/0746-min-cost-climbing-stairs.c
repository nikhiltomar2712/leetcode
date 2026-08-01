int minCostClimbingStairs(int* cost, int costSize) {
    // dp[i] = minimum cost to reach step i
    // We can reach the top (index costSize) from costSize-1 or costSize-2
    int prev2 = 0;  // cost to reach step i-2
    int prev1 = 0;  // cost to reach step i-1
    
    for (int i = 2; i <= costSize; i++) {
        int curr = (prev1 + cost[i - 1]) < (prev2 + cost[i - 2]) 
                   ? (prev1 + cost[i - 1]) 
                   : (prev2 + cost[i - 2]);
        prev2 = prev1;
        prev1 = curr;
    }
    
    return prev1;
}