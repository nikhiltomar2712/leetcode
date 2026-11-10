class Solution {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length;
        int m = changeIndices.length;
        
        // Quick check: need at least n seconds to mark n indices
        if (n > m) return -1;
        
        int low = 1, high = m;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canMarkAll(nums, changeIndices, mid)) {
                ans = mid;
                high = mid - 1;  // try earlier
            } else {
                low = mid + 1;   // need more time
            }
        }
        
        return ans;
    }
    
    private boolean canMarkAll(int[] nums, int[] changeIndices, int seconds) {
        int n = nums.length;
        int[] lastOccurrence = new int[n];
        Arrays.fill(lastOccurrence, -1);
        
        // Find the LAST occurrence of each index within [0, seconds-1]
        // (changeIndices is 1-indexed in problem, but we work with 0-indexed here)
        for (int i = 0; i < seconds; i++) {
            int idx = changeIndices[i] - 1;  // convert to 0-indexed
            lastOccurrence[idx] = i;
        }
        
        // Every index must appear at least once
        for (int i = 0; i < n; i++) {
            if (lastOccurrence[i] == -1) return false;
        }
        
        // Greedy check: track available operations for decrements
        long availableOps = 0;  // operations we can use to decrement values
        int marked = 0;
        
        for (int i = 0; i < seconds; i++) {
            int idx = changeIndices[i] - 1;
            
            if (i == lastOccurrence[idx]) {
                // This is the LAST chance to mark idx
                // Need nums[idx] decrements before this point
                if (nums[idx] > availableOps) return false;
                availableOps -= nums[idx];  // consume operations
                marked++;
            } else {
                // Not the last occurrence, so this second can be used to decrement
                availableOps++;
            }
        }
        
        return marked == n;
    }
}