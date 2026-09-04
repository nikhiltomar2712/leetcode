class Solution {
    public int movesToMakeZigzag(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        
        // Case 1: Even indices are peaks (A[0] > A[1] < A[2] > A[3] < ...)
        int movesEven = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) { // Even index should be greater than neighbors
                int minNeighbor = Integer.MAX_VALUE;
                if (i - 1 >= 0) minNeighbor = Math.min(minNeighbor, nums[i - 1]);
                if (i + 1 < n) minNeighbor = Math.min(minNeighbor, nums[i + 1]);
                
                if (nums[i] >= minNeighbor) {
                    movesEven += nums[i] - minNeighbor + 1;
                }
            }
        }
        
        // Case 2: Odd indices are peaks (A[0] < A[1] > A[2] < A[3] > ...)
        int movesOdd = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 1) { // Odd index should be greater than neighbors
                int minNeighbor = Integer.MAX_VALUE;
                if (i - 1 >= 0) minNeighbor = Math.min(minNeighbor, nums[i - 1]);
                if (i + 1 < n) minNeighbor = Math.min(minNeighbor, nums[i + 1]);
                
                if (nums[i] >= minNeighbor) {
                    movesOdd += nums[i] - minNeighbor + 1;
                }
            }
        }
        
        return Math.min(movesEven, movesOdd);
    }
}