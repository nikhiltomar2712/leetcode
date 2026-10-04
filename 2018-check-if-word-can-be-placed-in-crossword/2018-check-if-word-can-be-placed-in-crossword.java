class Solution {
    public boolean placeWordInCrossword(char[][] board, String word) {
        int m = board.length, n = board[0].length, len = word.length();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canPlace(board, word, i, j, 0, 1) || canPlace(board, word, i, j, 0, -1) ||
                    canPlace(board, word, i, j, 1, 0) || canPlace(board, word, i, j, -1, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean canPlace(char[][] board, String word, int i, int j, int di, int dj) {
        int m = board.length, n = board[0].length, len = word.length();
        int ni = i - di, nj = j - dj;
        if (ni >= 0 && ni < m && nj >= 0 && nj < n && board[ni][nj] != '#') return false;
        for (int k = 0; k < len; k++) {
            int x = i + k * di, y = j + k * dj;
            if (x < 0 || x >= m || y < 0 || y >= n || (board[x][y] != ' ' && board[x][y] != word.charAt(k))) {
                return false;
            }
        }
        int x = i + len * di, y = j + len * dj;
        if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#') return false;
        return true;
    }
}