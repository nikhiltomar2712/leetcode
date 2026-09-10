class Solution {
    public String tictactoe(int[][] moves) {
        char[][] board = new char[3][3];
        
        for (int i = 0; i < moves.length; i++) {
            int row = moves[i][0];
            int col = moves[i][1];
            char player = (i % 2 == 0) ? 'X' : 'O';  // A = X, B = O
            board[row][col] = player;
            
            if (isWinner(board, player)) {
                return player == 'X' ? "A" : "B";
            }
        }
        
        return moves.length == 9 ? "Draw" : "Pending";
    }
    
    private boolean isWinner(char[][] board, char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        
        return false;
    }
}