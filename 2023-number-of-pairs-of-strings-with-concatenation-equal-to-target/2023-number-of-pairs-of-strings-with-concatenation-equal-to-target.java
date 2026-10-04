class Solution {
    public int numOfPairs(String[] nums, String target) {
        int n = nums.length, count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && (nums[i] + nums[j]).equals(target)) {
                    count++;
                }
            }
        }
        return count;
    }
}