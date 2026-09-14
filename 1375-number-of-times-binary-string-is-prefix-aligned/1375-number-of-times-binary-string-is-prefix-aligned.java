class Solution {
    public int numTimesAllBlue(int[] flips) {
        int max = 0;
        int count = 0;
        
        for (int i = 0; i < flips.length; i++) {
            max = Math.max(max, flips[i]);
            // After (i+1) steps, if max == i+1 then positions 1..(i+1) are all flipped
            if (max == i + 1) {
                count++;
            }
        }
        
        return count;
    }
}