import java.util.*;

class Solution {
    private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length, cols = heights[0].length;
        int lo = 0, hi = 1_000_000;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canReach(heights, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean canReach(int[][] heights, int maxEffort) {
        int rows = heights.length, cols = heights[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0});
        visited[0][0] = true;

        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            int r = curr[0], c = curr[1];

            if (r == rows - 1 && c == cols - 1) return true;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc]) {
                    if (Math.abs(heights[nr][nc] - heights[r][c]) <= maxEffort) {
                        visited[nr][nc] = true;
                        stack.push(new int[]{nr, nc});
                    }
                }
            }
        }

        return false;
    }
}