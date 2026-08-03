class Solution {
    public boolean validTicTacToe(String[] board) {
        int xCount = count(board, 'X');
        int oCount = count(board, 'O');

        // X always goes first → X can have at most one more mark than O
        if (xCount != oCount && xCount != oCount + 1) {
            return false;
        }

        boolean xWin = win(board, 'X');
        boolean oWin = win(board, 'O');

        // Both cannot win
        if (xWin && oWin) {
            return false;
        }

        // If X wins, X must have one more move
        if (xWin && xCount != oCount + 1) {
            return false;
        }

        // If O wins, counts must be equal
        if (oWin && xCount != oCount) {
            return false;
        }

        return true;
    }

    private int count(String[] board, char c) {
        int cnt = 0;
        for (String row : board) {
            for (char ch : row.toCharArray()) {
                if (ch == c) cnt++;
            }
        }
        return cnt;
    }

    private boolean win(String[] board, char c) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c) {
                return true;
            }
            if (board[0].charAt(i) == c && board[1].charAt(i) == c && board[2].charAt(i) == c) {
                return true;
            }
        }
        // Check diagonals
        if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) {
            return true;
        }
        if (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c) {
            return true;
        }
        return false;
    }
}