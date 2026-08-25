class Solution(object):
    def gameOfLife(self, board):
        if not board or not board[0]:
            return
        
        m, n = len(board), len(board[0])
        
        def count_live_neighbors(r, c):
            # Count live neighbors (bits 0-2)
            dirs = [(-1,-1), (-1,0), (-1,1),
                    (0,-1),        (0,1),
                    (1,-1),  (1,0), (1,1)]
            count = 0
            for dr, dc in dirs:
                nr, nc = r + dr, c + dc
                if 0 <= nr < m and 0 <= nc < n:
                    count += board[nr][nc] & 1  # Check current state
            return count
        
        # First pass: compute next state and store in the second bit (bit 1)
        for i in range(m):
            for j in range(n):
                live_neighbors = count_live_neighbors(i, j)
                current = board[i][j] & 1
                
                if current == 1:
                    if live_neighbors == 2 or live_neighbors == 3:
                        board[i][j] = 3  # 11: current=1, next=1
                    else:
                        board[i][j] = 1  # 01: current=1, next=0
                else:  # current == 0
                    if live_neighbors == 3:
                        board[i][j] = 2  # 10: current=0, next=1
                    else:
                        board[i][j] = 0  # 00: current=0, next=0
        
        # Second pass: shift right to get next state
        for i in range(m):
            for j in range(n):
                board[i][j] >>= 1