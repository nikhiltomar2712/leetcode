class Solution {
    private int[] rows = new int[9];
    private int[] cols = new int[9];
    private int[] boxes = new int[9];
    private char[][] board;
    private boolean solved = false;
    
    public void solveSudoku(char[][] board) {
        this.board = board;
        
        // Initialize bitmasks with existing numbers
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int digit = board[r][c] - '1';
                    int mask = 1 << digit;
                    int boxIdx = (r / 3) * 3 + (c / 3);
                    rows[r] |= mask;
                    cols[c] |= mask;
                    boxes[boxIdx] |= mask;
                }
            }
        }
        
        backtrack(0, 0);
    }
    
    private void backtrack(int r, int c) {
        if (r == 9) {
            solved = true;
            return;
        }
        
        // Move to next cell
        int nextR = (c == 8) ? r + 1 : r;
        int nextC = (c == 8) ? 0 : c + 1;
        
        if (board[r][c] != '.') {
            backtrack(nextR, nextC);
            return;
        }
        
        int boxIdx = (r / 3) * 3 + (c / 3);
        int used = rows[r] | cols[c] | boxes[boxIdx];
        
        for (int digit = 0; digit < 9 && !solved; digit++) {
            int mask = 1 << digit;
            if ((used & mask) != 0) continue;  // Digit already used
            
            // Place digit
            board[r][c] = (char) ('1' + digit);
            rows[r] |= mask;
            cols[c] |= mask;
            boxes[boxIdx] |= mask;
            
            backtrack(nextR, nextC);
            
            // Backtrack
            if (!solved) {
                board[r][c] = '.';
                rows[r] &= ~mask;
                cols[c] &= ~mask;
                boxes[boxIdx] &= ~mask;
            }
        }
    }
}