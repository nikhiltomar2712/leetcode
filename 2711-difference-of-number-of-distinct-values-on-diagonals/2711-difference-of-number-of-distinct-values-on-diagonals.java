class Solution {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Set<Integer> tl = new HashSet<>();
                int r = i - 1, c = j - 1;
                while (r >= 0 && c >= 0) {
                    tl.add(grid[r][c]);
                    r--;
                    c--;
                }
                Set<Integer> br = new HashSet<>();
                r = i + 1;
                c = j + 1;
                while (r < m && c < n) {
                    br.add(grid[r][c]);
                    r++;
                    c++;
                }
                res[i][j] = Math.abs(tl.size() - br.size());
            }
        }
        return res;
    }
}