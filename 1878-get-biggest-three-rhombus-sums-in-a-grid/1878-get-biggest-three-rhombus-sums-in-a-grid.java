class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int maxR = Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j));
                for (int r = 0; r <= maxR; r++) {
                    int sum = 0;
                    if (r == 0) {
                        sum = grid[i][j];
                    } else {
                        int x = i - r, y = j;
                        for (int k = 0; k < r; k++) sum += grid[x + k][y + k];
                        for (int k = 0; k < r; k++) sum += grid[x + r + k][y + r - k];
                        for (int k = 0; k < r; k++) sum += grid[x + 2 * r - k][y - k];
                        for (int k = 0; k < r; k++) sum += grid[x + r - k][y - r + k];
                    }
                    set.add(sum);
                }
            }
        }
        int size = Math.min(3, set.size());
        int[] res = new int[size];
        int idx = 0;
        for (int val : set) {
            if (idx == size) break;
            res[idx++] = val;
        }
        return res;
    }
}