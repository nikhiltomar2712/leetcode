class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][] memo;
    private int[] loc;
    private int n, finish;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        this.loc = locations;
        this.n = locations.length;
        this.finish = finish;
        this.memo = new int[n][fuel + 1];
        for (int[] row : memo) java.util.Arrays.fill(row, -1);
        return dfs(start, fuel);
    }

    private int dfs(int city, int fuel) {
        if (memo[city][fuel] != -1) return memo[city][fuel];

        long ways = (city == finish) ? 1 : 0; // ending the route here counts as one valid route

        for (int next = 0; next < n; next++) {
            if (next == city) continue;
            int cost = Math.abs(loc[city] - loc[next]);
            if (cost <= fuel) {
                ways += dfs(next, fuel - cost);
            }
        }

        memo[city][fuel] = (int) (ways % MOD);
        return memo[city][fuel];
    }
}