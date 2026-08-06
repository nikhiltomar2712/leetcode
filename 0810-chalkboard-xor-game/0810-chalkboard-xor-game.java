class Solution {
    public boolean xorGame(int[] nums) {
        // If the initial XOR is 0, Alice wins immediately
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        
        // Alice wins if: initial XOR is 0 OR the array length is even
        return xor == 0 || nums.length % 2 == 0;
    }
}