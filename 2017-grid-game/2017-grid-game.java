class Solution {
    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long top = 0;
        for (int x : grid[0]) top += x;
        long bottom = 0, ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            top -= grid[0][i];
            ans = Math.min(ans, Math.max(top, bottom));
            bottom += grid[1][i];
        }
        return ans;
    }
}