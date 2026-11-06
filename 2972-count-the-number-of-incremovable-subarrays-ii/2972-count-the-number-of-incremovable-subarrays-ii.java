class Solution {
    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        
        int i = 0;
        while (i + 1 < n && nums[i] < nums[i + 1]) i++;
        if (i == n - 1) return (long) n * (n + 1) / 2;
        
        int j = n - 1;
        while (j - 1 >= 0 && nums[j - 1] < nums[j]) j--;
        
        long ans = n - j + 1; 
        
        for (int p = 0; p <= i; p++) {
            while (j < n && nums[p] >= nums[j]) {
                j++;
            }
            ans += n - j + 1;
        }
        
        return ans;
    }
}