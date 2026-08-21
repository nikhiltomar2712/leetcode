class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long left = 1;
        long right = (long) 1e11; // sufficient upper bound
        
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (count(mid, coins) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    // Count distinct amounts <= x using inclusion-exclusion
    private long count(long x, int[] coins) {
        long cnt = 0;
        int n = coins.length;
        
        // Enumerate all non-empty subsets
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;
            
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    lcm = lcm(lcm, coins[i]);
                    if (lcm > x) break; // overflow / too large
                }
            }
            
            if (lcm > x) continue;
            
            // Inclusion-exclusion: + for odd size, - for even size
            if (bits % 2 == 1) {
                cnt += x / lcm;
            } else {
                cnt -= x / lcm;
            }
        }
        return cnt;
    }
    
    private long lcm(long a, long b) {
        return a / gcd(a, b) * b; // avoid overflow
    }
    
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}