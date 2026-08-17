class Solution {
    public int sumSubseqWidths(int[] nums) {
        int MOD = 1_000_000_007;
        int n = nums.length;
        
        // Sort the array to easily determine min and max for subsequences
        Arrays.sort(nums);
        
        // Precompute powers of 2 modulo MOD
        long[] pow2 = new long[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }
        
        long result = 0;
        
        // For each element nums[i], it acts as max in 2^i subsequences
        // and as min in 2^(n-1-i) subsequences
        for (int i = 0; i < n; i++) {
            // Contribution as max: nums[i] * 2^i
            long maxContrib = (nums[i] * pow2[i]) % MOD;
            // Contribution as min: nums[i] * 2^(n-1-i)
            long minContrib = (nums[i] * pow2[n - 1 - i]) % MOD;
            
            // Add as max, subtract as min
            result = (result + maxContrib - minContrib) % MOD;
        }
        
        // Ensure result is positive
        if (result < 0) {
            result += MOD;
        }
        
        return (int) result;
    }
}