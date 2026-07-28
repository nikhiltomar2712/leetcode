class Solution {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();

        // Collect all trees: {height, row, col}
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{forest.get(i).get(j), i, j});
                }
            }
        }

        // Sort by height (ascending)
        trees.sort(Comparator.comparingInt(a -> a[0]));

        int totalSteps = 0;
        int sr = 0, sc = 0;          // start position

        for (int[] tree : trees) {
            int steps = bfs(forest, sr, sc, tree[1], tree[2]);
            if (steps == -1) return -1;
            totalSteps += steps;
            sr = tree[1];
            sc = tree[2];
        }
        return totalSteps;
    }

    // Standard BFS to find shortest path from (sr,sc) to (tr,tc)
    private int bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        if (sr == tr && sc == tc) return 0;

        int m = forest.size();
        int n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{sr, sc, 0});   // row, col, steps
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], steps = cur[2];

            for (int[] d : DIRS) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (visited[nr][nc] || forest.get(nr).get(nc) == 0) continue;

                if (nr == tr && nc == tc) return steps + 1;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, steps + 1});
            }
        }
        return -1;   // unreachable
    }
}