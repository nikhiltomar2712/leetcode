import java.util.ArrayList;
import java.util.List;

class Solution {
    // Direction vectors: 0: Rook, 1: Queen, 2: Bishop
    private int[][][] dirs = {
        {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}, // Rook
        {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}}, // Queen
        {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}} // Bishop
    };

    public int countCombinations(String[] pieces, int[][] positions) {
        int n = pieces.length;
        // Convert to 0-based indexing
        for (int i = 0; i < n; i++) {
            positions[i][0]--;
            positions[i][1]--;
        }
        
        // Precompute all possible end states for each piece
        // A state is defined as [endRow, endCol, timeTaken]
        List<int[]>[] endStates = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            endStates[i] = new ArrayList<>();
            int dirIdx = getDirIndex(pieces[i]);
            
            // Option 1: Stay in place (0 time)
            endStates[i].add(new int[]{positions[i][0], positions[i][1], 0});
            
            // Option 2: Move in each valid direction
            for (int[] d : dirs[dirIdx]) {
                int r = positions[i][0];
                int c = positions[i][1];
                int time = 0;
                while (true) {
                    r += d[0];
                    c += d[1];
                    time++;
                    if (r < 0 || r >= 8 || c < 0 || c >= 8) break;
                    endStates[i].add(new int[]{r, c, time});
                }
            }
        }
        
        // DFS to count valid combinations
        return dfs(0, positions, endStates, new int[n][3]);
    }
    
    private int dfs(int idx, int[][] positions, List<int[]>[] endStates, int[][] chosen) {
        if (idx == positions.length) {
            return 1;
        }
        
        int count = 0;
        for (int[] state : endStates[idx]) {
            if (isValid(idx, state, positions, chosen)) {
                chosen[idx] = state;
                count += dfs(idx + 1, positions, endStates, chosen);
                chosen[idx] = null;
            }
        }
        return count;
    }
    
    private boolean isValid(int idx, int[] state, int[][] positions, int[][] chosen) {
        int startR = positions[idx][0];
        int startC = positions[idx][1];
        int endR = state[0];
        int endC = state[1];
        int totalTime = state[2];
        
        // Determine movement direction and speed (1 square per second)
        int dr = (endR - startR);
        int dc = (endC - startC);
        if (totalTime > 0) {
            dr /= totalTime;
            dc /= totalTime;
        }
        
        // Check collision against all previously placed pieces
        for (int j = 0; j < idx; j++) {
            int[] prev = chosen[j];
            int pStartR = positions[j][0];
            int pStartC = positions[j][1];
            int pEndR = prev[0];
            int pEndC = prev[1];
            int pTime = prev[2];
            
            int pDr = (pEndR - pStartR);
            int pDc = (pEndC - pStartC);
            if (pTime > 0) {
                pDr /= pTime;
                pDc /= pTime;
            }
            
            // Check for collision at every second from 0 to max(totalTime, pTime)
            int maxT = Math.max(totalTime, pTime);
            for (int t = 0; t <= maxT; t++) {
                // Position of current piece at time t
                int curR = startR + (t <= totalTime ? dr * t : endR);
                int curC = startC + (t <= totalTime ? dc * t : endC);
                // Wait, for t > totalTime, piece is at endR. But if t <= totalTime, it moves.
                // Correct: if t < totalTime, it's at start + dr*t. If t >= totalTime, it's at end.
                // But careful: at t = totalTime, it arrives at end.
                // For t > totalTime, it stays at end.
                if (t > totalTime) {
                    curR = endR;
                    curC = endC;
                } else {
                    curR = startR + dr * t;
                    curC = startC + dc * t;
                }
                
                // Position of previous piece at time t
                int prevR, prevC;
                if (t > pTime) {
                    prevR = pEndR;
                    prevC = pEndC;
                } else {
                    prevR = pStartR + pDr * t;
                    prevC = pStartC + pDc * t;
                }
                
                if (curR == prevR && curC == prevC) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private int getDirIndex(String piece) {
        if (piece.equals("rook")) return 0;
        if (piece.equals("queen")) return 1;
        return 2; // bishop
    }
}