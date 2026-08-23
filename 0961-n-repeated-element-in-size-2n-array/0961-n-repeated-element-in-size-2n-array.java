class Solution {
    public int repeatedNTimes(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return num;          // already present → this is the repeated one
            }
        }
        return -1;                   // never reached
    }
}