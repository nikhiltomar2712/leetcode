class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int odd = 0, even = 1;   // empty prefix is even
        int prefix = 0;
        int result = 0;

        for (int x : arr) {
            prefix += x;
            if (prefix % 2 == 0) {
                result = (result + odd) % MOD;
                even++;
            } else {
                result = (result + even) % MOD;
                odd++;
            }
        }

        return result;
    }
}