class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);          // empty prefix has sum 0

        int prefix = 0;
        int ans = 0;

        for (int num : nums) {
            // keep the remainder in the range [0, k-1]
            prefix = ((prefix + num) % k + k) % k;

            // every previous prefix with the same remainder
            // forms a valid subarray with the current one
            ans += count.getOrDefault(prefix, 0);

            count.put(prefix, count.getOrDefault(prefix, 0) + 1);
        }

        return ans;
    }
}