class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{entrance[0], entrance[1], 0});
        maze[entrance[0]][entrance[1]] = '+';
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], d = cur[2];
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || maze[nr][nc] == '+') continue;
                if (nr == 0 || nr == m - 1 || nc == 0 || nc == n - 1) return d + 1;
                maze[nr][nc] = '+';
                queue.offer(new int[]{nr, nc, d + 1});
            }
        }
        return -1;
    }
}