class Solution {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int left = 1, right = cells.length;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (canCross(row, col, cells, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean canCross(int row, int col, int[][] cells, int day) {
        boolean[][] water = new boolean[row][col];
        for (int i = 0; i < day; i++) {
            water[cells[i][0] - 1][cells[i][1] - 1] = true;
        }
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        for (int j = 0; j < col; j++) {
            if (!water[0][j]) {
                queue.offer(new int[]{0, j});
                visited[0][j] = true;
            }
        }
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];
            if (r == row - 1) return true;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col && !water[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}