class Solution {
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        char opp = color == 'W' ? 'B' : 'W';
        for (int[] d : dirs) {
            int r = rMove + d[0], c = cMove + d[1];
            int count = 0;
            while (r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] == opp) {
                r += d[0];
                c += d[1];
                count++;
            }
            if (count > 0 && r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] == color) {
                return true;
            }
        }
        return false;
    }
}