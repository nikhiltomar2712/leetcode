/**
 * @param {character[][]} board
 * @return {void} Do not return anything, modify board in-place instead.
 */
var solve = function(board) {
    const m = board.length;
    const n = board[0].length;
    
    // Mark border-connected 'O's
    const markBorderConnected = (i, j) => {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] !== 'O') {
            return;
        }
        
        board[i][j] = '#';
        
        markBorderConnected(i - 1, j);
        markBorderConnected(i + 1, j);
        markBorderConnected(i, j - 1);
        markBorderConnected(i, j + 1);
    };
    
    // Check borders
    for (let i = 0; i < m; i++) {
        markBorderConnected(i, 0);
        markBorderConnected(i, n - 1);
    }
    
    for (let j = 0; j < n; j++) {
        markBorderConnected(0, j);
        markBorderConnected(m - 1, j);
    }
    
    // Final conversion
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (board[i][j] === 'O') {
                board[i][j] = 'X';
            } else if (board[i][j] === '#') {
                board[i][j] = 'O';
            }
        }
    }
};