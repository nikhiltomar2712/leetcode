class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1); // empty prefix
        
        int sum = 0;
        int result = 0;
        
        for (int num : nums) {
            sum += num;
            // If there is a prefix with sum = (current sum - goal),
            // then the subarray between them sums to goal
            result += prefixCount.getOrDefault(sum - goal, 0);
            prefixCount.put(sum, prefixCount.getOrDefault(sum, 0) + 1);
        }
        
        return result;
    }
}