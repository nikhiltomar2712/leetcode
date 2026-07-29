class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();

        int degree = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            count.put(num, count.getOrDefault(num, 0) + 1);
            degree = Math.max(degree, count.get(num));

            first.putIfAbsent(num, i);   // only record the first occurrence
            last.put(num, i);            // always update the last occurrence
        }

        int ans = nums.length;
        for (int num : count.keySet()) {
            if (count.get(num) == degree) {
                ans = Math.min(ans, last.get(num) - first.get(num) + 1);
            }
        }
        return ans;
    }
}