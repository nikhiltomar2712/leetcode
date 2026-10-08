class Solution {
    public int mostFrequent(int[] nums, int key) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == key) {
                count.merge(nums[i + 1], 1, Integer::sum);
            }
        }
        int max = 0, res = -1;
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                res = e.getKey();
            }
        }
        return res;
    }
}