class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int j = 1;  // pointer for odd indices
        
        for (int i = 0; i < nums.length; i += 2) {
            if (nums[i] % 2 == 1) {          // even index has odd number
                while (nums[j] % 2 == 1) {   // find next even number at odd index
                    j += 2;
                }
                // swap
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return nums;
    }
}