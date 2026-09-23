class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        int total = 0;
        int groupSum = 0;
        int groupMax = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0 && colors.charAt(i) != colors.charAt(i - 1)) {
                // close previous group
                total += groupSum - groupMax;
                groupSum = 0;
                groupMax = 0;
            }
            groupSum += neededTime[i];
            groupMax = Math.max(groupMax, neededTime[i]);
        }
        total += groupSum - groupMax; // close last group

        return total;
    }
}