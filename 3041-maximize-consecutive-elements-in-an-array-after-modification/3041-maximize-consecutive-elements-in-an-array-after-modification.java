class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int maxSelected = 0;
        
        for (int num : nums) {
            // Option 1: increase num by 1, chain ends at num + 1
            dp.put(num + 1, dp.getOrDefault(num, 0) + 1);
            
            // Option 2: keep num as is, chain ends at num
            dp.put(num, dp.getOrDefault(num - 1, 0) + 1);
            
            maxSelected = Math.max(maxSelected, Math.max(dp.get(num), dp.get(num + 1)));
        }
        
        return maxSelected;
    }
}