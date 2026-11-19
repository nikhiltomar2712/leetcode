import java.math.BigInteger;
import java.util.Arrays;

class Solution {
    public int maxTotalReward(int[] rewardValues) {
        // Remove duplicates and sort
        int[] nums = Arrays.stream(rewardValues)
                           .distinct()
                           .sorted()
                           .toArray();
        
        // f is a bitset where the i-th bit is set if sum i is achievable
        BigInteger f = BigInteger.ONE;  // initially 0 is achievable
        
        for (int v : nums) {
            // Keep only bits representing sums < v
            BigInteger mask = BigInteger.ONE.shiftLeft(v).subtract(BigInteger.ONE);
            BigInteger bitsLessThanV = f.and(mask);
            
            // Add v to all those sums
            BigInteger shifted = bitsLessThanV.shiftLeft(v);
            
            // Merge the new achievable sums
            f = f.or(shifted);
        }
        
        // The highest set bit position is the maximum achievable sum
        return f.bitLength() - 1;
    }
}