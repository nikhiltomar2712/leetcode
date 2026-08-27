class Solution {
    public int numRookCaptures(char[][] board) {
        // 1. Find the position of the rook
        int rookRow = -1, rookCol = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    rookRow = i;
                    rookCol = j;
                    break;
                }
            }
            if (rookRow != -1) break;
        }

        // 2. Four directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int count = 0;

        // 3. Check each direction
        for (int[] dir : directions) {
            int r = rookRow;
            int c = rookCol;

            while (true) {
                r += dir[0];
                c += dir[1];

                // Out of bounds
                if (r < 0 || r >= 8 || c < 0 || c >= 8) break;

                // Hit a bishop → blocked
                if (board[r][c] == 'B') break;

                // Hit a pawn → capture and stop this direction
                if (board[r][c] == 'p') {
                    count++;
                    break;
                }
                // Otherwise continue (empty square '.')
            }
        }

        return count;
    }
}