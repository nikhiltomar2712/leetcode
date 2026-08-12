import java.util.TreeSet;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        TreeSet<Integer> sums = new TreeSet<>();   // keeps values sorted ascending

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Maximum possible half-side length centered at (i, j)
                int maxK = Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j));

                for (int k = 0; k <= maxK; k++) {
                    int sum;
                    if (k == 0) {
                        sum = grid[i][j];          // single cell (area 0)
                    } else {
                        sum = getRhombusSum(grid, i, j, k);
                    }
                    sums.add(sum);
                    if (sums.size() > 3) {
                        sums.pollFirst();         // keep only the 3 largest
                    }
                }
            }
        }

        // Convert to descending array
        int[] result = new int[sums.size()];
        int idx = 0;
        for (int val : sums.descendingSet()) {
            result[idx++] = val;
        }
        return result;
    }

    // Compute border sum of rhombus centered at (i, j) with half-side k
    private int getRhombusSum(int[][] grid, int i, int j, int k) {
        int sum = 0;

        // Four vertices
        sum += grid[i - k][j];          // top
        sum += grid[i + k][j];          // bottom
        sum += grid[i][j - k];          // left
        sum += grid[i][j + k];          // right

        // Four sides (excluding vertices already added)
        for (int d = 1; d < k; d++) {
            sum += grid[i - k + d][j - d];   // upper-left
            sum += grid[i - k + d][j + d];   // upper-right
            sum += grid[i + k - d][j - d];   // lower-left
            sum += grid[i + k - d][j + d];   // lower-right
        }
        return sum;
    }
}