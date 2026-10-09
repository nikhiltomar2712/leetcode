class Solution {
    public int maximumMinutes(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] fire = new int[m][n];
        for (int[] row : fire) Arrays.fill(row, -1);
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fire[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : dirs) {
                int nr = cur[0] + d[0], nc = cur[1] + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && fire[nr][nc] == -1 && grid[nr][nc] != 2) {
                    fire[nr][nc] = fire[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        int left = 0, right = 1000000000, res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canEscape(grid, fire, mid, m, n, dirs)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private boolean canEscape(int[][] grid, int[][] fire, int wait, int m, int n, int[][] dirs) {
        if (grid[0][0] == 1) return false;
        int[][] time = new int[m][n];
        for (int[] row : time) Arrays.fill(row, -1);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        time[0][0] = wait;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int t = time[cur[0]][cur[1]];
            if (cur[0] == m - 1 && cur[1] == n - 1) return true;
            for (int[] d : dirs) {
                int nr = cur[0] + d[0], nc = cur[1] + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] == 2 || time[nr][nc] != -1) continue;
                int nt = t + 1;
                if (fire[nr][nc] != -1 && fire[nr][nc] <= nt && !(nr == m - 1 && nc == n - 1 && fire[nr][nc] == nt)) continue;
                if (nr == m - 1 && nc == n - 1 && fire[nr][nc] != -1 && fire[nr][nc] < nt) continue;
                time[nr][nc] = nt;
                queue.offer(new int[]{nr, nc});
            }
        }
        return false;
    }
}