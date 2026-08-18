class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int[] result = new int[nums.length];
        int index = 0;
        
        // First place all even numbers
        for (int num : nums) {
            if (num % 2 == 0) {
                result[index++] = num;
            }
        }
        
        // Then place all odd numbers
        for (int num : nums) {
            if (num % 2 == 1) {
                result[index++] = num;
            }
        }
        
        return result;
    }
}