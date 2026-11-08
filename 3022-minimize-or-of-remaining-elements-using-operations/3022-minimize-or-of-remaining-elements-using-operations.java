class Solution {
    public int minOrAfterOperations(int[] nums, int k) {
        int mask = 0;      // bits we are trying to clear
        int result = 0;    // bits we cannot clear (will be set in answer)
        
        for (int bit = 29; bit >= 0; bit--) {
            int testMask = mask | (1 << bit);
            int operationsNeeded = 0;
            int currentAnd = 0;
            
            for (int num : nums) {
                if (currentAnd == 0) {
                    // Start a new segment
                    currentAnd = testMask & num;
                } else {
                    // Continue ANDing within the current segment
                    currentAnd &= (testMask & num);
                }
                
                if (currentAnd != 0) {
                    // This element must be merged with the next one
                    operationsNeeded++;
                }
            }
            
            if (operationsNeeded <= k) {
                // We can clear this bit, keep it in the mask
                mask = testMask;
            } else {
                // Cannot clear this bit, it must be set in the result
                result |= (1 << bit);
            }
        }
        
        return result;
    }
}