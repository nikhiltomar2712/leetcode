class Solution {
    int best = Integer.MAX_VALUE;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        for (int base : baseCosts) {
            dfs(toppingCosts, 0, base, target);
        }
        return best;
    }
    private void dfs(int[] toppingCosts, int i, int cost, int target) {
        if (Math.abs(cost - target) < Math.abs(best - target) || 
            (Math.abs(cost - target) == Math.abs(best - target) && cost < best)) {
            best = cost;
        }
        if (i >= toppingCosts.length || cost > target) return;
        dfs(toppingCosts, i + 1, cost, target);
        dfs(toppingCosts, i + 1, cost + toppingCosts[i], target);
        dfs(toppingCosts, i + 1, cost + 2 * toppingCosts[i], target);
    }
}