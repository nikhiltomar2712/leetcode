class Solution {
    public int movesToChessboard(int[][] board) {
        int n = board.length;

        // 1. Check the “four-corner” property
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((board[0][0] ^ board[i][0] ^ board[0][j] ^ board[i][j]) == 1) {
                    return -1;
                }
            }
        }

        // 2. Count 1’s in first row / first column
        int rowSum = 0, colSum = 0;
        for (int i = 0; i < n; i++) {
            rowSum += board[0][i];
            colSum += board[i][0];
        }
        if (rowSum != n / 2 && rowSum != (n + 1) / 2) return -1;
        if (colSum != n / 2 && colSum != (n + 1) / 2) return -1;

        // 3. Count mismatches against the pattern 0 1 0 1 …
        int rowSwaps = 0, colSwaps = 0;
        for (int i = 0; i < n; i++) {
            if (board[i][0] == (i & 1)) rowSwaps++;
            if (board[0][i] == (i & 1)) colSwaps++;
        }

        // 4. Choose the better of the two possible alternating patterns
        if (n % 2 == 1) {                     // odd size – only one pattern works
            if (rowSwaps % 2 == 1) rowSwaps = n - rowSwaps;
            if (colSwaps % 2 == 1) colSwaps = n - colSwaps;
        } else {                              // even size – take the cheaper pattern
            rowSwaps = Math.min(rowSwaps, n - rowSwaps);
            colSwaps = Math.min(colSwaps, n - colSwaps);
        }

        // each swap fixes two mismatches
        return (rowSwaps + colSwaps) / 2;
    }
}