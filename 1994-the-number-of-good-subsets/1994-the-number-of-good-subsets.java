class Solution {
    public int numberOfGoodSubsets(int[] nums) {
        int mod = 1_000_000_007;
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int[] freq = new int[31];
        for (int num : nums) freq[num]++;
        
        long[] dp = new long[1 << 10];
        dp[0] = 1;
        
        for (int num = 2; num <= 30; num++) {
            if (freq[num] == 0) continue;
            int mask = 0;
            boolean valid = true;
            int x = num;
            for (int i = 0; i < 10; i++) {
                int p = primes[i];
                if (x % p == 0) {
                    if (x % (p * p) == 0) {
                        valid = false;
                        break;
                    }
                    mask |= (1 << i);
                    x /= p;
                }
            }
            if (!valid) continue;
            
            for (int state = (1 << 10) - 1; state >= 0; state--) {
                if ((state & mask) == 0) {
                    dp[state | mask] = (dp[state | mask] + dp[state] * freq[num]) % mod;
                }
            }
        }
        
        long res = 0;
        for (int i = 1; i < (1 << 10); i++) {
            res = (res + dp[i]) % mod;
        }
        
        long ones = 1;
        for (int i = 0; i < freq[1]; i++) {
            ones = (ones * 2) % mod;
        }
        res = res * ones % mod;
        return (int) res;
    }
}