class Solution {
    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int i = 0;
        
        // Find the longest strictly increasing prefix [0, i]
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        
        // If the entire array is already strictly increasing
        if (i == n - 1) {
            return n * (n + 1) / 2; // Every subarray removal works
        }
        
        // Count removals that leave only a prefix (including empty prefix)
        int ans = i + 2;
        
        // Scan from right to find valid suffix starts
        for (int j = n - 1; j > 0; j--) {
            // Shrink prefix pointer until nums[i] < nums[j]
            while (i >= 0 && nums[i] >= nums[j]) {
                i--;
            }
            ans += i + 2;
            
            // If suffix is no longer strictly increasing, stop
            if (nums[j - 1] >= nums[j]) {
                break;
            }
        }
        
        return ans;
    }
}