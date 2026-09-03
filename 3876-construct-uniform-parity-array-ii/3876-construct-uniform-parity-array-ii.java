class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        boolean hasOdd = false;
        
        for (int x : nums1) {
            minVal = Math.min(minVal, x);
            if (x % 2 == 1) {
                hasOdd = true;
            }
        }
        
        // All even → already uniform
        if (!hasOdd) {
            return true;
        }
        
        // Mixed: possible to make all odd only if the overall minimum is odd
        // (every even can then subtract the min odd to become odd)
        // Impossible to make all even (the smallest odd has nothing smaller to subtract)
        return minVal % 2 == 1;
    }
}