class Solution {
    public int getMaxLen(int[] nums) {
        int pos = 0;   // longest subarray ending here with positive product
        int neg = 0;   // longest subarray ending here with negative product
        int maxLen = 0;

        for (int num : nums) {
            if (num > 0) {
                pos = pos + 1;
                neg = (neg > 0) ? neg + 1 : 0;
            } else if (num < 0) {
                int newPos = (neg > 0) ? neg + 1 : 0;
                int newNeg = pos + 1;
                pos = newPos;
                neg = newNeg;
            } else {
                pos = 0;
                neg = 0;
            }
            maxLen = Math.max(maxLen, pos);
        }

        return maxLen;
    }
}