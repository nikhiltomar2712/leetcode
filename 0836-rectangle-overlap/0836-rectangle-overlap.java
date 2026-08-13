class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if rec1 is to the left of rec2
        boolean left = rec1[2] <= rec2[0];
        // Check if rec1 is to the right of rec2
        boolean right = rec2[2] <= rec1[0];
        // Check if rec1 is above rec2
        boolean above = rec1[1] >= rec2[3];
        // Check if rec1 is below rec2
        boolean below = rec2[1] >= rec1[3];
        
        // If any non-overlap condition is true, they don't overlap
        return !(left || right || above || below);
    }
}