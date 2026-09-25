class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;
        
        // Step 1: Find the pivot - the first index from the right
        // where nums[i] < nums[i + 1]
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        if (i >= 0) {
            // Step 2: Find the smallest element to the right of pivot
            // that is greater than nums[i], then swap
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        
        // Step 3: Reverse the suffix starting at i + 1
        reverse(nums, i + 1, n - 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}