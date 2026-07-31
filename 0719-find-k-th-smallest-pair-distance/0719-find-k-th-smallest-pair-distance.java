class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        // Step 1: Sort the array to enable binary search and two-pointer counting
        Arrays.sort(nums);
        int n = nums.length;
        
        // Step 2: Define the search space for distances
        // Minimum possible distance is 0, maximum is difference between largest and smallest
        int low = 0;
        int high = nums[n - 1] - nums[0];
        
        // Step 3: Binary search to find the k-th smallest distance
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            // Count pairs with distance <= mid
            int count = countPairsWithMaxDistance(nums, mid);
            
            if (count >= k) {
                // If there are at least k pairs with distance <= mid, 
                // the answer is mid or smaller
                high = mid;
            } else {
                // Otherwise, answer must be larger than mid
                low = mid + 1;
            }
        }
        
        return low;
    }
    
    // Helper method to count pairs with distance <= maxDist using two pointers
    private int countPairsWithMaxDistance(int[] nums, int maxDist) {
        int count = 0;
        int left = 0;
        int n = nums.length;
        
        // For each right pointer, find the smallest left such that 
        // nums[right] - nums[left] <= maxDist
        for (int right = 0; right < n; right++) {
            // Move left pointer to satisfy the distance constraint
            while (nums[right] - nums[left] > maxDist) {
                left++;
            }
            // All pairs from left to right-1 have distance <= maxDist
            count += right - left;
        }
        
        return count;
    }
}