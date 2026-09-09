class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);          // empty prefix

        int prefix = 0;
        int ans = 0;

        for (int num : nums) {
            prefix += (num & 1); // +1 if odd, +0 if even
            ans += freq.getOrDefault(prefix - k, 0);
            freq.put(prefix, freq.getOrDefault(prefix, 0) + 1);
        }
        return ans;
    }
}