class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        // Sort the three positions
        int x = Math.min(a, Math.min(b, c));
        int z = Math.max(a, Math.max(b, c));
        int y = a + b + c - x - z;   // the middle one
        
        int minMoves = 0;
        int maxMoves = 0;
        
        if (z - x > 2) {                 // not already consecutive
            // Minimum moves
            if (y - x <= 2 || z - y <= 2) {
                minMoves = 1;            // two stones are already adjacent or have one gap
            } else {
                minMoves = 2;            // otherwise need two moves
            }
            // Maximum moves: keep moving the endpoints one step at a time
            maxMoves = z - x - 2;
        }
        // else already consecutive → [0, 0]
        
        return new int[]{minMoves, maxMoves};
    }
}