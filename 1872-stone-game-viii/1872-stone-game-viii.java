class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        // Compute prefix sums in-place
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // f represents the maximum score difference the current player can achieve
        // starting from a state where the first (i) stones have already been merged
        int f = stones[n - 1];  // base case: only one move left → take everything
        
        // Iterate backwards from n-2 down to 1
        for (int i = n - 2; i > 0; i--) {
            // Current player can either:
            // 1. Skip this prefix (same as f from i+1), or
            // 2. Take prefix sum stones[i] and leave the opponent with -f
            f = Math.max(f, stones[i] - f);
        }
        
        return f;  // Alice starts from index 1 (must take at least 2 stones)
    }
}