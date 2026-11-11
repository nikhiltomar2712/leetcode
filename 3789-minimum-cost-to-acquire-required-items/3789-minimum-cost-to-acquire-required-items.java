class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Plan 1: Buy everything separately (Type 1 for need1, Type 2 for need2)
        long plan1 = (long) need1 * cost1 + (long) need2 * cost2;

        // Plan 2: Buy all Type 3 items up to the maximum requirement
        long maxNeed = Math.max(need1, need2);
        long plan2 = maxNeed * costBoth;

        // Plan 3: Use Type 3 for the overlapping part (min need), then fill the rest separately
        long minNeed = Math.min(need1, need2);
        long plan3 = (long) minNeed * costBoth 
                   + (long) (need1 - minNeed) * cost1 
                   + (long) (need2 - minNeed) * cost2;

        // Return the minimum of the three plans
        return Math.min(plan1, Math.min(plan2, plan3));
    }
}