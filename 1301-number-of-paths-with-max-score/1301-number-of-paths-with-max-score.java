class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int MOD = 1_000_000_007;
        int n = board.size();
        int[][] score = new int[n][n];
        int[][] ways  = new int[n][n];
        for (int[] row : score) Arrays.fill(row, -1);

        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1]  = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char c = board.get(i).charAt(j);
                if (c == 'X' || (i == n - 1 && j == n - 1)) continue;

                int best = -1;
                long cnt = 0;

                // predecessors: down (i+1,j), right (i,j+1), down-right (i+1,j+1)
                int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
                for (int[] d : dirs) {
                    int ni = i + d[0], nj = j + d[1];
                    if (ni >= n || nj >= n || score[ni][nj] == -1) continue;
                    if (score[ni][nj] > best) {
                        best = score[ni][nj];
                        cnt  = ways[ni][nj];
                    } else if (score[ni][nj] == best) {
                        cnt = (cnt + ways[ni][nj]) % MOD;
                    }
                }

                if (best == -1) continue; // unreachable

                int val = (c == 'S' || c == 'E') ? 0 : (c - '0');
                score[i][j] = best + val;
                ways[i][j]  = (int) cnt;
            }
        }

        if (score[0][0] == -1) return new int[]{0, 0};
        return new int[]{score[0][0], ways[0][0]};
    }
}