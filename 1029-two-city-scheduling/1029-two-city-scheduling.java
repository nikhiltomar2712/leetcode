class Solution {
    public int twoCitySchedCost(int[][] costs) {
        // Sort by the extra cost of going to A instead of B
        Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));

        int n = costs.length / 2;
        int total = 0;

        // First n people → City A
        // Last n people  → City B
        for (int i = 0; i < n; i++) {
            total += costs[i][0] + costs[i + n][1];
        }

        return total;
    }
}