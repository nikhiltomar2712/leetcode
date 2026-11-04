class Solution {
    public int minGroupsForValidAssignment(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }
        int minFreq = Integer.MAX_VALUE;
        for (int c : count.values()) {
            minFreq = Math.min(minFreq, c);
        }
        for (int size = minFreq; size >= 1; size--) {
            int res = 0;
            boolean valid = true;
            for (int c : count.values()) {
                if (c / size < c % size) {
                    valid = false;
                    break;
                }
                res += (c + size) / (size + 1);
            }
            if (valid) return res;
        }
        return -1;
    }
}