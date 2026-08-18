class Solution {
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        
        // Initial score without any clever splitting
        int ans = nums[n - 1] - nums[0];
        
        // Try every possible "split point"
        // Numbers on the left of the split go +k, numbers on the right go -k
        for (int i = 0; i < n - 1; i++) {
            int high = Math.max(nums[i] + k, nums[n - 1] - k);
            int low  = Math.min(nums[0] + k, nums[i + 1] - k);
            ans = Math.min(ans, high - low);
        }
        
        return ans;
    }
}