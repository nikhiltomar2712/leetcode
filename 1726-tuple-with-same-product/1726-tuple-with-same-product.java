class Solution {
    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int product = nums[i] * nums[j];
                map.merge(product, 1, Integer::sum);
            }
        }

        int count = 0;
        for (int freq : map.values()) {
            count += freq * (freq - 1) / 2 * 8;
        }

        return count;
    }
}