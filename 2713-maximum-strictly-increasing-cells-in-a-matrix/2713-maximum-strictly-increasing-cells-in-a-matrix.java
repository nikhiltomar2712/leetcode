class Solution {
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m][n];
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(mat[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int res = 0;
        for (List<int[]> cells : map.values()) {
            List<int[]> updates = new ArrayList<>();
            for (int[] cell : cells) {
                int r = cell[0], c = cell[1];
                int val = Math.max(rowMax[r], colMax[c]) + 1;
                dp[r][c] = val;
                updates.add(new int[]{r, c, val});
                res = Math.max(res, val);
            }
            for (int[] update : updates) {
                int r = update[0], c = update[1], val = update[2];
                rowMax[r] = Math.max(rowMax[r], val);
                colMax[c] = Math.max(colMax[c], val);
            }
        }
        return res;
    }
}