class Solution {
    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
                i++;
            }
        }
        return i;
    }
}