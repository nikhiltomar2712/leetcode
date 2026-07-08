function updateBoard(board: string[][], click: number[]): string[][] {
    const [row, col] = click;
    const rows = board.length;
    const cols = board[0].length;
    
    const directions = [
        [-1, -1], [-1, 0], [-1, 1],
        [0, -1],           [0, 1],
        [1, -1],  [1, 0],  [1, 1]
    ];

    function countMines(r: number, c: number): number {
        let count = 0;
        for (const [dr, dc] of directions) {
            const nr = r + dr;
            const nc = c + dc;
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] === 'M') {
                count++;
            }
        }
        return count;
    }

    function dfs(r: number, c: number): void {
        if (r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] !== 'E') {
            return;
        }

        const mines = countMines(r, c);
        if (mines > 0) {
            board[r][c] = mines.toString();
        } else {
            board[r][c] = 'B';
            for (const [dr, dc] of directions) {
                dfs(r + dr, c + dc);
            }
        }
    }

    if (board[row][col] === 'M') {
        board[row][col] = 'X';
    } else {
        dfs(row, col);
    }

    return board;
}