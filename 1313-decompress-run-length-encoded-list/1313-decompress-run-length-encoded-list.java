class Solution {
    public int[] decompressRLElist(int[] nums) {
        // First pass: compute total size of the output
        int totalSize = 0;
        for (int i = 0; i < nums.length; i += 2) {
            totalSize += nums[i];
        }
        
        int[] result = new int[totalSize];
        int index = 0;
        
        // Second pass: fill the result
        for (int i = 0; i < nums.length; i += 2) {
            int freq = nums[i];
            int val = nums[i + 1];
            for (int j = 0; j < freq; j++) {
                result[index++] = val;
            }
        }
        
        return result;
    }
}