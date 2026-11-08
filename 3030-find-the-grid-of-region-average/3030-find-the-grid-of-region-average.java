class Solution {
    public int[][] resultGrid(int[][] image, int threshold) {
        int m = image.length, n = image[0].length;
        int[][] sum = new int[m][n];
        int[][] count = new int[m][n];
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                boolean ok = true;
                for (int r = i - 1; r <= i + 1 && ok; r++) {
                    for (int c = j - 1; c <= j + 1; c++) {
                        if (r > i - 1 && Math.abs(image[r][c] - image[r - 1][c]) > threshold) { ok = false; break; }
                        if (c > j - 1 && Math.abs(image[r][c] - image[r][c - 1]) > threshold) { ok = false; break; }
                    }
                }
                if (ok) {
                    int total = 0;
                    for (int r = i - 1; r <= i + 1; r++) {
                        for (int c = j - 1; c <= j + 1; c++) {
                            total += image[r][c];
                        }
                    }
                    int avg = total / 9;
                    for (int r = i - 1; r <= i + 1; r++) {
                        for (int c = j - 1; c <= j + 1; c++) {
                            sum[r][c] += avg;
                            count[r][c]++;
                        }
                    }
                }
            }
        }
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = count[i][j] > 0 ? sum[i][j] / count[i][j] : image[i][j];
            }
        }
        return res;
    }
}