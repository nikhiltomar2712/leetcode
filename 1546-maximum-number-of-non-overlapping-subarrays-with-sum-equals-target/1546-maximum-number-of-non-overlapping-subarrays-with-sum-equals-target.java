class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            if (seen.contains(sum - target)) {
                count++;
                seen.clear();
                seen.add(0);
                sum = 0;
            } else {
                seen.add(sum);
            }
        }
        return count;
    }
}