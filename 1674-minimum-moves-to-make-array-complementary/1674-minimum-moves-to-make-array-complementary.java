class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[2 * limit + 2];
        
        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);
            
            // Default: 2 moves for all possible sums [2, 2*limit]
            diff[2] += 2;
            diff[2 * limit + 1] -= 2;
            
            // Range for 1 move: [a+1, b+limit]
            diff[a + 1] -= 1;
            diff[b + limit + 1] += 1;
            
            // Exact sum a+b: 0 moves
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
        }
        
        int minMoves = n; // Maximum possible moves
        int currentMoves = 0;
        
        // Sweep through all possible target sums
        for (int target = 2; target <= 2 * limit; target++) {
            currentMoves += diff[target];
            minMoves = Math.min(minMoves, currentMoves);
        }
        
        return minMoves;
    }
}