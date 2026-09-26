class Solution {
    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] result = new int[n];

        for (int startCol = 0; startCol < n; startCol++) {
            int col = startCol;
            boolean stuck = false;

            for (int row = 0; row < m; row++) {
                int dir = grid[row][col];
                int nextCol = col + dir;

                if (nextCol < 0 || nextCol >= n || grid[row][nextCol] != dir) {
                    stuck = true;
                    break;
                }
                col = nextCol;
            }

            result[startCol] = stuck ? -1 : col;
        }

        return result;
    }
}