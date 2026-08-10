class Solution {
    public int consecutiveNumbersSum(int n) {
        int count = 0;
        int twoN = 2 * n;
        
        // k is the number of consecutive integers
        // The maximum k occurs when sequence starts from 1: k*(k+1)/2 <= n
        int maxK = (int)(Math.sqrt(2 * n)) + 1;
        
        for (int k = 1; k <= maxK; k++) {
            if (twoN % k == 0) {
                int numerator = twoN / k - k + 1;
                if (numerator > 0 && numerator % 2 == 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
}