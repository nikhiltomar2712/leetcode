class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // Calculate all 6 distances
        long[] dists = new long[6];
        dists[0] = distSq(p1, p2);
        dists[1] = distSq(p1, p3);
        dists[2] = distSq(p1, p4);
        dists[3] = distSq(p2, p3);
        dists[4] = distSq(p2, p4);
        dists[5] = distSq(p3, p4);
        
        // Sort distances
        java.util.Arrays.sort(dists);
        
        // For a square: 4 equal sides, 2 equal diagonals (longer)
        // No zero distances (distinct points)
        if (dists[0] == 0) return false;
        
        // Four sides equal, two diagonals equal and longer
        return dists[0] == dists[1] && dists[1] == dists[2] && dists[2] == dists[3] &&
               dists[4] == dists[5] &&
               dists[0] * 2 == dists[4];  // Pythagoras: diagonal^2 == side^2 * 2
    }
    
    private long distSq(int[] p, int[] q) {
        long dx = p[0] - q[0];
        long dy = p[1] - q[1];
        return dx * dx + dy * dy;
    }
}