class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long oddCount = 0;  // Number of prefix sums that are odd
        long evenCount = 1; // Number of prefix sums that are even (empty prefix sum = 0 is even)
        long prefixSum = 0;
        long result = 0;

        for (int num : arr) {
            prefixSum += num;
            if (prefixSum % 2 == 0) {
                // Current prefix sum is even, pair with previous odd prefix sums
                result = (result + oddCount) % MOD;
                evenCount++;
            } else {
                // Current prefix sum is odd, pair with previous even prefix sums
                result = (result + evenCount) % MOD;
                oddCount++;
            }
        }

        return (int) result;
    }
}