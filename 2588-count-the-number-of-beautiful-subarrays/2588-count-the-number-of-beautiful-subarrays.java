class Solution {
    public long beautifulSubarrays(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int mask = 0;
        long res = 0;
        for (int num : nums) {
            mask ^= num;
            int count = map.getOrDefault(mask, 0);
            res += count;
            map.put(mask, count + 1);
        }
        return res;
    }
}