class Solution {
    public long numberOfWeeks(int[] milestones) {
        long sum = 0;
        int max = 0;
        for (int m : milestones) {
            sum += m;
            max = Math.max(max, m);
        }
        long rest = sum - max;
        if (max > rest + 1) {
            return rest * 2 + 1;
        }
        return sum;
    }
}