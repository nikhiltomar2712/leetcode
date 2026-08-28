class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int n = tops.length;
        
        // Try making everything equal to tops[0]
        int rotations = check(tops[0], tops, bottoms, n);
        if (rotations != -1) {
            return rotations;
        }
        
        // If tops[0] != bottoms[0], also try bottoms[0]
        if (tops[0] != bottoms[0]) {
            return check(bottoms[0], tops, bottoms, n);
        }
        
        return -1;
    }
    
    private int check(int target, int[] tops, int[] bottoms, int n) {
        int topRotations = 0;    // rotations needed to make all tops = target
        int bottomRotations = 0; // rotations needed to make all bottoms = target
        
        for (int i = 0; i < n; i++) {
            // Impossible if target is on neither side
            if (tops[i] != target && bottoms[i] != target) {
                return -1;
            }
            
            // Need to rotate if top is not the target
            if (tops[i] != target) {
                topRotations++;
            }
            // Need to rotate if bottom is not the target
            else if (bottoms[i] != target) {
                bottomRotations++;
            }
            // If both sides are target, no rotation needed for either goal
        }
        
        return Math.min(topRotations, bottomRotations);
    }
}