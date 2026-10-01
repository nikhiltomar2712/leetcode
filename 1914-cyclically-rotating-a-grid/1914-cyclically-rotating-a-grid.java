class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int layers = Math.min(m, n) / 2;
        for (int layer = 0; layer < layers; layer++) {
            List<Integer> vals = new ArrayList<>();
            int top = layer, left = layer, bottom = m - 1 - layer, right = n - 1 - layer;
            for (int j = left; j <= right; j++) vals.add(grid[top][j]);
            for (int i = top + 1; i <= bottom; i++) vals.add(grid[i][right]);
            for (int j = right - 1; j >= left; j--) vals.add(grid[bottom][j]);
            for (int i = bottom - 1; i > top; i--) vals.add(grid[i][left]);
            int size = vals.size();
            int rot = k % size;
            List<Integer> rotated = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                rotated.add(vals.get((i + rot) % size));
            }
            int idx = 0;
            for (int j = left; j <= right; j++) grid[top][j] = rotated.get(idx++);
            for (int i = top + 1; i <= bottom; i++) grid[i][right] = rotated.get(idx++);
            for (int j = right - 1; j >= left; j--) grid[bottom][j] = rotated.get(idx++);
            for (int i = bottom - 1; i > top; i--) grid[i][left] = rotated.get(idx++);
        }
        return grid;
    }
}