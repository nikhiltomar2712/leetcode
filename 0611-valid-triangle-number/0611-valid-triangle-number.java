class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        
        for (int k = 2; k < n; k++) {
            int left = 0;
            int right = k - 1;
            
            while (left < right) {
                if (nums[left] + nums[right] > nums[k]) {
                    // All between left and right will also satisfy with right
                    count += (right - left);
                    right--;
                } else {
                    left++;
                }
            }
        }
        
        return count;
    }
}