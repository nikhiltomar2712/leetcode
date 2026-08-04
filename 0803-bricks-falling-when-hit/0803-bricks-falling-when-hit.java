class Solution {
    private int m, n;
    private int[] parent;
    private int[] size;
    private static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        m = grid.length;
        n = grid[0].length;
        int total = m * n;
        parent = new int[total + 1];   // extra node = ceiling
        size   = new int[total + 1];
        for (int i = 0; i <= total; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // 1. Copy grid and erase all hit positions
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            copy[i] = grid[i].clone();
        }
        for (int[] hit : hits) {
            copy[hit[0]][hit[1]] = 0;
        }

        // 2. Union remaining bricks
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 1) {
                    unionNeighbors(copy, i, j);
                }
            }
        }

        // 3. Process hits in reverse
        int[] result = new int[hits.length];
        for (int k = hits.length - 1; k >= 0; k--) {
            int r = hits[k][0], c = hits[k][1];
            if (grid[r][c] == 0) {          // no brick originally
                result[k] = 0;
                continue;
            }

            int before = size[find(total)]; // size connected to ceiling
            copy[r][c] = 1;
            unionNeighbors(copy, r, c);

            int after = size[find(total)];
            // bricks that fell = (new stable bricks) - 1 (the hit brick itself)
            result[k] = Math.max(0, after - before - 1);
        }
        return result;
    }

    private void unionNeighbors(int[][] g, int i, int j) {
        int id = i * n + j;
        if (i == 0) union(id, m * n);       // connect to ceiling

        for (int[] d : DIRS) {
            int ni = i + d[0], nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && g[ni][nj] == 1) {
                union(id, ni * n + nj);
            }
        }
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    private void union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return;
        // union by size
        if (size[px] < size[py]) {
            parent[px] = py;
            size[py] += size[px];
        } else {
            parent[py] = px;
            size[px] += size[py];
        }
    }
}