class Solution {
    public long maxStrength(int[] nums) {
        long res = 1;
        int negCount = 0;
        int maxNeg = Integer.MIN_VALUE;
        boolean hasPos = false;
        for (int num : nums) {
            if (num > 0) {
                res *= num;
                hasPos = true;
            } else if (num < 0) {
                negCount++;
                res *= num;
                maxNeg = Math.max(maxNeg, num);
            }
        }
        if (negCount % 2 != 0) {
            res /= maxNeg;
        }
        if (!hasPos && negCount <= 1) {
            int max = Integer.MIN_VALUE;
            for (int num : nums) max = Math.max(max, num);
            return max;
        }
        return res;
    }
}