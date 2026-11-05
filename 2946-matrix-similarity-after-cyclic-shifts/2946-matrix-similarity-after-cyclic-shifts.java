class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        k %= n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int shifted;
                if (i % 2 == 0) {
                    shifted = mat[i][(j + k) % n];
                } else {
                    shifted = mat[i][(j - k + n) % n];
                }
                if (shifted != mat[i][j]) return false;
            }
        }
        return true;
    }
}