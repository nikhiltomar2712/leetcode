class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] prev = new long[k];

        for (int num : nums) {
            int numMod = num % k;
            long[] curr = new long[k];

            curr[numMod]++;

            for (int r = 0; r < k; r++) {
                if (prev[r] > 0) {
                    int newR = (r * numMod) % k;
                    curr[newR] += prev[r];
                }
            }

            for (int r = 0; r < k; r++) {
                result[r] += curr[r];
            }

            prev = curr;
        }

        return result;
    }
}