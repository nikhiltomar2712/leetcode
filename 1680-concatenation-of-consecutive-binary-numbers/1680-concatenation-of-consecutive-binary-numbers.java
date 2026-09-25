class Solution {
    public int concatenatedBinary(int n) {
        final int MOD = 1_000_000_007;
        long ans = 0;
        
        for (int i = 1; i <= n; i++) {
            // bit length = 32 - leading zeros
            int bitLength = 32 - Integer.numberOfLeadingZeros(i);
            ans = ((ans << bitLength) | i) % MOD;
        }
        
        return (int) ans;
    }
}