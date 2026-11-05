class Solution {
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] result = new int[n / 3][3];
        
        for (int i = 0; i < n; i += 3) {
            // Since sorted, nums[i] is smallest and nums[i+2] is largest in this triplet
            if (nums[i + 2] - nums[i] > k) {
                return new int[0][]; // Impossible
            }
            result[i / 3] = new int[]{nums[i], nums[i + 1], nums[i + 2]};
        }
        
        return result;
    }
}