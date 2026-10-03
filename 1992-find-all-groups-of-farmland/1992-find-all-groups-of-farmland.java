class Solution {
    public int[][] findFarmland(int[][] land) {
        int m = land.length, n = land[0].length;
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    int r2 = i, c2 = j;
                    while (r2 + 1 < m && land[r2 + 1][j] == 1) r2++;
                    while (c2 + 1 < n && land[i][c2 + 1] == 1) c2++;
                    for (int x = i; x <= r2; x++) {
                        for (int y = j; y <= c2; y++) {
                            land[x][y] = 0;
                        }
                    }
                    res.add(new int[]{i, j, r2, c2});
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}