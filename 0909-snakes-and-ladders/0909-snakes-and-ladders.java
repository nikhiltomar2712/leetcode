class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;
        
        // Convert 2D board label → destination (or -1)
        int[] dest = new int[target + 1];
        Arrays.fill(dest, -1);
        
        // Fill dest array following Boustrophedon order
        boolean leftToRight = true;
        int label = 1;
        for (int r = n - 1; r >= 0; r--) {
            if (leftToRight) {
                for (int c = 0; c < n; c++) {
                    if (board[r][c] != -1) {
                        dest[label] = board[r][c];
                    }
                    label++;
                }
            } else {
                for (int c = n - 1; c >= 0; c--) {
                    if (board[r][c] != -1) {
                        dest[label] = board[r][c];
                    }
                    label++;
                }
            }
            leftToRight = !leftToRight;
        }
        
        // BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[target + 1];
        
        queue.offer(1);
        visited[1] = true;
        int moves = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                
                if (curr == target) {
                    return moves;
                }
                
                // Try dice rolls 1 to 6
                for (int dice = 1; dice <= 6; dice++) {
                    int next = curr + dice;
                    if (next > target) break;
                    
                    // Take snake/ladder if exists
                    int finalPos = dest[next] == -1 ? next : dest[next];
                    
                    if (!visited[finalPos]) {
                        visited[finalPos] = true;
                        queue.offer(finalPos);
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}